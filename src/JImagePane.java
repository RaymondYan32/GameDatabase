import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//A custom Jpanel used to hold images
public class JImagePane extends JPanel
{
	private BufferedImage image;
	//initializes the first image
	public JImagePane(String directory)
	{	
		try
		{
			image = ImageIO.read(new File(directory));
		} catch (Exception e)
		{
		}
	}
	
	//changes the image 
	public void setImage(String directory)
	{
		try
		{
			image = ImageIO.read (new File (directory));
		}
		catch (IOException e)
		{
			try
			{
			if (directory.substring(0,5).equalsIgnoreCase("Fulls"))
				image = ImageIO.read(new File ("Fulls/blank.png"));
			else if (directory.substring(0,4).equalsIgnoreCase("Misc"))
				image = ImageIO.read(new File ("Misc/blank.png"));
			}
			catch (IOException e2)
			{
				
			}
		}
	}
	
	//redraws the image
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
