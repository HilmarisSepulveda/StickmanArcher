import java.awt.Color;

import javax.swing.*;

public class MainWindow extends JFrame {
	
	
	public MainWindow() {
		
		super("Stickman");
		
		// Screen settings
		setBounds(0,0,1240,720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new MainPanel());
		setBackground(Color.RED);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		revalidate();
		repaint();
		
	}


	
}
