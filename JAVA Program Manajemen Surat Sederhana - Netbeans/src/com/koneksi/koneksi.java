package com.koneksi;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class koneksi {
   private static Connection koneksi;
   
   public static Connection getKoneksi() throws ClassNotFoundException {
       if (koneksi == null) {
           try {
               String url ="jdbc:mysql://sql12.freesqldatabase.com:3306/sql12297545";
               String user="sql12297545";
               String pass="nMY1GCtICw";
               Class.forName("com.mysql.jdbc.Driver");
               koneksi = (Connection) DriverManager.getConnection(url, user, pass);
           } catch (Exception e) {
               System.out.println(e);
           }
       }
       return koneksi;
   }
}
