// Source code is decompiled from a .class file using FernFlower decompiler.
package com.mahasiswa_controller;

import com.mahasiswa_model.model_mahasiswa;
import java.util.List;

public interface controller_mahasiswa {
   void save(model_mahasiswa mahasiswa);
   void update(model_mahasiswa mahasiswa);
   void delete(model_mahasiswa mahasiswa);
   model_mahasiswa get(String nama);
   List<model_mahasiswa> list();
}
