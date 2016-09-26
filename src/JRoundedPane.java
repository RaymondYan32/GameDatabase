
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//A special Jpanel that uses a gradient color background and have rounded edges
public class JRoundedPane extends JPanel
{
	Color color1;
	Color color2;	
	
	//two colour gradient panes
	public JRoundedPane(Color a, Color b)
	{
		super();
		color1 = a;
		color2 = b;
	}
	
	//one colour light-dark gradient panes
	public JRoundedPane(Color a)
	{
		super();
		color1 = a;
		color2 = a.darker();
	}
	
	//Overrides the paintComponent so object is drawn in a custom way
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, this.getSize().height, color2);
		g2d.setPaint(gp);
		g2d.fillRoundRect(0, 0, getWidth(), this.getSize().height, 14, 14);
	}


}
