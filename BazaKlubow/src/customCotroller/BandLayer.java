package customCotroller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.print.attribute.HashAttributeSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import customStructure.RodzajKomponentu;
import customStructure.RodzajTabeli;
import customStructure.DoublePair;
import customStructure.ZmiennaWartosc;
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
		WidokPracMenu.doajPust¹Przesztrzeñ(graphicsRepresentation);
		List<Zespol> myData=dowlandDataFromDataBase("");
		for(Zespol z: myData)
		{
			prepareDataRow(z);
		}
		ustwaWszystkieChecboxy();
		prepareFooter();
		/*
		remoAllDataRows();
		
		removeFooter();
		
		for(Zespol z: myData)
		{
			prepareDataRow(z);
		}
		prepareFooter();
		*/
		
	}

	@Override
	public List<Zespol> dowlandDataFromDataBase(String Condision) {
		List<Zespol> testowe = DbProcessor.getAllBands();
		
		return testowe;
	}

	@Override
	public void deleteDataRecordFromList() {
		dataRows.clear();

	}

	@Override
	public void prepareHeader(JPanel header) {
		HashSet<JLabel> nieSortowalne=new HashSet<JLabel>();
		header.setLayout(new GridBagLayout());
		JLabel nrZ = new JLabel("ID");
		JLabel naz = new JLabel("Nazwa");
		JLabel datU = new JLabel("Data uformowania");
		JLabel[] tab = { nrZ, naz, datU };
		WidokPracMenu.createHeader(header,nieSortowalne,this ,tab, 0, 0);
		
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
		int[] indexs={0,1,2};
		
		JPanel czlonkowie=new JPanel();
		czlonkowie.setLayout(new GridBagLayout());
		
		czlonkowie.add(new JLabel("cz³onkowie:"), new GBC(0,0,GBC.REMAINDER,1).setAnchor(GBC.WEST));
		czlonkowie.add(new JLabel(), new GBC(0,1));
		System.out.println("Id zespolu to: "+IdZ);
		Warstwa<Muzyk> child=new MusicLayer(czlonkowie,IdZ);
		child.initializeGraphicsRepresentation();
		
		DataRecord< Zespol, Muzyk> row=new DataRecord<Zespol, Muzyk>();
		DoublePair result=WidokPracMenu.createDataRow(graphicsRepresentation,this,row,species, indexs,data,czlonkowie,tabela);
		JCheckBox box =result.getBox();
		HashMap<Integer,JTextComponent> components=result.getMapOFComponents();
		JPanel funcionButtoons=result.getFuncionButtons();
		JPanel gab=result.getGap();
			
			
		
			
		row.setAll(this,IdZ,box, components, funcionButtoons,child,gab);;
		dataRows.add(row);
		
		row.dodajDoNowyWpisDoMapyWartosci(0, new ZmiennaWartosc<Object>(IdZ));
		row.dodajDoNowyWpisDoMapyWartosci(1, new ZmiennaWartosc<Object>(nazwa));
		row.dodajDoNowyWpisDoMapyWartosci(2, new ZmiennaWartosc<Object>(date));
	}

	@Override
	public void prepareFooter() {
		
		comboBox=new JComboBox<String>(new SortedComboBoxModel<String>(new String[]{LAST_ADD_VALUE},new MojComparator(LAST_ADD_VALUE)));
		
		WidokPracMenu.createFooter(graphicsRepresentation, comboBox, deletButton,footerGab, 3);
	}

	@Override
	public void remoAllDataRows() {
		for(DataRecord<Zespol, Muzyk> data:dataRows){
			Collection<JTextComponent> tab=data.getComponents().values();
			graphicsRepresentation.remove(data.getCheckBox());
			graphicsRepresentation.remove(data.getFunctionBars());
			graphicsRepresentation.remove(data.getGap());
			for(Component c:tab)
			{
				graphicsRepresentation.remove(c);
			}
			Warstwa<Muzyk> child=data.getChild();
			if(child!=null)
			{
				child.clearGraphicsRepresentation();
				graphicsRepresentation.remove(child.getGraphicsRepresentation());;
				child.deleteDataRecordFromList();
				
			}
				
		}
		deleteDataRecordFromList();
		
		
	}

	@Override
	public String utwórzZapytanieSortuj¹ce(String kluczSortujacy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dajNazweNaglowkaKolumny(String tekst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void wykonajSortowanie(String kluczSortowania) {
		// TODO Auto-generated method stub
		
	}
	
	public void ustawChecboxa(DataRecord<Zespol,Muzyk> record)
	{
		if(record.getChild()==null||record.getChild().ileMamRekordów()==0)
		{
			record.ustwaWidocznoœæCheckBoxa(true);
		}
		else
		{
			record.ustwaWidocznoœæCheckBoxa(false);
		}
	}
	
	public void ustwaWszystkieChecboxy()
	{
		for(DataRecord<Zespol, Muzyk> record:dataRows)
		{
			ustawChecboxa(record);
		}
	}
	@Override
	public int ileMamRekordów() {
		// TODO Auto-generated method stub
		return dataRows.size();
	}

}
