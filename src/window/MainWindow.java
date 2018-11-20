import javax.swing.*; 

public class Mainwindow extends JFrame{

	public static void main(String[] args) 
	{ 
		new Mainwindow(); 
	} 
	 
	JFrame frame; 
	Mainwindow() 
	{ 
		setTitle("Stickman"); 

		// setting close operation 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 

		setSize(1080, 700); 
		setLayout(null); 
		setLocationRelativeTo(null);
		setVisible(true); 
		setResizable(false);
		setBounds(0,0,1080,700);
		// revalidate();
		// repaint();
	} 
} 

