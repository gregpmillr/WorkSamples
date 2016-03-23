import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.Random;
import javax.swing.JPanel;

public class Parallelogram extends Shape{
	
	Random random = new Random();
	private int x = 1 + random.nextInt(650);
	private int y = random.nextInt(550);
	private int shapes = 1;
	private int nx = 1;
	private int ny = 1;
	
	public void move(JPanel drawP){
			
			for(int i=0;i<shapes;i++)
			{
				x = x + (1*nx);
				y =  y - (1*ny);
				if(x>drawP.getWidth()-35)
				{
					nx = -1;
				}
				else if(x<0)
				{
					nx = 1;
				}
				if(y>drawP.getHeight()-20)
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
		int xPoints[] = { 0,20,35,15};
		int yPoints[] = { 20, 20, 0,0};
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo( xPoints[0], yPoints[0] );
		for (int count = 1; count < xPoints.length; count++)
		{triangle.lineTo(xPoints[count], yPoints[count]);};
		triangle.closePath();
		int color = 0 + random.nextInt(7);
	    g2d.setColor(colorArray[color]);
	    move(drawP);
	    g2d.translate(x, y);
		g2d.fill(triangle);        

	}//end draw method
	
}//end class
