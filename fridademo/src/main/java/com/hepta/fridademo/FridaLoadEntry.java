package com.hepta.fridademo;

import android.content.Context;

public class FridaLoadEntry {

    public static void Entry(Context context , String source,String argument){
        LoadSo(context,source);
        
    }

    private static void LoadSo(Context context, String source) {

        String abi= "arm64-v8a";
        if(!android.os.Process.is64Bit()){
            abi = "armeabi-v7a";
        }
        String str = source+"!/lib/"+abi+"/adrif.so";
        System.load(str);
    }


}
