import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

/**
 * This class is the main panel of the game. It extends the JPanel class.
 * @author Hilmaris Sepulveda
 * @version 1.0
 */
public class MainPanel extends JPanel {

	Image background;

	/**
	 * Creates the main panel.
	 */
	MainPanel() {
		
		background = new 

		// Sets the background image
//		URL url = Main.class.getResource("/resources/stars.jpg");
//		background = new ImageIcon(url).getImage();

		setOpaque(true);
		setVisible(true);
	}

	/**
	 * Draws pictures in to the main panel.
	 */

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draws the background
		g.drawImage(background, 0, 0, null);

	}
}