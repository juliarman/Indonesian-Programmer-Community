package com.model;

import com.controller.controller_login;
import com.koneksi.koneksi;
import com.koneksi.userid;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.view.FrmInput;
import com.view.FrmLogin;
import com.view.FrmMenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class model_login implements controller_login {

    @Override
    public void Login(FrmLogin lgn) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT * FROM user WHERE username = '" + lgn.cbUsername.getSelectedItem().toString() + "' AND password = '" + lgn.txtPassword.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                userid.setUsername(lgn.cbUsername.getSelectedItem().toString());
                JOptionPane.showMessageDialog(lgn, "Selamat Datang, " + rs.getString(1).toString());
                new FrmMenu().setVisible(true);
                lgn.dispose();
            } else {
                JOptionPane.showMessageDialog(lgn, "User " + lgn.cbUsername.getSelectedItem().toString() + " Tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
