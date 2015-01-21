package widokiP;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import customCotroller.BandLayer;
import customCotroller.DataRecord;
import customCotroller.Warstwa;
import customGraphics.*;
import polaczenieZbaz¹.DbProcessor;
import klasyBazodanowe.Muzyk;
import customStructure.DoublePair;
import customGraphics.GBC;
import customStructure.DoublePair;
import customStructure.RodzajKomponentu;
import customStructure.RodzajTabeli;
import customStructure.SortingImporatnce;
import customStructure.ZmiennaWartosc;

public class WidokPracMenu extends JPanel {

	public final static String info = "Witamy w programie";
	private static final int VerticalGapInTab = 10;
	private static final int HorizontalGapIntTab = 15;

	
	
	
	public WidokPracMenu() {

		setLayout(new BorderLayout());
		JTextField t1 = new JTextField(info);
		add(BorderLayout.NORTH, t1);

		// boczny panel do lewego menu
		JPanel bocznyL = new JPanel();
		bocznyL.setLayout(new GridLayout(0, 1, 0, 10));

		// stworz przyciski do lewego menu
		JButton koncerty = new JButton("Koncerty");
		JButton zespoly = new JButton("Zespo³y");
		JButton istrumenty = new JButton("Istrumenty");

		// Testowy przycisk do przechodzenia miêdzy kartami
		// JButton next = new JButton("Next");

		bocznyL.add(koncerty);
		bocznyL.add(zespoly);
		bocznyL.add(istrumenty);
		// bocznyL.add(next);

		// panel pomocniczy w którym bêd¹ znajdowaæ siê przyciski
		JPanel pomocL = new JPanel();
		pomocL.setLayout(new FlowLayout());
		pomocL.setBorder(BorderFactory.createLineBorder(Color.RED));

		pomocL.add(bocznyL);
		add(BorderLayout.WEST, pomocL);

		// panele œrodkowe
		JPanel panelKoncerty = new JPanel();
		JPanel panelZespoly = new JPanel();
		JPanel panelIstrumenty = new JPanel();

		/*
		 * ustawi³em im tymczasowo ró¿ne kolorki ¿eby ³atwiej je identyfikowaæ,
		 * bo nic w nich nie ma jeszcze
		 */
		panelKoncerty.setBackground(Color.CYAN);
		panelZespoly.setBackground(Color.GRAY);

		
		
		//Uwaga!!!!!!! To powinno byæ gdzieœ indziej - tworzê poszczególne rekordy zespo³ów
				/*
		header.setLayout(new GridBagLayout());
		JLabel nrZ = new JLabel("Nr Zespo³u");
		JLabel naz = new JLabel("Nazwa");
		JLabel datU = new JLabel("Data uformowania");
		JLabel[] tab = { nrZ, naz, datU };

		createHeader(header,tab,0,0);
		
		
		
		List<Zespol> testowe = DbProcessor.getAllBands();
		for (Zespol z : testowe)
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
			createDataRow(header,species,data,tabela);
		}

		panelZespoly.add(header);
		*/
		//Zamiast tego nowe u góry nowy kody
		JPanel gridZespoly=new JPanel();
		gridZespoly.setLayout(new GridBagLayout());
		BandLayer warstwaNadrz=new BandLayer(gridZespoly,-1);
		warstwaNadrz.initializeGraphicsRepresentation();
		panelZespoly.add(gridZespoly);
		
		
		
		
		panelIstrumenty.setBackground(Color.BLUE);

		final JPanel srodek = new JPanel(new CardLayout());

		srodek.add(panelKoncerty, "Koncerty");
		srodek.add(panelZespoly, "Zespo³y");
		srodek.add(panelIstrumenty, "Istrumenty");

		// fajnie, elegancko napisane
		ActionListener listenerPrzycisku = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				CardLayout cardLayout = (CardLayout) srodek.getLayout();
				// getActionCommand zwraca string który jest na przycisku
				cardLayout.show(srodek, event.getActionCommand());
			}
		};

		koncerty.addActionListener(listenerPrzycisku);
		zespoly.addActionListener(listenerPrzycisku);
		istrumenty.addActionListener(listenerPrzycisku);

		/*
		 * next.addActionListener(new ActionListener(){
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { CardLayout
		 * cardLayout = (CardLayout) srodek.getLayout();
		 * cardLayout.next(srodek); }
		 * 
		 * });
		 */

		add(BorderLayout.CENTER, srodek);

	}

	public static Stack<SortingImporatnce> incjujStoc() {
		Stack<SortingImporatnce> stos = new Stack<SortingImporatnce>();
		stos.add(SortingImporatnce.BIA£Y);
		stos.add(SortingImporatnce.ZIELONY);
		stos.add(SortingImporatnce.NIEBIESKI);
		stos.add(SortingImporatnce.¯Ó£TY);
		stos.add(SortingImporatnce.CZERWONY);
		return stos;
	}

	public static <T,Z>DoublePair createDataRow(JPanel panel,final Warstwa<T> warstwaDecyzyjna,final DataRecord<T, Z> row,RodzajKomponentu[] species,final int[] indexs,Object[] data,JPanel child,RodzajTabeli tab) { //dodaj warstwê jako ayrgument
		//Tablica komponentów do póŸniejszego zwrócenia
		Component[] components=new Component[data.length+1];
		
		
		
		//Tworze przyciski do anulowania i cofania
		JButton update = new JButton("aktualizuj");
		JButton cancel = new JButton("cofnij");
		final JPanel funcionButtons=new JPanel();
		funcionButtons.setVisible(false);
		funcionButtons.add(update);
		funcionButtons.add(cancel);
		//tworzê check ox
		JCheckBox box=new JCheckBox();
		panel.add(box, new GBC(GBC.RELATIVE, GBC.RELATIVE).setAnchor(GBC.EAST));
		
		final HashMap<Integer, Integer> mapaHashCodeIndex=new HashMap<Integer, Integer>(); //tworzy mapê gdzie klucze to hash kody graficznych komponentów a wartoœæ to nr kolumn	
		
		HashMap<Integer, JTextComponent> mapaIndexComponent=new HashMap<Integer, JTextComponent>(); //tworzy mapê gdzie klucze to nrKolumn a wartoœæ to graficzne reprezentacja komponentów		
		
		for(int i=0;i<species.length;i++)
		{
			switch (species[i]) {
			case EDITABLE_TEXT:
				String text=(String)data[i];
				JTextField editableT=new JTextField(text);
				editableT.setHorizontalAlignment(SwingConstants.CENTER);
				mapaIndexComponent.put(indexs[i], editableT);
				row.dodajDoNowyWpisDoMapyWartosci(indexs[i], new ZmiennaWartosc<Object>(text));
				mapaHashCodeIndex.put(editableT.hashCode(), indexs[i]);
				Document docEditable_Text=editableT.getDocument();
				
					docEditable_Text.addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						funcionButtons.setVisible(true);
						
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						funcionButtons.setVisible(true);
						
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						funcionButtons.setVisible(true);
						
					}
				});
			
				panel.add(editableT, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				components[i]=editableT;
				break;
				
			case IMAGE:
				String imageD=String.format("%.20s",data[i].toString());
				JTextField unImgDisc=new JTextField(imageD);
				unImgDisc.setHorizontalAlignment(SwingConstants.CENTER);
				unImgDisc.setEditable(false);
				panel.add(unImgDisc, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				mapaHashCodeIndex.put(unImgDisc.hashCode(), indexs[i]);
				mapaIndexComponent.put(indexs[i], unImgDisc);
				row.dodajDoNowyWpisDoMapyWartosci(indexs[i], new ZmiennaWartosc<Object>(String.format("%.20s",data[i].toString())));
				//components[i]=unImgDisc;
				
				
				
				break;
				
				
				
			case DATE_ONLY_YEAR:
				Timestamp date=(Timestamp)data[i];
				JFormattedTextField dateText=new JFormattedTextField(String.format("%tY", date));
				dateText.setHorizontalAlignment(SwingConstants.CENTER);
				mapaHashCodeIndex.put(dateText.hashCode(), indexs[i]);
				mapaIndexComponent.put(indexs[i], dateText);
				row.dodajDoNowyWpisDoMapyWartosci(indexs[i], new ZmiennaWartosc<Object>((String.format("%tY", date))));
				Document docDateOnlyYear=dateText.getDocument();
		
				
				
				dateText.addPropertyChangeListener("value", new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						String old=(String) evt.getOldValue();
						String current=(String) evt.getNewValue();
						if(!old.equals(current))
						{	
							row.uaktualnijWpisWMapieWartosci(mapaHashCodeIndex.get(evt.getSource().hashCode()), current);
							System.out.println("pracuje");
							System.out.println(row);
							
						}
						
						
					}
				});
				
				panel.add(dateText, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				components[i]=dateText;
				
				break;
			case DATE_DD_MM_YYYY:
				Timestamp dateClasic=(Timestamp)data[i];
				JFormattedTextField dateTextClassic=new JFormattedTextField(String.format("%tF", dateClasic));
				dateTextClassic.setHorizontalAlignment(SwingConstants.CENTER);
				row.dodajDoNowyWpisDoMapyWartosci(indexs[i], new ZmiennaWartosc<Object>(String.format("%tF", dateClasic)));
				
				mapaIndexComponent.put(indexs[i], dateTextClassic);
				mapaHashCodeIndex.put(dateTextClassic.hashCode(), indexs[i]);
				
				
				//	Document docDateOnlyYear=dateText.getDocument();
		
				
				
				dateTextClassic.addPropertyChangeListener("value", new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						String old=(String) evt.getOldValue();
						String current=(String) evt.getNewValue();
						if(!old.equals(current))
						{
							row.uaktualnijWpisWMapieWartosci(mapaHashCodeIndex.get(evt.getSource().hashCode()), current);
							row.setVisibelForFunctionBar();
							System.out.println("pracuje");
							System.out.println(row);
						}
						
						
					}
				});
				
				panel.add(dateTextClassic, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				components[i]=dateTextClassic;
				
				break;
			case UNEDITABLE_INT:
				int uneditableInt=(int)data[i];
				JTextField uneditableIntText=new JFormattedTextField(String.format("%d", uneditableInt));
				uneditableIntText.setEditable(false);
				uneditableIntText.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(uneditableIntText, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				mapaIndexComponent.put(indexs[i],uneditableIntText);
				
				
				//components[i]=uneditableIntText;
				break;
				
			case UNEDITABLE_TEXT:
				String uneditableText=(String)data[i];
				JTextField uneditablTextField=new JFormattedTextField(String.format("%s", uneditableText));
				uneditablTextField.setEditable(false);
				uneditablTextField.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(uneditablTextField, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				mapaIndexComponent.put(indexs[i], uneditablTextField);
				
				
				//components[i]=uneditablTextField;
				
				
				
				break;
			

			default:
				break;
			}
		}
		panel.add(funcionButtons, new GBC(GBC.RELATIVE, GBC.RELATIVE).setAnchor(GBC.WEST));
		JPanel luka=new JPanel();
		luka.setBorder(BorderFactory.createLineBorder(Color.blue));
		panel.add(luka, new GBC(GBC.RELATIVE, GBC.RELATIVE,GBC.REMAINDER,1).setFill(GBC.BOTH));
		components[components.length-1]=luka;
		if(child!=null){
			System.out.println("Dodaje dziecko");
			panel.add(child, new GBC(GBC.RELATIVE,GBC.RELATIVE,GBC.REMAINDER,1).setInsets(5, 50, 5, 0));
		}
		System.out.println("box to "+box+"!!!!!!!!!!!!!!!!!!!");
		return new DoublePair(box, mapaIndexComponent, funcionButtons,luka);
		/*
		switch (tab) {
		case ZESPO£Y:
			JPanel listOFMembers=createListOfMembers(tab.ID);
			panel.add(listOFMembers, new GBC(0, GBC.RELATIVE,GBC.REMAINDER,1).setInsets(5,30, 15, 0));
			break;

		default:
			break;
		}
		*/
		
		
		
		
		/*
		
		JTextField id = new JTextField(new Integer(zespol.getID()).toString());
		JTextField nazwa = new JTextField(zespol.getNazwaZespolu());
		JTextField data = new JTextField(String.format("%tF",
				zespol.getDataZalozenia()));
		
		
		id.setHorizontalAlignment(SwingConstants.CENTER);
		nazwa.setHorizontalAlignment(SwingConstants.CENTER);
		data.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(id, new GBC(0, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
				.setFill(GBC.HORIZONTAL));
		panel.add(nazwa,
				new GBC(1, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
		panel.add(data, new GBC(2, GBC.RELATIVE)
				.setIpad(HorizontalGapIntTab, 0).setFill(GBC.HORIZONTAL));
		*/
		
		
		
		//A tutaj tworzê nag³ówek gotowy przyj¹æ listê cz³onków,
		
		
	
	}

	public static <T>List<JPanel> createHeader(JPanel header,HashSet<JLabel> nieSortowalne,final Warstwa<T> warstwaDecyzyjna,final JLabel[] tab,int xStart,int yStart) {
		
			
		
		List<JPanel> sortGroups = new ArrayList<JPanel>(tab.length-((nieSortowalne!=null) ? nieSortowalne.size():0));
		SortButton[] strzalki=new SortButton[tab.length];
		final Color orginal = tab[0].getBackground();
		System.out.println(orginal);
		// ca³a d³uga i skompikowana operacja ustawiania ró¿nicowania sortowania

		final Stack<SortingImporatnce> stos = incjujStoc();
		final HashMap<JLabel, SortingImporatnce> poznajKolor = new HashMap<JLabel, SortingImporatnce>();
		final TreeMap<SortingImporatnce, JLabel> poznajLabel = new TreeMap<SortingImporatnce, JLabel>();

		MouseAdapter adapter = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// ColorUIResource backOrginal=new ColorUIResource(238,238,238);
				Component com = e.getComponent();
				if (com instanceof JLabel) {
					JLabel l = (JLabel) com;
					com=l.getParent().getComponent(1);
					SortButton sortButt=(SortButton)com;
					if (orginal.equals(l.getBackground())) {
						SortingImporatnce k = stos.pop();
						poznajKolor.put(l, k);
						poznajLabel.put(k, l);
						sortButt.setVisible(true);
						l.setBackground(k.kolor);
					
					}

					else {
						sortButt.setVisible(false);
						l.setBackground(orginal);
						sortButt.isAscent(true);
						
						
						SortingImporatnce previous = poznajKolor.remove(l);
						TreeMap<SortingImporatnce, JLabel> wieksze = new TreeMap<SortingImporatnce, JLabel>(
								poznajLabel.tailMap(previous, false));
						for (Map.Entry<SortingImporatnce, JLabel> entry : wieksze
								.entrySet()) {
							JLabel curr = entry.getValue();
							poznajKolor.put(curr, previous);
							poznajLabel.remove(previous);
							poznajLabel.put(previous, curr);
							curr.setBackground(previous.kolor);
							previous = entry.getKey();
						}

						stos.push(previous);
						poznajLabel.remove(previous);

					}
					StringBuilder kluczdoSortowania = null;
					for (JLabel label : poznajLabel.values()) {
						
						if(kluczdoSortowania==null)
						{
						kluczdoSortowania=new StringBuilder();	
							
						}
						else{
							kluczdoSortowania.append(",");
						}
							
							kluczdoSortowania.append(label.getText());
							com=label.getParent().getComponent(1);
							if(com instanceof SortButton)
							{
								sortButt=(SortButton)com;
								kluczdoSortowania.append("-");
								if(sortButt.getAscent())
									kluczdoSortowania.append("ASCENT");
								else
									kluczdoSortowania.append("DESCENT");
						}
					}
					//System.out.println(kluczdoSortowania.toString());
					
					System.out.println(kluczdoSortowania);
					if(kluczdoSortowania!=null)
						warstwaDecyzyjna.wykonajSortowanie(kluczdoSortowania.toString());
					// -wydaje sie byc nie potrzebne l.validate();
					// l.repaint();
				}// koniec if(com instance JLabel)
				else {
					System.out.println("Obiekt nie jest JLabelem");
				}

			}// Koniec metody OnClick
		};// Koniec klasy mouse adapter;
		
		
		MouseAdapter dlaStrza³ki=new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Component com=e.getComponent();
				if(com instanceof SortButton)
				{
					SortButton strzalka=(SortButton)com;
					boolean current=strzalka.getAscent();
					strzalka.isAscent(!current);
					strzalka.repaint();
				}
				StringBuilder kluczdoSortowania = null;
				for (JLabel label : poznajLabel.values()) {
					
					if(kluczdoSortowania==null)
					{
					kluczdoSortowania=new StringBuilder();	
						
					}
					else{
						kluczdoSortowania.append(",");
					}
						
						kluczdoSortowania.append(label.getText());
						com=label.getParent().getComponent(1);
						if(com instanceof SortButton)
						{
							SortButton sortButt=(SortButton)com;
							kluczdoSortowania.append("-");
							if(sortButt.getAscent())
								kluczdoSortowania.append("ASCENT");
							else
								kluczdoSortowania.append("DESCENT");
					}
				}
				System.out.println(kluczdoSortowania.toString());
				warstwaDecyzyjna.wykonajSortowanie(kluczdoSortowania.toString());
			}
		};
			// koniec skomplikwanych operacji zwi¹zanych z sortowaniem
		
		

		for (JLabel label : tab) {

			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));// obramowanie
			label.setHorizontalAlignment(SwingConstants.CENTER);// wyrównanie
			label.setOpaque(true); // w³aœciwoœc ustawiona by umo¿liwiæ zmianê
								   // t³a
			
		}

		

		for (int i = 0; i < tab.length; i++) {
			
			
			if(nieSortowalne==null||!nieSortowalne.contains(tab[i]))
				{
				JPanel sortGroup = new JPanel();
				sortGroups.add(sortGroup);// tutaj to jest lista na JPanele nag³ówkowe
				sortGroup.add(tab[i]);// a tutaj to ju¿ sam w³aœciwy JPanel (panie widzisz tam gdzieœ s )
				
				SortButton sButt=new SortButton(tab[i].getPreferredSize().height);
				strzalki[i]=sButt;
				sButt.setVisible(false);
				sortGroup.add(sButt);
				//sButt.addMouseListener(dlaStrza³ki);
				//tab[i].addMouseListener(adapter);//tutaj by³ nadawany s³uchaæ zdarzeñ dla lebala nag³ówkowego w starej wersji
			// sortGroup.addMouseListener(adapter);
			
			
			//do panelu nag³ówkowego dodaje poszczegolne Labele
		
				header.add(sortGroup, new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
				}
			else{
				header.add(tab[i], new GBC(GBC.RELATIVE, GBC.RELATIVE).setIpad(HorizontalGapIntTab, 0)
						.setFill(GBC.HORIZONTAL));
			}
			
			
			

		}
		
		
		//Dodaje pusty Panel do ostaniej komorki wierszu
	
		return sortGroups;													 //Taka sztuczka, ¿eby przygotowaæ siê na szerokie "dzieci-lista cz³onków"
																			 //byc mo¿e zbêdne, do sprawdzenia w przysz³oœci	
	}

	
	public static void doajPust¹Przesztrzeñ(JPanel header) {
		JPanel pom=new JPanel();
		pom.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		header.add(pom, new GBC(GBC.RELATIVE, GBC.RELATIVE, GBC.REMAINDER, 1).setFill(GBC.HORIZONTAL));
	}

	public static void createFooter(JPanel panel,JComponent comboBox,JButton deletButton,JPanel gap ,int HeaderCount) {
		//deletButton.setVisible(false);
		panel.add(comboBox, new GBC(1,GBC.RELATIVE,HeaderCount,1).setFill(GBC.BOTH));
		
		//pom.setBorder(BorderFactory.createLineBorder(Color.red));
		panel.add(gap, new GBC(GBC.RELATIVE,GBC.RELATIVE,GBC.REMAINDER,1).setFill(GBC.BOTH));
		panel.add(deletButton,new GBC(1, GBC.RELATIVE,HeaderCount,1).setAnchor(GBC.EAST).setInsets(5, 0, 0, 0));
		
		
	}
	
	
	public static void createMusicRow()
	{
		
	}
}















