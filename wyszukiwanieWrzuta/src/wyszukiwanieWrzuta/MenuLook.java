package wyszukiwanieWrzuta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MenuLook implements ActionListener,ItemListener{
    JTextArea output;
    JScrollPane scrollPane;
    Okno okno;
        int x = 0;
    
    public MenuLook(Okno okno){
    	this.okno = okno;
    }
    
	public JMenuBar createMenuBar(){
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem item;
		
		menuBar = new JMenuBar();
		
		menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        
        item = new JMenuItem("Add colour", KeyEvent.VK_0);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        item.addActionListener(this);
        
        menu.add(item);
		
		return menuBar;
	}

	public Container createContentPane() {
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		return contentPane;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame2 = new JFrame();
		frame2.setSize(200, 100);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);
		JPanel panelWyboruKoloru = new JPanel();
		frame2.add(panelWyboruKoloru);
		int x,y,z;
		
		JPanel label = new JPanel();
//		label.setOpaque(true);
//		label.setBackground(new Color(x % 256, x % 256, x % 256));
//		label.setPreferredSize(new Dimension(20, 18));
//		okno.contane.add(label, BorderLayout.CENTER);
//		okno.frame.validate();
//		x++;
	}
}
