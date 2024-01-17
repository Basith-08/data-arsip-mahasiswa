// Source code is decompiled from a .class file using FernFlower decompiler.
package com.database;

import com.mysql.cj.jdbc.Driver;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi {

    static Connection con;

    public koneksi() {
    }

    public static Connection getConnection() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/arsip_data_mahasiswa";
                String username = "root";
                String pass = "";
                DriverManager.registerDriver(new Driver());
                con = DriverManager.getConnection(url, username, pass);
            } catch (Exception var3) {
                JOptionPane.showMessageDialog((Component) null, "Koneksi TIdak Berhasil!", "Pesan", 1);
                System.out.println(var3);
            }
        }

        return con;
    }
}
