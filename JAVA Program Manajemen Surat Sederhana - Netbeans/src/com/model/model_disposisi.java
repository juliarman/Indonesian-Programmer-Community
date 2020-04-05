package com.model;

import com.controller.controller_disposisi;
import com.koneksi.koneksi;
import com.koneksi.userid;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.view.FrmDisposisi;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;

public class model_disposisi implements controller_disposisi{

    String dirpdf;
    File Urlpdf;
    @Override
    public void Simpan(FrmDisposisi dps) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT INTO disposisi (surat_dari, tanggal, perihal, isi_disposisi, diterima_tanggal, nomor_agenda, nomor_surat, terusan, paraf, nama_file) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            String tgltrm = ((JTextField)dps.cbTanggaltrm.getDateEditor().getUiComponent()).getText();
            String tglhrini = ((JTextField)dps.cbTanggalhrini.getDateEditor().getUiComponent()).getText();
            int paraf = 0;
            if (dps.ckParaf.isSelected()) {
                paraf = 1;
            }
            String terusan = null;
            if(dps.rdSekretaris.isSelected())
            {
                terusan = dps.rdSekretaris.getText();
            }
            else if(dps.rdFslts.isSelected())
            {
                terusan = dps.rdFslts.getText();
            }
            else if(dps.rdKlbg.isSelected())
            {
                terusan = dps.rdKlbg.getText();
            }
            else if(dps.rdPp.isSelected())
            {
                terusan = dps.rdPp.getText();
            }
            else if(dps.rdKb.isSelected())
            {
                terusan = dps.rdKb.getText();
            }
            else if(dps.rdk3.isSelected())
            {
                terusan = dps.rdk3.getText();
            }
            ps.setString(1, dps.txtSuratdari.getText());
            ps.setString(2, tgltrm);
            ps.setString(3, dps.txtPerihal.getText());
            ps.setString(4, dps.txtIsidspsisi.getText());
            ps.setString(5, tglhrini);
            ps.setString(6, dps.txtNmrAgenda.getText());
            ps.setString(7, dps.txtNmrSrt.getText());
            ps.setString(8, terusan);
            ps.setString(9, String.valueOf(paraf));
            ps.setString(10, dps.lblFile.getText());
            ps.executeUpdate();
            FileUtils.copyFileToDirectory(Urlpdf, new File(System.getProperty("user.dir")+"\\file"));
            JOptionPane.showMessageDialog(dps, "Data Berhasil Disimpan");
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Ulang(dps);
            
        }
    }

    @Override
    public void Ulang(FrmDisposisi dps) throws SQLException {
        dps.txtBidang.setText("");
        dps.txtIsidspsisi.setText("");
        dps.txtNmrAgenda.setText("");
        dps.txtNmrSrt.setText("");
        dps.txtPerihal.setText("");
        dps.txtSuratdari.setText("");
    }

    @Override
    public void Filepdf(FrmDisposisi dps) throws SQLException {
        File file;
        JFileChooser fd = new JFileChooser(new File("."));
        fd.setDialogTitle("Buka File...");
        int action = fd.showOpenDialog(dps);
        if (action != JFileChooser.APPROVE_OPTION) {
            return;
        }
        file = fd.getSelectedFile();
        dirpdf = file.getName();
        String pdftanpaspasi = dirpdf.replaceAll(" ", "_");
        Urlpdf = new File(fd.getSelectedFile().getPath());
        dps.lblFile.setText(dirpdf);
        userid.setUrlpdf(Urlpdf);
    }
    
}
