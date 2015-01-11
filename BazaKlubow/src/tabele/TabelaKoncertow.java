package tabele;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import klasyBazodanowe.Koncert;
import polaczenieZbaz¹.DbProcessor;

public class TabelaKoncertow extends JPanel {
	public TabelaKoncertow(int wymiarX, int wymiarY){
		super(new GridLayout(1,0));
		String[] kolumny = {"ID", "Wystêp", "Data", "Liczba osób", "Cena", "Godzina", "Nr klubu"};
		
		ArrayList<Koncert> listaKoncertow = new ArrayList<Koncert>();
		listaKoncertow = DbProcessor.createKoncertyList();
		
		Object[][] object = new Object[listaKoncertow.size()][kolumny.length];
		System.out.println("Tabela koncertow: " + kolumny.length + "x" + listaKoncertow.size());
					
		int i=0;
		for(Koncert konc : listaKoncertow){
			object[i][0] = konc.getID();
			object[i][1] = konc.getNawzaW();
			object[i][2] = konc.getData();
			object[i][3] = konc.getliczbaLudzi();
			object[i][4] = konc.getCenaBiletu();
			object[i][5] = konc.getGodzOtw();
			object[i][6] = konc.getNrKlubu();
			i++;
		}
		
		JTable table = new JTable (object, kolumny);
		table.setPreferredScrollableViewportSize(new Dimension(wymiarX, wymiarY));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);
		
	}
}