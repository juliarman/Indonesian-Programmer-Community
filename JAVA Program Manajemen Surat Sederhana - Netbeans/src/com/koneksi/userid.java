package com.koneksi;

import java.io.File;

public class userid {
    private static String username;
    private static File urlpdf;

    public static File getUrlpdf() {
        return urlpdf;
    }

    public static void setUrlpdf(File urlpdf) {
        userid.urlpdf = urlpdf;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        userid.username = username;
    }
    
}
