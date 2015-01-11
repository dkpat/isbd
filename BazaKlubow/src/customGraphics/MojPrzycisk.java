package customGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class MojPrzycisk extends JButton 
{
	private String title;
	private Font f;
	
	
	public MojPrzycisk(String title) {
		this.title=title;
		f=new Font("Sefir",0,14);
		FontRenderContext context=getFontMetrics(f).getFontRenderContext();
		Rectangle2D bounds=f.getStringBounds(title,context);
		setPreferredSize(new Dimension((int)bounds.getWidth(), (int)bounds.getHeight()));
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		
		g2.setFont(f);
		FontRenderContext context=g2.getFontRenderContext();
		Rectangle2D bounds=f.getStringBounds(title,context);
		
		int x=0;
		int y=(int)-bounds.getY();
		g2.drawString(title, x, y);
		System.out.println(getWidth());
	}

}
