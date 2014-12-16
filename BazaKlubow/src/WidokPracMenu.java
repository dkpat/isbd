import java.awt.BorderLayout;
import java.awt.Button;
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
import javax.swing.JTextPane;

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
		JButton istrumenty = new JButton("Istrumenty");
		
		//Testowy przycisk do przechodzenia miêdzy kartami
		//JButton next = new JButton("Next");
		
		bocznyL.add(koncerty);
		bocznyL.add(zespoly);
		bocznyL.add(istrumenty);
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
		JPanel panelIstrumenty = new JPanel();
		
		/*
		 * ustawi³em im tymczasowo ró¿ne kolorki ¿eby ³atwiej je identyfikowaæ,
		 * bo nic w nich nie ma jeszcze 
		 */
		panelKoncerty.setBackground(Color.CYAN);
		panelZespoly.setBackground(Color.GRAY);
		panelIstrumenty.setBackground(Color.BLUE);
		
		final JPanel srodek = new JPanel(new CardLayout());
		
		srodek.add(panelKoncerty, "Koncerty");
		srodek.add(panelZespoly, "Zespo³y");
		srodek.add(panelIstrumenty, "Istrumenty");
		
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
		istrumenty.addActionListener(listenerPrzycisku);
		
		/*
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) srodek.getLayout();
				cardLayout.next(srodek);
			}
			
		});
		*/
		
		add(BorderLayout.CENTER, srodek);
		
	
	}

}
