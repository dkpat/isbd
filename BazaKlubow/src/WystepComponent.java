import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
public class WystepComponent extends JComponent 
{
	
private Wystêp wystêp;
private String nazwaZ;
private static int indentation=10;
private static int height;
private static int width;
private static double a;
private static double h;
private static double ascent;
private static final double FRACTION=17.0/30.0;
private static final int FONT_SIZE=14;
private static final String FONT_FAMILY=Font.SERIF;
public WystepComponent(Wystêp wystêp)
{
	
	this.wystêp=wystêp;
	nazwaZ=DbProcessor.getBandName(this.wystêp.getNrZespo³u());
	if(height==0);
		triangleSize();
	//Okreœlanie rozmiaru napisu
	Font f=new Font(Font.SERIF,0,FONT_SIZE);
	FontRenderContext context=getFontMetrics(f).getFontRenderContext();
	Rectangle2D bounds=f.getStringBounds(nazwaZ, context);
	width=(int)Math.ceil(bounds.getWidth());
	 System.out.println("Wysokoœæ napisu"+height);
}

public static void triangleSize()
{
	JPanel pom=new JPanel();
	Font f=new Font(FONT_FAMILY,0,FONT_SIZE);
	FontRenderContext conctext=pom.getFontMetrics(f).getFontRenderContext();
	Rectangle2D bounds=f.getStringBounds("Ky", conctext);
	double stringHeight=bounds.getHeight(); 
	double stringWidth=bounds.getWidth();
	height=(int)Math.ceil(stringHeight);
	
	ascent=-bounds.getY();
	 
	 //Obliczenie wysokoœci i boku rysowanego trójk¹ta
	 h=FRACTION*stringHeight;
	 a=2*Math.sqrt(3)*h/3.0;
	 
	
}
public Dimension getPreferredSize(){
		return new Dimension(width,height);
}

protected void paintComponent(Graphics g)
{	Graphics2D g2=(Graphics2D)g;
	
	double initX=getIndentation();
	double initY=(1.0-FRACTION)/2.0*height;

	Path2D.Double trojkat=new Path2D.Double();
	trojkat.moveTo(initX, initY);
	trojkat.lineTo(initX-a/2, initY+h);
	trojkat.lineTo(initX+a/2, initY+h);
	g2.draw(trojkat);
	g2.fill(trojkat);
	g2.drawString(nazwaZ,(int)Math.round( 2*getIndentation() ),(int)Math.round(ascent));
	
	
}

public static int getIndentation()
{
	if(height==0)
		triangleSize();
	return indentation+(int)Math.round(a/2);
}
}
