package com.model;

import com.controller.controller_tampilsuratkeluar;
import com.koneksi.ambilid;
import com.koneksi.koneksi;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.view.FrmTampilSuratKeluar;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

public class model_tampilsuratkeluar implements controller_tampilsuratkeluar {

    @Override
    public void Hapus(FrmTampilSuratKeluar tsk) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM suratkeluar WHERE id_suratkeluar = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                
            ps.setString(1, tsk.txtId.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(tsk, "Data Berhasil Dihapus");
            File dataPdf = FileUtils.getFile(System.getProperty("user.dir")+"\\file\\", ambilid.getNamafilenya());
            FileUtils.forceDelete(dataPdf);
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(model_tampilsuratkeluar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            TampilTabel(tsk);
        }
    }

    @Override
    public void PDF(FrmTampilSuratKeluar tsk) throws SQLException {
        try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+System.getProperty("user.dir")+"\\file\\"+ambilid.getNamafilenya());
            } catch (IOException ex) {
                Logger.getLogger(model_tampil.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void KlikTabel(FrmTampilSuratKeluar tsk) throws SQLException {
        int pilih = tsk.jTable1.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            tsk.txtId.setText(tsk.tbl.getValueAt(pilih, 0).toString());
            ambilid.setAmbilidnya(tsk.tbl.getValueAt(pilih, 0).toString());
            ambilid.setNamafilenya(tsk.tbl.getValueAt(pilih, 8).toString());
    }

    @Override
    public void TampilTabel(FrmTampilSuratKeluar tsk) throws SQLException {
        tsk.tbl.getDataVector().removeAllElements();
        tsk.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM suratkeluar GROUP BY tanggal_dibuat ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[10];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ob[6] = rs.getString(7);
                ob[7] = rs.getString(8);
                ob[8] = rs.getString(9);
                tsk.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    public void Cari(FrmTampilSuratKeluar tsk) throws SQLException {
        tsk.tbl.getDataVector().removeAllElements();
        tsk.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM suratkeluar WHERE tanggal_dibuat LIKE '%" + tsk.txtPencarian.getText().toString() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[10];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ob[6] = rs.getString(7);
                ob[7] = rs.getString(8);
                ob[8] = rs.getString(9);
                tsk.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
}
