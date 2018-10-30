import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Window {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Stickman");
		
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.getGraphics();
		frame.setDefaultCloseOperation
							(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
