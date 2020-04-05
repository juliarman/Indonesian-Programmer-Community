
package com.model;

import com.controller.controller_surat_keluar;
import com.koneksi.koneksi;
import com.koneksi.userid;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.view.FrmSuratKeluar;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;


public class model_surat_keluar implements controller_surat_keluar {

    String dirpdf;
    File Urlpdf;
    @Override
    public void Simpan(FrmSuratKeluar sk) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT INTO suratkeluar (nomor_agenda, tujuan_surat, bidang, nomor_surat, tanggal_dibuat, lampiran, perihal, nama_file) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            String tanggal = ((JTextField)sk.cbTanggal.getDateEditor().getUiComponent()).getText();
            
            ps.setString(1, sk.txtNmrAgenda.getText());
            ps.setString(2, sk.txtTujuanSurat.getText());
            ps.setString(3, sk.txtBidang.getText());
            ps.setString(4, sk.txtNomorSurat.getText());
            ps.setString(5, tanggal);
            ps.setString(6, sk.txtLampiran.getText());
            ps.setString(7, sk.txtPerihal.getText());
            ps.setString(8, sk.lblFile.getText());
            ps.executeUpdate();
            FileUtils.copyFileToDirectory(Urlpdf, new File(System.getProperty("user.dir")+"\\file\\"));
            JOptionPane.showMessageDialog(sk, "Data Berhasil Disimpan");
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(model_surat_keluar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            Ulang(sk);
        }
    }

    @Override
    public void Ulang(FrmSuratKeluar sk) throws SQLException {
        sk.txtLampiran.setText("");
        sk.txtNmrAgenda.setText("");
        sk.txtNomorSurat.setText("");
        sk.txtPerihal.setText("");
        sk.txtTujuanSurat.setText("");
    }

    @Override
    public void UploadFile(FrmSuratKeluar sk) throws SQLException {
        File file;
        JFileChooser fd = new JFileChooser(new File("."));
        fd.setDialogTitle("Buka File...");
        int action = fd.showOpenDialog(sk);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        }
        file = fd.getSelectedFile();
        dirpdf = file.getName();
        String pdftanpaspasi = dirpdf.replaceAll(" ", "_");
        Urlpdf = new File(fd.getSelectedFile().getPath());
        sk.lblFile.setText(dirpdf);
        userid.setUrlpdf(Urlpdf);
    }
    
}
