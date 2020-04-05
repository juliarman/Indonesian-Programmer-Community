package com.controller;

import com.view.FrmTplDsp;
import java.sql.SQLException;

public interface controller_tpldsp {
    public void PDF (FrmTplDsp tpdsp) throws SQLException;
    public void KlikTabel (FrmTplDsp tpdsp) throws SQLException;
    public void TampilTabel (FrmTplDsp tpdsp) throws SQLException;
    public void Cari (FrmTplDsp tpdsp) throws SQLException;
}
