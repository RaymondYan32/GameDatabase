
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JToggleButton;

//A button for the hero button scrollbar
public class JInfoButton extends JToggleButton 
{
	private Color color1;
	private Color color2;
	
	private BufferedImage image;
	private static int active = 0;
	private int heroNum;
	private String name;

	//creates the button with the proper image and name
	public JInfoButton(String name, int heroNum)
	{
		super();
		try
		{
			image = ImageIO.read(new File("Thumbnails/" + name + ".png"));
		} catch (IOException ex)
		{
			// handle exception...
		}
		this.name = name;
		this.heroNum = heroNum;
		color1 = new Color(220, 180, 200, 250); //this
		color2 = new Color(200, 100, 160, 80);
	}
	
	//Getters
	public static int getActive()
	{
		return active;
	}
	
	public int getHeroNum()
	{
		return heroNum;
	}
	
	//Setters
	public static void setActive(int num)
	{
		active = num;
	}
	
	//updates the colouring of the button when clicked or not clicked
	public void updateButton(int i)
	{
		if (i == active)
		{
			color1 = new Color(200, 100, 160, 120);
			color2 = new Color(200, 100, 160, 200);
		}
		else
		{
			color1 = new Color(220, 180, 200, 250);
			color2 = new Color(200, 100, 160, 80);
		}
	}
	
	//redraws the button when somethings is done in the program
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(new Color(100, 80, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.clip(new RoundRectangle2D.Double(1, 1, this.getWidth() - 2, this.getHeight() - 2, 20, 20));
		
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, this.getSize().height, color2);
		g2d.setPaint(gp);

		g2d.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
		g.drawImage(image, 0, 0, null);
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g.setColor(new Color(50, 0, 50));
		g.drawString(name, 50, 30);
	}
	

}
