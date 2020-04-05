package com.controller;

import com.view.FrmEdit;
import com.view.FrmTampil;
import java.sql.SQLException;

public interface controller_tampil {
    public void Edit (FrmEdit Edt) throws SQLException;
    public void Hapus (FrmTampil Tpl) throws SQLException;
    public void PDF (FrmTampil Tpl) throws SQLException;
    public void Print (FrmTampil Tpl) throws SQLException;
    public void KlikTabel (FrmTampil Tpl) throws SQLException;
    public void TampilTabel (FrmTampil Tpl) throws SQLException;
    public void Cari (FrmTampil Tpl) throws SQLException;
    public void Bersih (FrmEdit Edt) throws SQLException;
    public void tampildata (FrmEdit Edt) throws SQLException;
}
