

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;

//A special JScrollPane used 
public class JRoundedScrollPane extends JScrollPane
{
	private BufferedImage image;
	
	//initializes the component with a nice background
	public JRoundedScrollPane(Component x, int y, int z)
	{
		super(x, y, z);
		try {                
	          image = ImageIO.read(new File("bg.png"));
	       } catch (Exception e) {
	            // handle exception...
	       }
	}
	
	//redraws the component when somethings happens in the program
	@Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());          
    }
}
