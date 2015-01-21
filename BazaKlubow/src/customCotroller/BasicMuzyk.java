package customCotroller;

import java.awt.Component;
import java.util.HashSet;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import widokiP.WidokPracMenu;
import klasyBazodanowe.Instrument;
import klasyBazodanowe.Muzyk;

public abstract class BasicMuzyk<C> extends Warstwa {

	List<DataRecord<Muzyk,C >> dataRows;
	public BasicMuzyk(JPanel main, int ID) {
		super(main, ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void initializeGraphicsRepresentation();

	@Override
	public List dowlandDataFromDataBase(String Condision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDataRecordFromList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepareHeader(JPanel header) {
		/*
		JLabel nrM = new JLabel("Nr Muzyka");
		JLabel nazwiskoM = new JLabel("Nazwisko");
		JLabel imieM = new JLabel("Nazwikso");
		JLabel ZjecieM = new JLabel("Zdjêcie");
		JLabel dataU=new JLabel("Data Urodzenia");
		JLabel dataS=new JLabel("Data Œmierci");
		System.out.println("robiê");*/
		JLabel[] tab = { nrM, nazwiskoM, imieM,ZjecieM,dataU,dataS};
		
		HashSet<JLabel> nieSortowalne=new HashSet<JLabel>();
		nieSortowalne.add(ZjecieM);
		
		List<JPanel> naglowki= WidokPracMenu.createHeader(header,nieSortowalne, this,tab, 0, 0);
		for(JPanel p:naglowki)
		{
			Component[] child=p.getComponents();
			child[0].addMouseListener(sorter);
			child[1].addMouseListener(strzalkowy);
		}
	}

	@Override
	public void prepareFooter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remoAllDataRows() {
		// TODO Auto-generated method stub

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

	@Override
	public int ileMamRekordów() {
		// TODO Auto-generated method stub
		return 0;
	}

}
