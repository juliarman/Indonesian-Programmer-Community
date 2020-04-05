/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kklp;

import com.view.FrmLogin;

/**
 *
 * @author User
 */
public class KKLP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new FrmLogin().setVisible(true);
        String url = System.getProperty("user.dir") + "\\file";
        System.out.print(url);
    }
}
