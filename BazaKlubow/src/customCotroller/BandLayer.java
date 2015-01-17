package customCotroller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customStructure.RodzajKomponentu;
import customStructure.RodzajTabeli;
import customStructure.DoublePair;
import polaczenieZbaz¹.DbProcessor;
import widokiP.WidokPracMenu;
import klasyBazodanowe.Muzyk;
import klasyBazodanowe.Zespol;
import customGraphics.GBC;
import customGraphics.customComboBox.*;
public class BandLayer extends Warstwa<Zespol> {
	List<DataRecord<Zespol,Muzyk >> dataRows;
	private final static String LAST_ADD_VALUE="dodaj zespó³";
	
	
	
	
	public BandLayer(JPanel main,int ID) {
		super(main,ID);
		dataRows=new ArrayList<DataRecord<Zespol,Muzyk>>();
		
	}

	@Override
	public void initializeGraphicsRepresentation() {
		if(graphicsRepresentation==null){
			graphicsRepresentation=new JPanel();
			graphicsRepresentation.setLayout(new GridBagLayout());
		}
		prepareHeader(graphicsRepresentation);
		List<Zespol> myData=dowlandDataFromDataBase("");
		for(Zespol z: myData)
		{
			prepareDataRow(z);
		}
		prepareFooter();
		/*
		remoAllDataRows();
		removeFooter();
		for(Zespol z: myData)
		{
			prepareDataRow(z);
		}
		prepareFooter(-1);
		*/
	}

	@Override
	public List<Zespol> dowlandDataFromDataBase(String Condision) {
		List<Zespol> testowe = DbProcessor.getAllBands();
		
		return testowe;
	}

	@Override
	public void deleteChildren() {
		dataRows.clear();

	}

	@Override
	public void prepareHeader(JPanel header) {
		
		header.setLayout(new GridBagLayout());
		JLabel nrZ = new JLabel("ID");
		JLabel naz = new JLabel("Nazwa");
		JLabel datU = new JLabel("Data uformowania");
		JLabel[] tab = { nrZ, naz, datU };
		WidokPracMenu.createHeader(header, tab, 0, 0);
		
	}
	public void prepareDataRow(Zespol z)
	{
		int IdZ=z.getID();
		RodzajKomponentu IDComp=RodzajKomponentu.UNEDITABLE_INT;
		
		String nazwa=z.getNazwaZespolu();
		RodzajKomponentu nazwaComp=RodzajKomponentu.EDITABLE_TEXT;
		
		Timestamp date= z.getDataZalozenia();
		RodzajKomponentu dataComp=RodzajKomponentu.DATE_ONLY_YEAR;
		
		RodzajTabeli tabela=RodzajTabeli.ZESPO£Y;
		tabela.ID=z.getID();
		
		RodzajKomponentu[] species={IDComp,nazwaComp,dataComp};
		Object[] data={IdZ,nazwa,date};
		
		JPanel czlonkowie=new JPanel();
		czlonkowie.setLayout(new GridBagLayout());
		
		czlonkowie.add(new JLabel("cz³onkowie:"), new GBC(0,0,GBC.REMAINDER,1).setAnchor(GBC.WEST));
		System.out.println("Id zespolu to: "+IdZ);
		Warstwa<Muzyk> child=new MusicLayer(czlonkowie,IdZ);
		child.initializeGraphicsRepresentation();
		
		DoublePair result=WidokPracMenu.createDataRow(graphicsRepresentation,species,data,czlonkowie,tabela);
		JCheckBox box =result.getBox();
		Component[] components=result.getTabOFComponents();
		JPanel funcionButtoons=result.getFuncionButtons();
		JPanel gab=result.getGap();
			
			
			
			
		
		dataRows.add(new DataRecord<Zespol,Muzyk>(this,IdZ,box, components, funcionButtoons,child,gab));
	}

	@Override
	public void prepareFooter() {
		
		comboBox=new JComboBox<String>(new SortedComboBoxModel<String>(new String[]{LAST_ADD_VALUE},new MojComparator(LAST_ADD_VALUE)));
		
		WidokPracMenu.createFooter(graphicsRepresentation, comboBox, deletButton,footerGab, 3);
	}

	@Override
	public void remoAllDataRows() {
		for(DataRecord<Zespol, Muzyk> data:dataRows){
			Component[] tab=data.getComponents();
			graphicsRepresentation.remove(data.getCheckBox());
			graphicsRepresentation.remove(data.getFunctionBars());
			graphicsRepresentation.remove(data.getGap());
			for(Component c:tab)
			{
				graphicsRepresentation.remove(c);
			}
		}
		deleteChildren();
		
		
	}
	

}
