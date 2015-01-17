package customCotroller;


import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import customStructure.RodzajKomponentu;

public abstract class Warstwa<T> {
	protected int ID_overridingObject;
	protected JPanel graphicsRepresentation;
	protected JComboBox<String> comboBox;
	protected JButton deletButton;
	protected JPanel footerGab=new JPanel();
//	protected boolean highestLevel=true; //- po co skoro mogê sprawdzaæ czy ma ojca, okazuje siê ¿ê wcale nie 
	private final static String DELET_INFO="usuñ";
	
	
	RodzajKomponentu typ;
	
	public Warstwa(JPanel main,int ID) {
		graphicsRepresentation=main;
		ID_overridingObject=ID;
		footerGab.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		deletButton=new JButton(DELET_INFO);
	}
	
	public void usunZawartosc()
	{
		graphicsRepresentation.removeAll();
		deleteChildren();
	}
	public abstract void initializeGraphicsRepresentation();
	
	public abstract List<T> dowlandDataFromDataBase(String Condision);
	
	public abstract  void deleteChildren();
	
	public abstract void prepareHeader(JPanel header);
	
	public abstract void prepareFooter();
	
	public  void clearGraphicsRepresentation()
	{
		graphicsRepresentation.removeAll();
	}
	public abstract void remoAllDataRows();
	
	
	public void removeFooter()
	{
		graphicsRepresentation.remove(comboBox);
		graphicsRepresentation.remove(deletButton);
		graphicsRepresentation.remove(footerGab);
	}
	/*
	public boolean isHighestLevel()
	{
		return highestLevel;
	}
	public void setHighestLevel (boolean highestLevel) {
		this.highestLevel=highestLevel;
	}
	*/


}
