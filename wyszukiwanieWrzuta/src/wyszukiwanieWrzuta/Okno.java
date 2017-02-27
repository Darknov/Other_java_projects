package wyszukiwanieWrzuta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Okno {
	public static JFrame frame;
	private static MenuLook menulook;
	public static Okno okno;
	public static JPanel contane;
	public Okno(){
		okno = this;
	}
	
	public static void createJFrame() {
		frame = new JFrame();
		frame.setSize(600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void showColors() {
		
		
        JPanel yellowLabel = new JPanel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(20, 20));
        contane.add(yellowLabel,BorderLayout.CENTER);
	}

	public static void showWindow() {
		contane = new JPanel();
		menulook = new MenuLook(okno);
		createJFrame();
		frame.add(contane);
		frame.setJMenuBar(menulook.createMenuBar());
		showColors();

		frame.pack();
	}

	public static void main(String[] args) {
		Link link = new Link("http://w280.wrzuta.pl/audio/0ke2fhLOviN/madonna_-_into_the_groove_live_studio_version");
		//org.apache.commons.io.FileUtils.copyURLToFile(link.changeToDownload(), "lol");
		System.out.println(link.changeToDownload());
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showWindow();
			}
		});
	}
	
	

}
