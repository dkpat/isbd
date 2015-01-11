package widokiP;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import polaczenieZbaz¹.DbProcessor;
import klasyBazodanowe.Koncert;

public class WidokPracMenu extends JPanel {

	public final static String info = "Witamy w programie";

	
	public WidokPracMenu() {
		
		setLayout(new BorderLayout());
		JTextField t1 = new JTextField(info);
		add(BorderLayout.NORTH, t1);
		
		//boczny panel do lewego menu
		JPanel bocznyL = new JPanel();
		bocznyL.setLayout(new GridLayout(0,1,0,10));
		
		//stworz przyciski do lewego menu
		JButton koncerty = new JButton("Koncerty");
		JButton zespoly = new JButton("Zespo³y");
		JButton instrumenty = new JButton("Instrumenty");
		
		//Testowy przycisk do przechodzenia miêdzy kartami
		//JButton next = new JButton("Next");
		
		bocznyL.add(koncerty);
		bocznyL.add(zespoly);
		bocznyL.add(instrumenty);
		//bocznyL.add(next);
		
		//panel pomocniczy w którym bêd¹ znajdowaæ siê przyciski
		JPanel pomocL = new JPanel();
		pomocL.setLayout(new FlowLayout());
		pomocL.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		pomocL.add(bocznyL);
		add(BorderLayout.WEST,pomocL);
		
		
		//panele œrodkowe
		JPanel panelKoncerty = new JPanel();
		JPanel panelZespoly = new JPanel();
		JPanel panelInstrumenty = new JPanel();
		
		/*
		 * ustawi³em im tymczasowo ró¿ne kolorki ¿eby ³atwiej je identyfikowaæ,
		 * bo nic w nich nie ma jeszcze 
		 */
		panelKoncerty.setBackground(Color.CYAN);
		panelZespoly.setBackground(Color.GRAY);
		panelInstrumenty.setBackground(Color.BLUE);
		
		final JPanel srodek = new JPanel(new CardLayout());
		
		srodek.add(panelKoncerty, "Koncerty");
		srodek.add(panelZespoly, "Zespo³y");
		srodek.add(panelInstrumenty, "Instrumenty");
		
		ActionListener listenerPrzycisku = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				CardLayout cardLayout = (CardLayout) srodek.getLayout();
				//getActionCommand zwraca string który jest na przycisku
				cardLayout.show(srodek, event.getActionCommand());
			}
		};
		
		koncerty.addActionListener(listenerPrzycisku);
		zespoly.addActionListener(listenerPrzycisku);
		instrumenty.addActionListener(listenerPrzycisku);
		
		/*
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) srodek.getLayout();
				cardLayout.next(srodek);
			}
			
		});
		*/
		
		TabelaKoncertow tabelka = new TabelaKoncertow();
		panelKoncerty.add(tabelka);
		
		add(BorderLayout.CENTER, srodek);
		
	
	}

	public class TabelaKoncertow extends JPanel {
		public TabelaKoncertow(){
			super(new GridLayout(1,0));
			String[] kolumny = {"ID", "Wystêp", "Data", "Liczba osób", "Cena", "Godzina", "Nr klubu"};
			
			Object[][] object = new Object[10][7];
			
			ArrayList<Koncert> listaKoncertow = new ArrayList<Koncert>();
			listaKoncertow = DbProcessor.createKoncertyList();
			
			
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
			table.setPreferredScrollableViewportSize(new Dimension(600, 300));
			table.setFillsViewportHeight(true);
			
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane);
		}
	}
	
}

