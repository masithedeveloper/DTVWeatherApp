package com.dvtweather.android.utilities;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.net.URLEncoder;

public class FileCache {
    private File imagesFolder;

    //----------------------------------------------------------------------------------------------
    public FileCache(Context cxt){
        imagesFolder = cxt.getDir("DVTWeatherApp", Context.MODE_PRIVATE);
        if (!imagesFolder.exists()) {
            if(!imagesFolder.mkdirs()) {}
        }
    }
    //----------------------------------------------------------------------------------------------
    public File getFile(String url){
        String filename = URLEncoder.encode(url);
        File f = new File(imagesFolder, filename);
        return f;
    }
    //----------------------------------------------------------------------------------------------
    public void clear(){
        File[] files = imagesFolder.listFiles();
        if(files == null) return;
        for(File f : files) f.delete();
    }
    //----------------------------------------------------------------------------------------------
}