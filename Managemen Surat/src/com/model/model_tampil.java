package com.model;

import com.controller.controller_tampil;
import com.koneksi.ambilid;
import com.koneksi.koneksi;
import com.koneksi.userid;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.view.FrmEdit;
import com.view.FrmInput;
import com.view.FrmTampil;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;

public class model_tampil implements controller_tampil {

    @Override
    public void Hapus(FrmTampil Tpl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM data WHERE id_surat = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            
            ps.setString(1, Tpl.txtId.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(Tpl, "Data Berhasil Dihapus");
            File dataPdf = FileUtils.getFile(System.getProperty("user.dir")+"\\file\\", ambilid.getNamafilenya());
            FileUtils.forceDelete(dataPdf);
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            TampilTabel(Tpl);
        }
    }

    @Override
    public void PDF(FrmTampil Tpl) throws SQLException {
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+System.getProperty("user.dir")+"\\file\\"+ambilid.getNamafilenya());
            } catch (IOException ex) {
                Logger.getLogger(model_tampil.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }

    @Override
    public void Print(FrmTampil Tpl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void KlikTabel(FrmTampil Tpl) throws SQLException {
        try {
            int pilih = Tpl.jTable1.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            Tpl.txtId.setText(Tpl.tbl.getValueAt(pilih, 0).toString());
            ambilid.setAmbilidnya(Tpl.tbl.getValueAt(pilih, 0).toString());
            ambilid.setNamafilenya(Tpl.tbl.getValueAt(pilih, 7).toString());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    public void TampilTabel(FrmTampil Tpl) throws SQLException {
        Tpl.tbl.getDataVector().removeAllElements();
        Tpl.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM data GROUP BY tanggal_masuk ASC";
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
                Tpl.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    public void Cari(FrmTampil Tpl) throws SQLException {
        Tpl.tbl.getDataVector().removeAllElements();
        Tpl.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM data WHERE tanggal_surat LIKE '%" + Tpl.txtPencarian.getText().toString() + "%'";
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
                Tpl.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    public void Edit(FrmEdit Edt) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE data SET nomor_surat = ?, asal_surat = ?, bidang = ?, tanggal_masuk = ?, tanggal_surat = ?, keterangan = ?, nama_file = ? WHERE id_surat = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            String tanggal = ((JTextField)Edt.cbTanggal.getDateEditor().getUiComponent()).getText();
            String tglsrt = ((JTextField)Edt.cbTglsurat.getDateEditor().getUiComponent()).getText();
            
            ps.setString(1, Edt.txtNmrsurat.getText());
            ps.setString(2, Edt.txtAsalsurat.getText());
            ps.setString(3, Edt.txtBidang.getText());
            ps.setString(4, tanggal);
            ps.setString(5, tglsrt);
            ps.setString(6, Edt.txtKeterangan.getText());
            ps.setString(7, Edt.lblFile.getText());
            ps.setString(8, ambilid.getAmbilidnya());
            ps.executeUpdate();
            FileUtils.copyFileToDirectory(userid.getUrlpdf(), new File(System.getProperty("user.dir")+"\\file"));
            JOptionPane.showMessageDialog(Edt, "Data Berhasil Diupdate");
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Bersih(FrmEdit Edt) throws SQLException {
        Edt.txtNmrsurat.setText("");
        Edt.txtAsalsurat.setText("");
        Edt.txtKeterangan.setText("");
    }

    @Override
    public void tampildata(FrmEdit Edt) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM data WHERE id_surat = '" + ambilid.getAmbilidnya() + "'";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Edt.txtNmrsurat.setText(rs.getString("nomor_surat").toString());
                Edt.txtAsalsurat.setText(rs.getString("asal_surat").toString());
                Edt.txtBidang.setText(rs.getString("bidang").toString());
                Edt.txtKeterangan.setText(rs.getString("keterangan").toString());
            }
        } catch (Exception e) {
            
        }
    }
    
}
