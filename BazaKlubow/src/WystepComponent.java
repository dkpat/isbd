import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
public class WystepComponent extends JComponent 
{private Wystêp wystêp;

public WystepComponent(Wystêp wystêp)
{
	this.wystêp=wystêp;
}

protected void paintComponent(Graphics g)
{
	Graphics2D g2=(Graphics2D)g;
	Font f=new Font(Font.SERIF,0,14);
	g2.setFont(f);
	FontRenderContext context=g2.getFontRenderContext();
	String NazwaZ=DbProcessor.getBandName(wystêp.getnrZespo³u());
	Rectangle2D bounds=f.getStringBounds(NazwaZ,context );
	double triangleHeight=bounds.getHeight();
	Polygon trojkat=new Polygon();
}

}
