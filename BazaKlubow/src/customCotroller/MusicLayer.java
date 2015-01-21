package customCotroller;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.text.JTextComponent;

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

public class MusicLayer extends BasicMuzyk<Instrument> {
	
	
	
	
	
	
	private final static String LAST_ADD_VALUE="dodaj muzyka";
	
	
	
	public MusicLayer(JPanel main,int ID) {
		super(main,ID);
		dataRows=new ArrayList<DataRecord<Muzyk,Instrument>>();
		deletButton.addActionListener(new AkcjaUsuwania());
	}

	
	
	@Override
	public void initializeGraphicsRepresentation() {
			if(graphicsRepresentation==null){
				graphicsRepresentation=new JPanel();
				graphicsRepresentation.setLayout(new GridBagLayout());
			}
			prepareHeader(graphicsRepresentation);
			WidokPracMenu.doajPust¹Przesztrzeñ(graphicsRepresentation);
			List<Muzyk> myData=dowlandDataFromDataBase("");
			for(Muzyk m: myData)
			{
				prepareDataRow(m);
			}
			prepareFooter();
			ustawWidzialnoœæPrzyiskuDoUsuwania();
			
		}

	

	@Override
	public List<Muzyk> dowlandDataFromDataBase(String Condision) {
		///Wersja bardzo robocza, do poprawy!!!!!!!!!!!!!!
		return DbProcessor.createMusicianList(ID_overridingObject);
	}

	@Override
	public void deleteDataRecordFromList() {
		dataRows.clear();

	}

	
	@Override
	public void prepareHeader(JPanel header) {
		
		super.prepareHeader(header);
		
	
			JLabel dataP=new JLabel("Data Dol¹czenia");
			JLabel dataO=new JLabel("Data Wystapienia");
			JLabel[] tab = { dataP,dataO};
			
		
	
		
		
		
		List<JPanel> naglowki =WidokPracMenu.createHeader(header,null, this,tab, 0, 0);
		System.out.println("rozmiar naglowka " +naglowki.size()+" !!!!!!!!!!!!");
		for(JPanel p:naglowki)
		{	
			
			Component[] child=p.getComponents();
			System.out.println("Moje dziecko "+child+" dziecko!!!!!!!!!!!!!!!!!!!!!");
			child[0].addMouseListener(sorter);
			child[1].addMouseListener(strzalkowy);
		}
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
		int indexs[];
		Object[] data;
		if(ID_overridingObject>=0)
		{
			
			
			species=new RodzajKomponentu[8];
			data=new Object[8];
			indexs=new int[8];
			
			species[0]=IDComp;
			indexs[0]=1;
			data[0]=IdM;
			
			
			
			species[1]=nazwiskComp;
			indexs[1]=2;
			data[1]=nazwiskoM;
			
			species[2]=imieComp;
			indexs[2]=3;
			data[2]=imieM;
			
			species[3]=imageComp;
			indexs[3]=4;
			data[3]=zdjecieM;
			
			species[4]=dataClasic;
			indexs[4]=5;
			data[4]=dateU;
			
			species[5]=dataClasic;
			indexs[5]=6;
			data[5]=dateS;
			
			//Tymczasowe, tylko ¿eby wyœwietliæ grafikê i sprawdziæ czy jest poprawna
			species[6]=RodzajKomponentu.UNEDITABLE_TEXT;
			indexs[6]=7;			
			data[6]="brak daty";
			
			species[7]=RodzajKomponentu.UNEDITABLE_TEXT;
			indexs[7]=8;		
			data[7]="brak daty";
			
		}
			
		else
		{
			species=new RodzajKomponentu[6];
			data=new Object[6];
			indexs=new int[8];
			
			species[0]=IDComp;
			indexs[0]=1;	
			data[0]=IdM;
			
			species[1]=nazwiskComp;
			indexs[1]=2;
			data[1]=nazwiskoM;
			
			species[2]=imieComp;
			indexs[2]=3;
			data[2]=imieM;
			
			species[3]=imageComp;
			indexs[3]=4;
			data[3]=nazwiskoM;

			species[4]=dataClasic;
			indexs[4]=5;
			data[4]=dateU;
			
			species[5]=dataClasic;
			indexs[5]=6;
			data[5]=dateS;
		}
			
		
		RodzajTabeli tabela=RodzajTabeli.MUZYCY;
		tabela.ID=m.getNrMuzyka();
		
		
		
	
		
		DataRecord< Muzyk, Instrument> row=new DataRecord<Muzyk, Instrument>();
		
		
		DoublePair result=WidokPracMenu.createDataRow(graphicsRepresentation,this,row,species,indexs,data,null,tabela);
		JCheckBox box =result.getBox();
		
		HashMap<Integer, JTextComponent> components=result.getMapOFComponents();
		JPanel funcionButtoons=result.getFuncionButtons();
		JPanel gab=result.getGap();
		System.out.println(components+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		row.setAllWithoutChild(this,IdM,box, components, funcionButtoons,gab);
		dataRows.add(row);
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
		removeOneDataRow(data);
		}
		deleteDataRecordFromList();

	}

