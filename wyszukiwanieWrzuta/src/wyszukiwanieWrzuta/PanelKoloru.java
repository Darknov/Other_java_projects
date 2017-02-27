package wyszukiwanieWrzuta;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class PanelKoloru extends JLabel{

    public JLabel yellowLabel = new JLabel();
   
    public PanelKoloru(boolean opaque,Color color,Dimension dimension){
        this.yellowLabel.setOpaque(opaque);
        this.yellowLabel.setBackground(color);
        this.yellowLabel.setPreferredSize(dimension);
    }
}
