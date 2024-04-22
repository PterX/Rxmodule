package com.hepta.textmodule;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class LoadEntry {


    public static void Entry(Context context , String source,String argument){
        hookAppText(context);
    }

    //目前只需要这个函数，注入的时候修改目标MainActivity 的status的值
    private static void hookAppText(Context context) {
        Log.e("Rzx","hookAppText entry");
        try {
            Class<?> MainActivity_cls = context.getClassLoader().loadClass("com.hepta.textapp.MainActivity");
            Field status =  MainActivity_cls.getField("status");
            status.setAccessible(true);
            status.set(null, true);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Log.e("Rzx","hookAppText end");
    }
}
