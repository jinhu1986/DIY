package com.jinhu.diy.util;

import com.google.gson.Gson;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/5/10  21:37
 */

public class GsonUtils {
    public static Gson gson = new Gson();

    public static <T> T gsonToBean(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }
}
