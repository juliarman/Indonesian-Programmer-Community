package com.model;

import com.controller.controller_input;
import com.koneksi.koneksi;
import com.koneksi.userid;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.view.FrmEdit;
import com.view.FrmInput;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;

public class model_input implements controller_input {

    String dirpdf;
    File Urlpdf;
    @Override
    public void Simpan(FrmInput Ipt) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT INTO data (nomor_surat, asal_surat, bidang, tanggal_masuk, tanggal_surat, keterangan, nama_file) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            String tanggal = ((JTextField)Ipt.cbTanggal.getDateEditor().getUiComponent()).getText();
            String tglsrt = ((JTextField)Ipt.cbTglsurat.getDateEditor().getUiComponent()).getText();
            
            ps.setString(1, Ipt.txtNmrsurat.getText());
            ps.setString(2, Ipt.txtAsalsurat.getText());
            ps.setString(3, Ipt.txtBidang.getText());
            ps.setString(4, tanggal);
            ps.setString(5, tglsrt);
            ps.setString(6, Ipt.txtKeterangan.getText());
            ps.setString(7, Ipt.lblFile.getText());
            ps.executeUpdate();
            FileUtils.copyFileToDirectory(Urlpdf, new File(System.getProperty("user.dir")+"\\file"));
            JOptionPane.showMessageDialog(Ipt, "Data Berhasil Disimpan");
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Ulang(Ipt);
        }
    }

    @Override
    public void Ulang(FrmInput Ipt) throws SQLException {
        Ipt.txtNmrsurat.setText("");
        Ipt.txtAsalsurat.setText("");
        Ipt.txtKeterangan.setText("");
    }

    @Override
    public void Filepdf(FrmInput Ipt) throws SQLException {
        File file;
        JFileChooser fd = new JFileChooser(new File("."));
        fd.setDialogTitle("Buka File...");
        int action = fd.showOpenDialog(Ipt);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        }
        file = fd.getSelectedFile();
        dirpdf = file.getName();
        String pdftanpaspasi = dirpdf.replaceAll(" ", "_");
        Urlpdf = new File(fd.getSelectedFile().getPath());
        Ipt.lblFile.setText(dirpdf);
        userid.setUrlpdf(Urlpdf);
    }

    @Override
    public void Filepdf(FrmEdit Edt) throws SQLException {
        File file;
        JFileChooser fd = new JFileChooser(new File("."));
        fd.setDialogTitle("Buka File...");
        int action = fd.showOpenDialog(Edt);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        }
        file = fd.getSelectedFile();
        dirpdf = file.getName();
        String pdftanpaspasi = dirpdf.replaceAll(" ", "_");
        Urlpdf = new File(fd.getSelectedFile().getPath());
        Edt.lblFile.setText(dirpdf);
        userid.setUrlpdf(Urlpdf);
    }
    
}
