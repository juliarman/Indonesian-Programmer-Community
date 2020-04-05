package com.controller;

import com.view.FrmTampilSuratKeluar;
import java.sql.SQLException;

public interface controller_tampilsuratkeluar {
    public void Hapus (FrmTampilSuratKeluar tsk) throws SQLException;
    public void PDF (FrmTampilSuratKeluar tsk) throws SQLException;
    public void KlikTabel (FrmTampilSuratKeluar tsk) throws SQLException;
    public void TampilTabel (FrmTampilSuratKeluar tsk) throws SQLException;
    public void Cari (FrmTampilSuratKeluar tsk) throws SQLException;
}
