package com.dvtweather.android.utilities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dvtweather.android.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint("NewApi")
public class ImageHandler {

    private static final int BUFFER = 2048;
    private ContextWrapper _cw;
    private Context _context;
    private File _directory;
    private static String _dir;
    private static String _file_format = ".PNG";
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;
    Handler handler = new Handler();//handler to display images in UI thread
    final int default_image_id = R.drawable.ic_launcher;
    Bitmap bmp;
    Bitmap r_bitmap = null;
    /**
     * Constructors
     */
    //------------------------------------------------------------------------------
    public ImageHandler(){
        // there's no real use for this, I'm interested on Overloaded constructor
        // In fact there is, there is!!! :-)
    }
    public ImageHandler(Context context){ // this one is useful
        this._context = context;
        fileCache = new FileCache(this._context);
        executorService= Executors.newFixedThreadPool(5);
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     *
     * @param image_name
     * @return
     */
    public Bitmap loadImageFromStorage(String image_name){
        Bitmap bitmap = null;
        try {
            File file = new File(_directory, image_name);
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return bitmap;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Removes specified image from private storage
     * Works for both image and signature
     * @param image_name
     * @return
     */
    public boolean removeImage(String image_name) {
        File imageToRemove = new File(_directory, image_name); // system creates a app_directory directory... might need you to avoid the auto prefix
        try {
            imageToRemove.delete(); // delete actual record
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     *
     * @return
     */
    private static String createTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date_time = dateFormat.format(cal.getTime());
        String[] time = date_time.split(" ");
        String[] values = time[1].split(":");
        return values[0] + values[1] + new Random().nextInt() ; // 13032343+random_shit
        //return values[0] + values[1] + values[2] ; // 1303562343
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Encode Bitmap object in string base64
     * @param image
     * @return
     */
    public static String encodeTobase64(Bitmap image){
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 75, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT).toString();
        return imageEncoded;
    }

    public static byte[] encodeTobyteArray(Bitmap image){
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        return b;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Encode ByteArrayOutputStream object in string base64
     * @param
     * @return
     */
    public static String encodeTobase64(ByteArrayOutputStream baos){
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Decode Bitmap object in string base64
     * @param input
     * @return
     */
    public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     *
     * @param url
     * @param imageView
     */
    public void display_image_from_url(RoundedImageView imageView, String url){
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if(bitmap != null){
            imageView.setBackgroundResource(0);
            imageView.setBackgroundDrawable(null);
            imageView.setImageBitmap(null);
            imageView.setImageBitmap(bitmap);
        }
        else{ queuePhoto(url, imageView);}
    }

    private void queuePhoto(String url, RoundedImageView imageView) {
        PhotoToLoad p = new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     *
     * @param url
     * @return
     */
    public Bitmap getBitmap(String url) {
        url = "http://146.185.181.89/img/w/" + url +".png";
        final File f = fileCache.getFile(url);

        //from SD cache
        final Bitmap b = decodeFile(f);

        if(b!=null)
            return b;

        //from web
        try {
            Bitmap bitmap;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            //bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            //InputStream is = conn.getInputStream();
            try (OutputStream os = new FileOutputStream(f)) {
                CopyStream(is, os);
                os.close();
            }
            conn.disconnect();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Throwable ex){
            //ex.printStackTrace();
            if(ex instanceof OutOfMemoryError) memoryCache.clear();
            return null;
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size = 1024;
        try
        {
            byte[] bytes = new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //decodes image and scales it to reduce memory consumption
    /**
     *
     * @param f
     * @return
     */
    private Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1=new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();

            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 85;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap= BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;
        } catch (FileNotFoundException e) { }
        catch (IOException e) { }
        return null;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Task for the queue
    private class PhotoToLoad{
        public String url;
        public RoundedImageView imageView;
        public PhotoToLoad(String u, RoundedImageView i){
            url=u;
            imageView=i;
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad){
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            try{
                if(imageViewReused(photoToLoad))
                    return;
                bmp = getBitmap(photoToLoad.url);
                memoryCache.put(photoToLoad.url, bmp);
                if(imageViewReused(photoToLoad))
                    return;
                BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
                handler.post(bd);
            }catch(Throwable th){}
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     *
     * @param photoToLoad
     * @return
     */
    boolean imageViewReused(PhotoToLoad photoToLoad){
        String tag = imageViews.get(photoToLoad.imageView);
        if(tag==null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p){
            bitmap = b;
            photoToLoad = p;
        }
        public void run()
        {
            if(imageViewReused(photoToLoad))
                return;
            if(bitmap!=null) {
                photoToLoad.imageView.setBackgroundDrawable(null);
                photoToLoad.imageView.setImageBitmap(null);
                photoToLoad.imageView.setBackgroundResource(0);
                photoToLoad.imageView.setImageBitmap(bitmap); // use bitmap here
            }
            else {
                photoToLoad.imageView.setBackgroundDrawable(null);
                photoToLoad.imageView.setImageBitmap(null);
                photoToLoad.imageView.setBackgroundResource(0);
                photoToLoad.imageView.setImageBitmap(BitmapFactory.decodeResource(_context.getResources(), R.drawable.ic_launcher));
                //photoToLoad.imageView.setImageBitmap(bitmap); // I don't have to do this
            }
        }
    }
}



