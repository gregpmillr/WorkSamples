import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class Square extends Shape {
	Random random = new Random();
	private int size = 20;
	private int shapes = 1;
	private int nx = 1;
	private int ny = 1;
	private int m = 1;
	private int color = random.nextInt(5);
	private int x = 1 + random.nextInt(650);
	private int y = 1 + random.nextInt(550);
	
	public void move(JPanel drawP){
		
		for(int i=0;i<shapes;i++)
		{
			x = x - (1*nx);
			y =  y - (1*ny);
			if(x>drawP.getWidth()-20)
			{
				nx = 1;
			}
			else if(x<0)
			{
				nx = -1;
			}
			if(y>drawP.getHeight()-20)
			{
				ny = 1;
			}
			else if(y<0)
			{
				ny = -1;
			}
			//end if
			
			if(size>=20)
			{
				m = m*-1;
			}
			else if(size<=10)
			{
				m = m*-1;
			}
			//end if
			
			size += m;
		}//end for
	}//end move

	public void draw(Graphics g, JPanel drawP) {
		g.setColor(colorArray[color]);
		g.fillRect(x,y,size,size);
		move(drawP);
		
	}//end draw
	
}//end class
