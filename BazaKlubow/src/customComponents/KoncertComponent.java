package customComponents;
import javax.swing.*;
import javax.swing.border.Border;

import customGraphics.GBC;
import polaczenieZbaz�.DbProcessor;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import klasyBazodanowe.Koncert;
import klasyBazodanowe.Wyst�p;


public class KoncertComponent extends JComponent         
{
	
	private static final int ILE_ZNAK�W=30; //sta�a okre�laj�ca ile znak�w nazwy wydarzenia mog� wy�wietli�
	private Koncert koncert;
	
	
	public KoncertComponent(Koncert  koncert){
		this.koncert=koncert;
		ArrayList<Wyst�p> wyst�py=DbProcessor.createWyst�pyList(koncert.getID());
		
		//Tu si� bawi� zeby ustawi� d�ugo�� etykiety na 30 znak�w 
		//Straszne kombinowanie swoj� drog�
		JLabel nazwaWydarzenia=new JLabel(koncert.getNawzaW(),SwingConstants.CENTER);
		Font f=nazwaWydarzenia.getFont();
		FontRenderContext context=getFontMetrics(f).getFontRenderContext();
		Rectangle2D bounds=f.getStringBounds("ky", context);
		Double jednostka=bounds.getWidth();
		nazwaWydarzenia.setPreferredSize(new Dimension((int)(ILE_ZNAK�W/2*jednostka),(int)Math.ceil(bounds.getHeight())));
		
		
		
		JLabel dataW=new JLabel();
		SimpleDateFormat dateFormat=new SimpleDateFormat("E dd.MM.yyyy");
		dataW.setText(dateFormat.format(koncert.getData()));
		
		JLabel nazwaK=new JLabel();
		nazwaK.setText("Klub " + DbProcessor.getClubName(koncert.getNrKlubu()));
		
		
		// Drugi wiersz
		JLabel bilety=new JLabel();
		bilety.setHorizontalAlignment(JLabel.RIGHT);
		bilety.setText(String.format("bilety: %0$.2f z�", koncert.getCenaBiletu()));
		
		SimpleDateFormat hourFormat=new SimpleDateFormat("HH::mm");
		JLabel openHour=new JLabel("wej�cie od "+hourFormat.format(koncert.getGodzOtw()));
		JPanel secondRow=new JPanel();
		GridLayout gl=new GridLayout(1,3);
		gl.setHgap(10);
		secondRow.setLayout(gl);
		secondRow.add(nazwaK);
		secondRow.add(openHour);
		secondRow.add(bilety);
		
		
		setLayout(new GridBagLayout());
		add(nazwaWydarzenia,new GBC(0,0).setInsets(0, 0, 5, 0));
		add(dataW,new GBC(1, 0).setAnchor(GBC.EAST));
		add(secondRow,new GBC(0, 1,2,1).setAnchor(GBC.WEST).setInsets(0, WystepComponent.getIndentation(), 0, 0));
		
		//dodanie wyst�p
		int l=2;
		System.out.println("lenght"+wyst�py.size());
		for(Wyst�p w:wyst�py)
		{
			
			add(new WystepComponent(w),new GBC(0, l,2,1).setFill(GBC.BOTH).setInsets(10, 0, 0, 0));
			l++;
		}
		
	}
	
	
	
	protected void paintComponent(Graphics g)
	{
		
		
	}

}
