

package proyek.rpl.main;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import proyek.rpl.frame.frameLogin;

/**
 *
 * @author ibued
 */
public class MiniMarket {
    
     /**
     * @param args the command line arguments
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main (String args[]) throws UnsupportedLookAndFeelException{
        UIManager.put("nimbusBase", new Color(51,51,51));
        UIManager.put("controlText", new Color(51,51,51));
        
        UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new frameLogin().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(frameLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
