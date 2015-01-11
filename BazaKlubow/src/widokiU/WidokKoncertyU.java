package widokiU;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import polaczenieZbaz¹.DbProcessor;
import customComponents.KoncertComponent;
import customGraphics.GBC;
import klasyBazodanowe.Koncert;


public class WidokKoncertyU extends JComponent 
{		ArrayList<Koncert> koncerty=new ArrayList<Koncert>(); 
		JPanel pojemnik;
	public WidokKoncertyU(){
		setLayout(new FlowLayout());
		pojemnik=new JPanel();
		pojemnik.setLayout(new GridBagLayout());
		add(pojemnik);
		updateKoncertList();
		updateKoncertComponents();
		pojemnik.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	}

	public void updateKoncertList()
	{
		koncerty.clear();
		koncerty=DbProcessor.createKoncertyList();
	}
	
	public void updateKoncertComponents()
	{
		pojemnik.removeAll();
		for (int i=0;i<koncerty.size();i++)
		{
			pojemnik.add(new KoncertComponent(koncerty.get(i)),new GBC(0, i).setAnchor(GBC.WEST).setInsets(20));
		}
	}
}
