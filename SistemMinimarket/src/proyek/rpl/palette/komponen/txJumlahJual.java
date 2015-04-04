/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyek.rpl.palette.komponen;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author usu
 */
public final class txJumlahJual extends JTextField{
    
    private String shadowText;
    private Color shadowTextColor;
    private Color defaultColorText;
    private boolean shadow;

    public txJumlahJual() {
        super();
        defaultColorText = getForeground();
        setShadow(true);
        setDefaultColorText(getForeground());
        setShadowTextColor(Color.GRAY);
        setDefaultColorText(Color.GRAY);       
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (isShadow()) {
//                    setText("");
                    setForeground(getDefaultColorText());
                } 
            }

            @Override
            public void focusLost(FocusEvent e) {
                setShadow(false);
                if (getText().length() < 1) {
                    setForeground(getShadowTextColor());
                    setShadow(true);
                    setText(getShadowText());
                } else {
                    setShadow(false);
                    setForeground(getDefaultColorText());
                }
            }
        });
    }

    @Override
    @Deprecated
    public void setForeground(Color fg) {
        super.setForeground(fg);
    }

//    @Override
//    public String getText() {
//        if (isShadow()) {
//            return "";
//        }
//        return super.getText();
//    }

    public String getShadowText() {
        return shadowText;
    }

    public void setShadowText(String shadowText) {
        this.shadowText = shadowText;
    }

    public Color getShadowTextColor() {
        return shadowTextColor;
    }

    public void setShadowTextColor(Color shadowTextColor) {
        this.shadowTextColor = shadowTextColor;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public Color getDefaultColorText() {
        return defaultColorText;
    }

    public void setDefaultColorText(Color defaultColorText) {
        this.defaultColorText = defaultColorText;
    }
    
//    public static void main(String[] usu) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            SwingUtilities.invokeLater(() -> {
//             
//                txtShadow text = new txtShadow();
//                text.setColumns(30);
//                text.setShadowText("SHADOW TEXT");               
//            });
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(txtShadow.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
