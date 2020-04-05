package com.controller;

import com.view.FrmDisposisi;
import com.view.FrmEdit;
import com.view.FrmInput;
import java.sql.SQLException;

public interface controller_disposisi {
    public void Simpan (FrmDisposisi dps) throws SQLException;
    public void Ulang (FrmDisposisi dps) throws SQLException;
    public void Filepdf (FrmDisposisi dps) throws SQLException;
}
