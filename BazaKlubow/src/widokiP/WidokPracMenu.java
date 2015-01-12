package widokiP;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.ColorUIResource;











import polaczenieZbaz¹.DbProcessor;
import klasyBazodanowe.Zespol;
import customGraphics.GBC;
import customStructure.SortingImporatnce;

public class WidokPracMenu extends JPanel {

	public final static String info = "Witamy w programie";
	private static final int VerticalGapInTab=10;
	private static final int HorizontalGapIntTab=15 ;
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
		JPanel tabZespoly=createHeaderZespoly();
		List<Zespol> testowe=DbProcessor.getAllBands();
		for(Zespol z:testowe)
			createBandRow(tabZespoly, z);
		
		panelZespoly.add(tabZespoly);
		
		panelIstrumenty.setBackground(Color.BLUE);
		
		final JPanel srodek = new JPanel(new CardLayout());
		
		srodek.add(panelKoncerty, "Koncerty");
		srodek.add(panelZespoly, "Zespo³y");
		srodek.add(panelIstrumenty, "Istrumenty");
		
		//fajnie, elegancko napisane
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
	
	public static Stack<SortingImporatnce> incjujStoc(){
		Stack<SortingImporatnce> stos=new Stack<SortingImporatnce>();
		stos.add(SortingImporatnce.BIA£Y);
		stos.add(SortingImporatnce.ZIELONY);
		stos.add(SortingImporatnce.NIEBIESKI);
		stos.add(SortingImporatnce.¯Ó£TY);
		stos.add(SortingImporatnce.CZERWONY);
		return stos;
	}
	
	
	public static void createBandRow(JPanel panel,Zespol zespol)
	{
		JTextField id=new JTextField(new Integer(zespol.getID()).toString());
		JTextField nazwa=new JTextField(zespol.getNazwaZespolu());
		JTextField data=new JTextField(String.format("%tF", zespol.getDataZalozenia()));
		JButton button=new JButton("aktualizuj");
		button.setVisible(false);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		nazwa.setHorizontalAlignment(SwingConstants.CENTER);
		data.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(id, new GBC(0,GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		panel.add(nazwa,new GBC(1, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		panel.add(data,new GBC(2, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		panel.add(button,new GBC(3, GBC.RELATIVE));
	}
	
	public static JPanel createHeaderZespoly()
	{
		JPanel zespoly=new JPanel();
		zespoly.setLayout(new GridBagLayout());
		
		final JLabel nrZ = new JLabel("Nr Zespo³u");
		final JLabel naz=new JLabel("Nazwa");
		final JLabel datU=new JLabel("Data uformowania");
		
		//ustawiam obramowanie
		nrZ.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		naz.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		datU.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//wyrównanie
		nrZ.setHorizontalAlignment(SwingConstants.CENTER);
		naz.setHorizontalAlignment(SwingConstants.CENTER);
		datU.setHorizontalAlignment(SwingConstants.CENTER);
		//Opaque = true
		nrZ.setOpaque(true);
		naz.setOpaque(true);
		datU.setOpaque(true);
		
		final Color backOrginal=nrZ.getBackground();
		
		//tworzê stos z kolorkami.
		final Stack<SortingImporatnce> stos=incjujStoc();
		final HashMap<JLabel, SortingImporatnce> poznajKolor=new HashMap<JLabel, SortingImporatnce>();
		final TreeMap<SortingImporatnce, JLabel> poznajLabel=new TreeMap<SortingImporatnce, JLabel>();		
		MouseAdapter adapter=new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				//ColorUIResource backOrginal=new ColorUIResource(238,238,238);
				JLabel l= (JLabel)e.getComponent();
				if(backOrginal.equals(l.getBackground())){
					SortingImporatnce k=stos.pop();
					poznajKolor.put(l, k);
					poznajLabel.put(k, l);
					
					l.setBackground(k.kolor);
					
				}
					
				else{
					
					l.setBackground(backOrginal);
					SortingImporatnce previous=poznajKolor.remove(l);
					TreeMap<SortingImporatnce, JLabel> wieksze= new TreeMap<SortingImporatnce, JLabel>(poznajLabel.tailMap(previous, false));      					
					for(Map.Entry<SortingImporatnce,JLabel> entry:wieksze.entrySet())
					{
						JLabel curr=entry.getValue();
						poznajKolor.put(curr,previous);
						poznajLabel.remove(previous);
						poznajLabel.put(previous, curr);
						curr.setBackground(previous.kolor);
						previous=entry.getKey();
					}
					
					
					stos.push(previous);
					poznajLabel.remove(previous);
					
					
					
				}
				StringBuilder kluczdoSortowania=new StringBuilder("");
				for(JLabel label:poznajLabel.values())
				{
					kluczdoSortowania.append(label.getText());
					kluczdoSortowania.append(" ");
				}
				System.out.println(kluczdoSortowania.toString());
				
				// -wydaje sie byc nie potrzebne l.validate();
				//l.repaint();
				
			}
		}; 
		//ustawiam s³uczaczy
		nrZ.addMouseListener(adapter);
		naz.addMouseListener(adapter);
		datU.addMouseListener(adapter);
		
		
		zespoly.add(nrZ,new GBC(0,0).setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		zespoly.add(naz,new GBC(1,0).setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		zespoly.add(datU,new GBC(2,0).setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		zespoly.add(new JPanel(),new GBC(3, 0,GBC.REMAINDER,1));
		return zespoly;
	}

}
