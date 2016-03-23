import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;

public class ShapePanel extends JPanel {
	private final int ANIMATION_DELAY = 5;
	Timer animationTimer = new Timer(ANIMATION_DELAY,new TimerHandler());
	Random random = new Random();
	int newShape;
	protected int shapes = 0;
	final ArrayList<Shape> shapesArray = new ArrayList<Shape>();

	public ShapePanel() {
		setLayout(null);
		
		JButton btnAddShape = new JButton("ADD SHAPE");
		btnAddShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapes = 1 + shapes;
				newShape=random.nextInt(4);
				if (newShape==0)
				{
					shapesArray.add((Shape) new Circle());
				}
				else if (newShape==1)
				{
					shapesArray.add((Shape) new Square());
				}
				else if (newShape==2)
				{
					shapesArray.add((Shape) new Parallelogram());
				}
				else if (newShape==3)
				{
					shapesArray.add((Shape) new Polygon());;
				}
			}
		});
		btnAddShape.setBounds(10, 11, 104, 23);
		add(btnAddShape);
		
		animationTimer.start();
	}//end constructor
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
	
	    for(int i = 0; i < shapes; i++)
	    {
	    //keep the panel position
	    AffineTransform keep = g2d.getTransform(); 
	    shapesArray.get(i).draw(g, this);
	    //reset the panel position
	    g2d.setTransform(keep);
	    }//end for
	    
	} // end method paintComponent
	
	private class TimerHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			repaint();//calls paintComponent
		}
		
	}//end TimerHandler
}//end class