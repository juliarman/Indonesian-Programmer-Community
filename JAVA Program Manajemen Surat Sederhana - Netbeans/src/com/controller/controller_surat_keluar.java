package com.controller;

import com.view.FrmSuratKeluar;
import java.sql.SQLException;

public interface controller_surat_keluar {
    public void Simpan (FrmSuratKeluar sk) throws SQLException;
    public void Ulang (FrmSuratKeluar sk) throws SQLException;
    public void UploadFile (FrmSuratKeluar sk) throws SQLException;
}
