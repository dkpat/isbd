import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
public class WystepComponent extends JComponent 
{private Wystêp wystêp;
private String NazwaZ;
 
public WystepComponent(Wystêp wystêp)
{
	this.wystêp=wystêp;
	NazwaZ=DbProcessor.getBandName(wystêp.getNrZespo³u());
	//setBorder(BorderFactory.createLineBorder(Color.YELLOW));;
}
public Dimension getPreferredSize(){
		Font f=new Font(Font.SERIF,0,14);
		FontRenderContext context=getFontMetrics(f).getFontRenderContext();
		Rectangle2D bounds=f.getStringBounds(NazwaZ, context);
		int height=(int)bounds.getHeight();
		int width=(int)bounds.getWidth()+10;
		
		return new Dimension(width,height);
}

protected void paintComponent(Graphics g)
{
	Graphics2D g2=(Graphics2D)g;
	Font f=new Font(Font.SERIF,0,14);
	g2.setFont(f);
	FontRenderContext context=g2.getFontRenderContext();
	
	Rectangle2D bounds=f.getStringBounds(NazwaZ,context );
	double triangleHeight=bounds.getHeight();
	double ascent=-bounds.getY();
	double initX=20;
	double initY=1.0/6.0*bounds.getHeight();
	
	double h=2.0/3.0*bounds.getHeight();
	
	double a=(2.0*Math.sqrt(3)/3.0)*h;
	
	double[] xCoordinate=new double[3];
	double[] yCoordinate=new double[3];
	xCoordinate[0]=initX;
	yCoordinate[0]=initY;
	xCoordinate[1]=initX-a/2;
	yCoordinate[1]=initY+h;
	xCoordinate[2]=initX+a/2;
	yCoordinate[2]=initY+h;
	Path2D.Double trojkat=new Path2D.Double();
	trojkat.moveTo(initX, initY);
	trojkat.lineTo(initX-a/2, initY+h);
	trojkat.lineTo(initX+a/2, initY+h);
	System.out.println(initY+h);
	
	//g2.fillRect(0, 0, 20, (int)bounds.getHeight());
	g2.drawLine(0, 0, 40, 0);
	g2.draw(trojkat);
	g2.fill(trojkat);
	g2.drawString(NazwaZ,(int)Math.round( 30+h),(int)Math.round(ascent));
	System.out.println("rysowa³em");
	
}

}
