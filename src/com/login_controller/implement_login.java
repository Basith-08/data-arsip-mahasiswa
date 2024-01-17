// Source code is decompiled from a .class file using FernFlower decompiler.
package com.login_controller;

import com.login_model.model_login;
import com.database.koneksi;
import com.view.view_admin;
//import com.view.admin_view;
import java.awt.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class implement_login implements controller_login {

    // Fungsi untuk menghasilkan nilai MD5 dari string input
    private static String getMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    @Override
    public void login(model_login login) {
        try {
            if (login.getUsername() == null && login.getUsername().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Enter Username");
            }
            if (login.getPassword() == null && login.getPassword().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Enter Password");
            }

            Connection con = koneksi.getConnection();
            String var10000 = login.getUsername();
            String sql = "SELECT * FROM akses WHERE username='" + login.getUsername() + "' AND password='" + getMD5(login.getPassword()) + "'";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String hashedPassword = getMD5(login.getPassword());
                if (login.getUsername().equals(rs.getString("username")) && hashedPassword.equals(storedPassword)) {
                    JOptionPane.showMessageDialog((Component) null, "berhasil login");
                    (new view_admin()).setVisible(true);
                    return;
                } else {
                    JOptionPane.showMessageDialog((Component) null, "username atau password salah");

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog((Component) null, "Gagal LoginMassage");
        }
    }
}
