// Source code is decompiled from a .class file using FernFlower decompiler.
package com.mahasiswa_controller;

import com.database.koneksi;
import com.mahasiswa_model.model_mahasiswa;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class implement_mahasiswa implements controller_mahasiswa {

    @Override
    public void save(model_mahasiswa mahasiswa) {
        try {
            if (mahasiswa.getNim() == null || mahasiswa.getNim().isEmpty() || !mahasiswa.getNim().matches("^[0-9]*$")) {
                JOptionPane.showMessageDialog((Component) null, "NIM tidak boleh kosong dan harus berupa angka");
            }
            if (mahasiswa.getNama() == null || mahasiswa.getNama().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Nama tidak boleh kosong");
            }
            if (mahasiswa.getJenis_kelamin() == null || mahasiswa.getJenis_kelamin().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Jenis kelamin tidak boleh kosong");
            }
            if (mahasiswa.getEmail() == null || mahasiswa.getEmail().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Email tidak boleh kosong");
            }
            if (mahasiswa.getNomor_telepon() == null || mahasiswa.getNomor_telepon().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Nomor telepon tidak boleh kosong");
            }
            if (mahasiswa.getJurusan() == null || mahasiswa.getJurusan().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Jurusan tidak boleh kosong");
            }
            if (mahasiswa.getAngkatan() == null || mahasiswa.getAngkatan().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Angkatan tidak boleh kosong");
            }
            if (mahasiswa.getAlamat() == null && mahasiswa.getAlamat().isEmpty()) {
                JOptionPane.showMessageDialog((Component) null, "Alamat tidak boleh kosong");
            }

            Connection con = koneksi.getConnection();
            String sql = "INSERT INTO mahasiswa (nim, nama, jenis_kelamin, email, nomor_telepon, jurusan, angkatan, alamat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, mahasiswa.getNim());
            statement.setString(2, mahasiswa.getNama());
            statement.setString(3, mahasiswa.getJenis_kelamin());
            statement.setString(4, mahasiswa.getEmail());
            statement.setString(5, mahasiswa.getNomor_telepon());
            statement.setString(6, mahasiswa.getJurusan());
            statement.setString(7, mahasiswa.getAngkatan());
            statement.setString(8, mahasiswa.getAlamat());
            statement.executeUpdate();
            JOptionPane.showMessageDialog((Component) null, "Data telah disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog((Component) null, "Gagal simpan dataMassage");
        }
    }

    @Override
    public void update(model_mahasiswa mahasiswa) {
        try {
            Connection con = koneksi.getConnection();
            String sql = "UPDATE mahasiswa SET nim=?, nama=?, jenis_kelamin=?, email=?, nomor_telepon=?, jurusan=?, angkatan=?, alamat=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, mahasiswa.getNim());
            statement.setString(2, mahasiswa.getNama());
            statement.setString(3, mahasiswa.getJenis_kelamin());
            statement.setString(4, mahasiswa.getEmail());
            statement.setString(5, mahasiswa.getNomor_telepon());
            statement.setString(6, mahasiswa.getJurusan());
            statement.setString(7, mahasiswa.getAngkatan());
            statement.setString(8, mahasiswa.getAlamat());
            statement.setInt(9, mahasiswa.getId());
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog((Component) null, "Data telah diupdate");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog((Component) null, "Gagal update dataMassage");
        }

    }

    @Override
    public void delete(model_mahasiswa mahasiswa) {
        try {
            Connection con = koneksi.getConnection();
            String sql = "DELETE FROM mahasiswa WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, mahasiswa.getId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog((Component) null, "Data telah dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog((Component) null, "Gagal hapus dataMassage ");
        }

    }

    @Override
    public model_mahasiswa get(int id) {
        model_mahasiswa ms = new model_mahasiswa();

        try {
            Connection con = koneksi.getConnection();
            String sql = "SELECT * FROM mahasiswa WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ms.setId(rs.getInt("id"));
                ms.setNim(rs.getString("nim"));
                ms.setNama(rs.getString("nama"));
                ms.setJenis_kelamin(rs.getString("jenis_kelamin"));
                ms.setEmail(rs.getString("email"));
                ms.setNomor_telepon(rs.getString("nomor_telepon"));
                ms.setJurusan(rs.getString("jurusan"));
                ms.setAngkatan(rs.getString("angkatan"));
                ms.setAlamat(rs.getString("alamat"));
            }
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog((Component) null, "Gagal" + e.getMessage());
        }

        return ms;
    }

    @Override
    public List<model_mahasiswa> list() {
        List<model_mahasiswa> list = new ArrayList<>();

        try {
            Connection con = koneksi.getConnection();
            String sql = "SELECT * FROM mahasiswa";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                model_mahasiswa ms = new model_mahasiswa();
                ms.setId(rs.getInt("id"));
                ms.setNim(rs.getString("nim"));
                ms.setNama(rs.getString("nama"));
                ms.setJenis_kelamin(rs.getString("jenis_kelamin"));
                ms.setEmail(rs.getString("email"));
                ms.setNomor_telepon(rs.getString("nomor_telepon"));
                ms.setJurusan(rs.getString("jurusan"));
                ms.setAngkatan(rs.getString("angkatan"));
                ms.setAlamat(rs.getString("alamat"));
                list.add(ms);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog((Component) null, "Gagal menampilkan data");
        }

        return list;
    }

    @Override
    public model_mahasiswa get(String nama) {
        model_mahasiswa ms = new model_mahasiswa();

        try {
            Connection con = koneksi.getConnection();
            String sql = "SELECT * FROM mahasiswa WHERE nama=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nama);
            ResultSet rs = statement.executeQuery();
 
            while (rs.next()) {
                ms.setNama(rs.getString("nama"));
                ms.setJenis_kelamin(rs.getString("jenis_kelamin"));
                ms.setEmail(rs.getString("email"));
                ms.setJurusan(rs.getString("jurusan"));
                ms.setAngkatan(rs.getString("angkatan"));
            }
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog((Component) null, "Gagal" + e.getMessage());
        }

        return ms;
    }
}