	public void removeOneDataRow(DataRecord<Muzyk, Instrument> data){
		Collection<JTextComponent> tab=data.getComponents().values();
		Warstwa<Instrument> child=data.getChild();
		if(child!=null)
			child.clearGraphicsRepresentation();
		graphicsRepresentation.remove(data.getCheckBox());
		graphicsRepresentation.remove(data.getFunctionBars());
		graphicsRepresentation.remove(data.getGap());
		for(Component c:tab)
		{
			graphicsRepresentation.remove(c);
		}
		
	}

	@Override
	public String utwórzZapytanieSortuj¹ce(String kL) {
		
		StringBuilder zapytanieSortujace=null;
		String[] czlony=kL.split(",");
		for(String skladnik:czlony)
		{
			if(zapytanieSortujace==null){
				zapytanieSortujace=new StringBuilder();
				String[] tab=skladnik.split("-");
				System.out.println("Skladnik to "+tab[1]);
				String poCzym=dajNazweNaglowkaKolumny(tab[0]);
				String rodzaj=tab[1];
				zapytanieSortujace.append(poCzym);
				if(rodzaj.equals("DESCENT"))
					zapytanieSortujace.append(" DESC");
				
			}
			else{
				String[] tab=skladnik.split("-");
				String poCzym=dajNazweNaglowkaKolumny(tab[0]);
				String rodzaj=tab[1];
				zapytanieSortujace.append(","+poCzym);
				if(rodzaj.equals("DESCENT"))
					zapytanieSortujace.append(" DESC");
			}
				
		}
		System.out.println("Zapytanie sortuj¹ce to "+zapytanieSortujace.toString());
		return zapytanieSortujace.toString();
	}



	@Override
	public String dajNazweNaglowkaKolumny(String tekst) {
		if(tekst.equals("Nr Muzyka"))
			return "NrMuzyka";
		else
			if(tekst.equals("Imiê"))
				return "ImiêM";
			else
				if(tekst.equals("Nazwisko"))
					return "NazwiskoM";
				else
					if(tekst.equals("Zdjêcie"))
						return "ZdjêcieM";
					else
						if(tekst.equals("Data Urodzenia"))
							return "DataUrodzenia";
						else
							if(tekst.equals("Data Œmierci"))
								return "DataŒmierci";
							else
								if(tekst.equals("Data Do³¹czenia"))
									return "DataPrzyst";
								else
									if(tekst.equals("Data Wyst¹pienia"))
										return "DataOpuszczenia";
									else
										return null;
	}



	@Override
	public void wykonajSortowanie(String kluczSortowania) {
		String warunekSortowania=utwórzZapytanieSortuj¹ce(kluczSortowania);
		
		System.out.println("Dobry warunek :"+warunekSortowania);
		List<Muzyk> danePosortowane=DbProcessor.createSoretedMusicianList(ID_overridingObject,warunekSortowania );
		removeFooter();
		remoAllDataRows();
		for(Muzyk m: danePosortowane)
		{
			System.out.printf("%tF%n",m.getDataUrodzenia());
			prepareDataRow(m);
			
		}
		prepareFooter();
		graphicsRepresentation.validate();
		graphicsRepresentation.repaint();
	}

	

	public void test(){
		DataRecord r=new DataRecord(null, 0, null, null, null, null);
		
	}
	class AkcjaUsuwania implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton delete=(JButton)e.getSource();
			for(Object obiekt:toDelete)
			{
				DataRecord<Muzyk, Instrument> data=(DataRecord<Muzyk, Instrument>)obiekt;
				removeOneDataRow(data);
				dataRows.remove(data);
			}
			toDelete.clear();
			delete.setVisible(false);
			
		}
		
	}
	@Override
	public int ileMamRekordów() {
		return dataRows.size();
	}

	
}
