package tabele;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import klasyBazodanowe.Instrument;
import polaczenieZbaz¹.DbProcessor;

public class TabelaInstrumentow extends JPanel {

	public TabelaInstrumentow(int wymiarX, int wymiarY){
		super(new GridLayout(1,0));
		String[] kolumny = {"Nr", "Nazwa", "Typ instrumentu"};
		
		List<Instrument> listaInstrumentow = DbProcessor.createAllInstrumentList();
		
		Object[][] object = new Object[listaInstrumentow.size()][kolumny.length];
		System.out.println("Tabela instrumentow: " + kolumny.length + "x" + listaInstrumentow.size());
					
		int i=0;
		for(Instrument instr : listaInstrumentow){
			object[i][0] = instr.getID();
			object[i][1] = instr.getNazwaInstrumentu();
			object[i][2] = instr.getTypInstrumentu();
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
