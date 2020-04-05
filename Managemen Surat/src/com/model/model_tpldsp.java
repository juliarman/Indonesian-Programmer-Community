package com.model;

import com.controller.controller_tpldsp;
import com.koneksi.ambilid;
import com.koneksi.koneksi;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.view.FrmTplDsp;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class model_tpldsp implements controller_tpldsp{

    @Override
    public void PDF(FrmTplDsp tpdsp) throws SQLException {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+System.getProperty("user.dir")+"\\file\\"+ambilid.getNamafilenya());
        } catch (IOException ex) {
            Logger.getLogger(model_tpldsp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void KlikTabel(FrmTplDsp tpdsp) throws SQLException {
        try
        {
            int pilih = tpdsp.jTable1.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            tpdsp.txtId.setText(tpdsp.tbl1.getValueAt(pilih, 0).toString());
            ambilid.setAmbilidnya(tpdsp.tbl1.getValueAt(pilih, 0).toString());
            ambilid.setNamafilenya(tpdsp.tbl1.getValueAt(pilih, 10).toString());
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }

    @Override
    public void TampilTabel(FrmTplDsp tpdsp) throws SQLException {
        tpdsp.tbl1.getDataVector().removeAllElements();
        tpdsp.tbl1.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM disposisi ORDER BY tanggal ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[11];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ob[6] = rs.getString(7);
                ob[7] = rs.getString(8);
                ob[8] = rs.getString(9);
                ob[9] = rs.getString(10);
                ob[10] = rs.getString(11);
                tpdsp.tbl1.addRow(ob);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    public void Cari(FrmTplDsp tpdsp) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
