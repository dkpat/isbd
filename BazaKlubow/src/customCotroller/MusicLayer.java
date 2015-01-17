package customCotroller;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customGraphics.customComboBox.MojComparator;
import customGraphics.customComboBox.SortedComboBoxModel;
import customStructure.DoublePair;
import customStructure.RodzajKomponentu;
import customStructure.RodzajTabeli;
import polaczenieZbaz¹.DbProcessor;
import widokiP.WidokPracMenu;
import klasyBazodanowe.Instrument;
import klasyBazodanowe.Muzyk;
import klasyBazodanowe.Zespol;

public class MusicLayer extends Warstwa<Muzyk> {
	
	List<DataRecord<Muzyk,Instrument >> dataRows;
	private final static String LAST_ADD_VALUE="dodaj muzyka";

	public MusicLayer(JPanel main,int ID) {
		super(main,ID);
		dataRows=new ArrayList<DataRecord<Muzyk,Instrument>>();
		
	}

	@Override
	public void initializeGraphicsRepresentation() {
			if(graphicsRepresentation==null){
				graphicsRepresentation=new JPanel();
				graphicsRepresentation.setLayout(new GridBagLayout());
			}
			prepareHeader(graphicsRepresentation);
			List<Muzyk> myData=dowlandDataFromDataBase("");
			for(Muzyk m: myData)
			{
				prepareDataRow(m);
			}
			prepareFooter();
			
		}

	

	@Override
	public List<Muzyk> dowlandDataFromDataBase(String Condision) {
		///Wersja bardzo robocza, do poprawy!!!!!!!!!!!!!!
		return DbProcessor.createMusicianList(ID_overridingObject);
	}

	@Override
	public void deleteChildren() {
		dataRows.clear();

	}

	@Override
	public void prepareHeader(JPanel header) {
		header.setLayout(new GridBagLayout());
		
		
		JLabel nrM = new JLabel("Nr Muzyka");
		JLabel nazwiskoM = new JLabel("Imiê");
		JLabel imieM = new JLabel("Nazwisko");
		JLabel ZjecieM = new JLabel("Zdjêcie");
		JLabel dataU=new JLabel("Data Urodzenia");
		JLabel dataS=new JLabel("Data Œmierci");
		JLabel[] doW;
		if(ID_overridingObject>=0){
			JLabel dataP=new JLabel("Data Dol¹czenia");
			JLabel dataO=new JLabel("Data Wystapienia");
			JLabel[] tab = { nrM, nazwiskoM, imieM,ZjecieM,dataU,dataS,dataP,dataO};
			doW=tab;
		}
		else{
			JLabel[] tab = { nrM, nazwiskoM, imieM,ZjecieM,dataU,dataS};
			doW=tab;
		}
		
		
		WidokPracMenu.createHeader(header, doW, 0, 0);

	}

	public void prepareDataRow(Muzyk m)
	{
		int IdM=m.getNrMuzyka();
		RodzajKomponentu IDComp=RodzajKomponentu.UNEDITABLE_INT;
		
		String nazwiskoM=m.getNazwisko();
		RodzajKomponentu nazwiskComp=RodzajKomponentu.EDITABLE_TEXT;
		
		String imieM=m.getImiê();
		RodzajKomponentu imieComp=RodzajKomponentu.EDITABLE_TEXT;
		
		BufferedImage zdjecieM=m.getZdjêcie();
		RodzajKomponentu imageComp=RodzajKomponentu.IMAGE;
		
		
		Timestamp dateU= m.getDataUrodzenia();
		RodzajKomponentu dataClasic=RodzajKomponentu.DATE_DD_MM_YYYY;
		
		Timestamp dateS= m.getDataŒmierci();
		
		
		RodzajKomponentu[] species;
		Object[] data;
		if(ID_overridingObject>=0)
		{
			
			
			species=new RodzajKomponentu[8];
			data=new Object[8];
			
			species[0]=IDComp;
			data[0]=IdM;
			
			species[1]=nazwiskComp;
			data[1]=nazwiskoM;
			
			species[2]=imieComp;
			data[2]=imieM;
			
			species[3]=imageComp;
			data[3]=zdjecieM;
			
			species[4]=dataClasic;
			data[4]=dateU;
			
			species[5]=dataClasic;
			data[5]=dateS;
			
			//Tymczasowe, tylko ¿eby wyœwietliæ grafikê i sprawdziæ czy jest poprawna
			species[6]=RodzajKomponentu.UNEDITABLE_TEXT;
			data[6]="brak daty";
			
			species[7]=RodzajKomponentu.UNEDITABLE_TEXT;
			data[7]="brak daty";
		}
			
		else
		{
			species=new RodzajKomponentu[6];
			data=new Object[6];
			
			species[0]=IDComp;
			data[0]=IdM;
			
			species[1]=nazwiskComp;
			data[1]=nazwiskoM;
			
			species[2]=imieComp;
			data[2]=imieM;
			
			species[3]=imageComp;
			data[3]=zdjecieM;
			
			species[4]=dataClasic;
			data[4]=dateU;
			
			species[5]=dataClasic;
			data[5]=dateS;
		}
			
		
		RodzajTabeli tabela=RodzajTabeli.MUZYCY;
		tabela.ID=m.getNrMuzyka();
		
		
		
	
		
		
		
		
		DoublePair result=WidokPracMenu.createDataRow(graphicsRepresentation,species,data,null,tabela);
		JCheckBox box =result.getBox();
		Component[] components=result.getTabOFComponents();
		JPanel funcionButtoons=result.getFuncionButtons();
		JPanel gab=result.getGap();
		
		dataRows.add(new DataRecord<Muzyk,Instrument>(this,IdM,box, components, funcionButtoons,gab));
	}
	
	@Override
	public void prepareFooter() {
		String[] tab;
		if(ID_overridingObject>=0){
        	List<Muzyk>czlonkowie=DbProcessor.createReverseMusicianList(ID_overridingObject);
			tab=new String[czlonkowie.size()+1];
			for(int i=0;i<czlonkowie.size();i++)
			{
				Muzyk m =czlonkowie.get(i);
				String nazwisko=m.getNazwisko();
				String imie=m.getImiê();
				tab[i]=String.format("%s %s",nazwisko,imie);
			}
			
		}
		else
			tab=new String[1];
		tab[tab.length-1]=LAST_ADD_VALUE;
		
		
		
		comboBox=new JComboBox<String>(new SortedComboBoxModel<String>(tab,new MojComparator(LAST_ADD_VALUE)));
		
		WidokPracMenu.createFooter(graphicsRepresentation, comboBox, deletButton,footerGab, 3);

	}

	@Override
	public void remoAllDataRows() {
		for(DataRecord<Muzyk, Instrument> data:dataRows){
			Component[] tab=data.getComponents();
			Warstwa<Instrument> child=data.getChild();
			child.clearGraphicsRepresentation();
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
