package customGraphics;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

public class SortButton extends JComponent {

	private int weight;
	private  int height;
	private double fractionOfMargin=1.0/4.0;
	boolean ascent=true;
	public SortButton(int height) {
		super();
		enableInputMethods(true);
		this.height=(int) Math.ceil(height/4.0)*4;
		weight=(int) Math.round(height/2.0);
	}
	public boolean getAscent(){
		return ascent;
	}
	public void isAscent(boolean ascent)
	{
		this.ascent=ascent;
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(weight,height);
	}
	@Override
	public Dimension getMaximumSize(){
		return new Dimension(weight,height);
	}
	@Override
	public Dimension getMinimumSize(){
		return new Dimension(weight,height);
	}
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		double margin=Math.round(height*fractionOfMargin);
		
		double aCzwartych=weight/2.0;
		Line2D linia=new Line2D.Double(aCzwartych,margin,aCzwartych,height-margin);
		Line2D lewaSztrzałka;
		Line2D prawaStrzałka;
		/*
		System.out.println(margin);
		System.out.println(aCzwartych);
		System.out.println(height);
		*/
		if(ascent)
		{
			lewaSztrzałka=new Line2D.Double(0,aCzwartych+margin,aCzwartych,margin);
			prawaStrzałka=new Line2D.Double(aCzwartych,margin,weight,aCzwartych+margin);
		}else{
			lewaSztrzałka=new Line2D.Double(0,height-aCzwartych-margin,aCzwartych,height-margin);
			prawaStrzałka=new Line2D.Double(aCzwartych,height-margin,weight,height-aCzwartych-margin);
		}
		g2.draw(lewaSztrzałka);
		g2.draw(linia);
		g2.draw(prawaStrzałka);
		
	}
	

}
