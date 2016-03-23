import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JPanel;

public class Polygon extends Shape{
	
	Random random = new Random();
	private int x = 1 + random.nextInt(650);
	private int y = 1 + random.nextInt(550);
	private int shapes = 1;
	private int nx = 1;
	private int ny = 1;
	private int color = 0 + random.nextInt(7);
	private int newColor = 0 + random.nextInt(7);
	
	public void move(JPanel drawP){
		
		for(int i=0;i<shapes;i++)
		{
			x = x + (1*nx);
			y =  y - (1*ny);
			if(x>drawP.getWidth()-30)
			{
				nx = -1;
			}
			else if(x<0)
			{
				nx = 1;
			}
			if(y>drawP.getHeight()-30)
			{
				ny = 1;
			}
			else if(y<0)
			{
				ny = -1;
			}
		}//end for
	}//end move
		
	public void draw(Graphics g, JPanel drawP){	 
		Graphics2D g2d = (Graphics2D) g;
		int xPoints[] = { 0,10,0,25,30,30,10,13};
		int yPoints[] = { 20,15,0,20,10,30,30,25};
		GeneralPath poly = new GeneralPath();
		poly.moveTo( xPoints[0], yPoints[0] );
		for (int count = 1; count < xPoints.length; count++)
		{poly.lineTo(xPoints[count], yPoints[count]);};
		poly.closePath();
		g2d.setPaint(new GradientPaint(6,6,colorArray[color], 21,21,colorArray[newColor],true));
	    move(drawP);
	    g2d.translate(x, y);
		g2d.fill(poly);        
	}//end draw method
	
}//end class
