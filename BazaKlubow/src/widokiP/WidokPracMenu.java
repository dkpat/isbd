package widokiP;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import polaczenieZbaz�.DbProcessor;
import tabele.TabelaInstrumentow;
import tabele.TabelaKoncertow;

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
		JButton zespoly = new JButton("Zespo�y");
		JButton muzycy = new JButton("Muzycy");
		JButton instrumenty = new JButton("Instrumenty");
		
		
		//Testowy przycisk do przechodzenia mi�dzy kartami
		//JButton next = new JButton("Next");
		
		bocznyL.add(koncerty);
		bocznyL.add(zespoly);
		bocznyL.add(muzycy);
		bocznyL.add(instrumenty);
		//bocznyL.add(next);
		
		//panel pomocniczy w kt�rym b�d� znajdowa� si� przyciski
		JPanel pomocL = new JPanel();
		pomocL.setLayout(new FlowLayout());
		pomocL.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		pomocL.add(bocznyL);
		add(BorderLayout.WEST,pomocL);
		
		
		//panele �rodkowe
		JPanel panelKoncerty = new JPanel();
		JPanel panelZespoly = new JPanel();
		JPanel panelInstrumenty = new JPanel();
		JPanel panelMuzycy = new JPanel();
		
		/*
		 * ustawi�em im tymczasowo r�ne kolorki �eby �atwiej je identyfikowa�,
		 * bo nic w nich nie ma jeszcze 
		 */
		panelKoncerty.setBackground(Color.GRAY);
		panelZespoly.setBackground(Color.CYAN);
		panelMuzycy.setBackground(Color.GREEN);
		panelInstrumenty.setBackground(Color.BLUE);
		
		final JPanel srodek = new JPanel(new CardLayout());
		
		srodek.add(panelKoncerty, "Koncerty");
		srodek.add(panelZespoly, "Zespo�y");
		srodek.add(panelMuzycy, "Muzycy");
		srodek.add(panelInstrumenty, "Instrumenty");
		
		ActionListener listenerPrzycisku = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				CardLayout cardLayout = (CardLayout) srodek.getLayout();
				//getActionCommand zwraca string kt�ry jest na przycisku
				cardLayout.show(srodek, event.getActionCommand());
			}
		};
		
		koncerty.addActionListener(listenerPrzycisku);
		zespoly.addActionListener(listenerPrzycisku);
		instrumenty.addActionListener(listenerPrzycisku);
		muzycy.addActionListener(listenerPrzycisku);
		
		/*
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) srodek.getLayout();
				cardLayout.next(srodek);
			}
			
		});
		*/
		
		TabelaKoncertow tabelka = new TabelaKoncertow(650,400);
		panelKoncerty.add(tabelka);
		
		TabelaInstrumentow tabI = new TabelaInstrumentow(650,400);
		panelInstrumenty.add(tabI);
		
		
		add(BorderLayout.CENTER, srodek);
		
	
	}

}

