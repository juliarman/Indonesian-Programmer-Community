package com.controller;

import com.view.FrmEdit;
import com.view.FrmInput;
import java.sql.SQLException;

public interface controller_input {
    public void Simpan (FrmInput Ipt) throws SQLException;
    public void Ulang (FrmInput Ipt) throws SQLException;
    public void Filepdf (FrmInput Ipt) throws SQLException;
    public void Filepdf (FrmEdit Edt) throws SQLException;
}
