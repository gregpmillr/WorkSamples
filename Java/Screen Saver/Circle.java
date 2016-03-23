import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javax.swing.*;


public class Circle extends Shape {
	Random random = new Random();
	protected int x = 1 + random.nextInt(650);
	protected int y = 1 + random.nextInt(550);
	private int diameter=20;
	private int shapes = 1;
	private int nx = 1;
	private int ny = 1;
	private int color = random.nextInt(7);
	private int secondColor = random.nextInt(7);
		
	public void move(JPanel drawP){
		
	for(int i=0;i<shapes;i++)
	{
		x = x + (1*nx);
		y =  y + (1*ny);
		if(x==drawP.getWidth()-20)
		{
			nx = -1;
		}
		else if(x==0)
		{
			nx = 1;
		}
		if(y==drawP.getHeight()-20)
		{
			ny = -1;
		}
		else if(y==0)
		{
			ny = 1;
		}

	}//end for
	}//end move
		
	public void draw(Graphics g, JPanel drawP){
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage buffImage = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		Graphics2D gg = buffImage.createGraphics();		
		gg.setColor(colorArray[color]);
		gg.fillRect(0,0,80,80);
		gg.setColor(colorArray[secondColor]);
		gg.fillRect(4, 4, 40, 40);
		g2d.setPaint(new TexturePaint(buffImage,new Rectangle(10,10)));
		move(drawP);
		g.fillOval(x, y, diameter, diameter);
			
	}//end draw method
		
}//end class