/*	public static JPanel createListOfMembers(int BandID){
//najpierw trzeba utworzyæ nag³ówek + label tytu³owy 
JPanel listOFMembers=new JPanel();
listOFMembers.setBorder(BorderFactory.createLineBorder(Color.red));
listOFMembers.setLayout(new GridBagLayout());
JLabel title= new JLabel("cz³onkowie:");

//towrzê tablice labeli nag³ówkowych
		JLabel nazwisko=new JLabel("Nazwisko");
		JLabel imiê=new JLabel("Imiê");
		JLabel dataU=new JLabel("Data Urodzenia");
		JLabel dataS=new JLabel("Data Œmierci");
		JLabel dataD=new JLabel("Do³¹czy³");
		JLabel dataO=new JLabel("Odszed³");
		JLabel[] tab={imiê,nazwisko,dataU,dataS,dataD,dataO};
		

//dodaje tytu³ i pust¹ komórkê
title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
listOFMembers.add(title, new GBC(0, 0));
JPanel pom=new JPanel();
pom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
listOFMembers.add(pom, new GBC(0,1,1,GBC.REMAINDER).setFill(GBC.HORIZONTAL));

//Uruchamiam procedure dodawania labeli naglowkowych
createHeader(listOFMembers, tab,1,1);

List<Muzyk>members=DbProcessor.createMusicianList(BandID);




return listOFMembers;
}


*/
