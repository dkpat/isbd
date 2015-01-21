package customCotroller;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import klasyBazodanowe.Instrument;
import klasyBazodanowe.Muzyk;
import widokiP.WidokPracMenu;
import customGraphics.SortButton;
import customStructure.RodzajKomponentu;
import customStructure.SortingImporatnce;

public abstract class Warstwa<T> {
	protected int ID_overridingObject;
	protected JPanel graphicsRepresentation;
	protected JComboBox<String> comboBox;
	protected JButton deletButton;
	protected JPanel footerGab=new JPanel();
//	protected boolean highestLevel=true; //- po co skoro mogê sprawdzaæ czy ma ojca, okazuje siê ¿ê wcale nie 
	private final static String DELET_INFO="usuñ";
	HashSet toDelete=new HashSet();
	
	
	
	
	protected Warstwa<T>.SortAction sorter=new Warstwa.SortAction(); //s³uchacz akcji dla labeli nag³ówków
	protected Warstwa<T>.SortStrzalka strzalkowy=new Warstwa.SortStrzalka(); //s³uchacz akcji dla strza³ek
	
	///Dane dla sluchacza
	private Stack<SortingImporatnce> stos;
	private HashMap<JLabel, SortingImporatnce> poznajKolor;
	private TreeMap<SortingImporatnce, JLabel> poznajLabel;
	
	
	
	
	RodzajKomponentu typ;
	/**
	 * 
	 * @param main - Panel, na którym ma byæ skontruowana graficzna 
	 * reprezentacja danych
	 * @param ID - je¿eli nie jest to górna warstwa,
	 * np Muzycy(cz³onkowie zespo³u) bêd¹cy pierwsz¹
	 * podwarstw¹ Zespolu, to klucz rekordu wy¿szego 
	 * do którego przynale¿¹
	 * -je¿eli jest to najwy¿sza warstwa podaj liczbê ujemn¹ 
	 */
	public Warstwa(JPanel main,int ID) {
		graphicsRepresentation=main;
		ID_overridingObject=ID;
		footerGab.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		deletButton=new JButton(DELET_INFO);
	}

	

	
	/**
	 * To tutaj jest wywo³ywany ciag metod
	 * potrzebnych do zape³nienia panelu danymi
	 * z dabeli, najczeœciej s¹ to metody:
	 * prepareHeader, prepareDataRow,prepareFooter
	 */
	public abstract void initializeGraphicsRepresentation();
	
	
	/**
	 * poœrednicz¹ca w pobieraniu dabych z bazy
	 * @param Condision - w za³ozeniu mia³o byæ tak, ¿e tutej 
	 * odbywa siê parsowanie stringa, na konretne zapytanie sql
	 *  ,który zostanie przes³any do metody klasy DbProcessor,
	 *  tam nastêpuje ju¿ bezpoœrenie po³¹czenie siê z baz¹
	 * @return
	 */
	public abstract List<T> dowlandDataFromDataBase(String Condision);
	
	/**
	 * Czyœci listê przechowuj¹c¹ dane z tabel
	 * wyœwietlanych na ekranie, które sa przechowywane za pomoc¹
	 * klasy DataRecord
	 */
	public abstract  void deleteDataRecordFromList();
	
	/**
	 * 
	 * @param header JPanel do, którego zostanie
	 * dodany nag³ówek, opisuj¹cy tabele
	 * oraz same rekordy z tabeli
	 * Nadpisuj¹c metodê nale¿y przgotwaæ,
	 * JLabele, które maj¹, byæ umieszczone
	 * w nag³ówku, a nastêpnie wywo³aæ metodê
	 * WidokPracMenu.createHeader
	 */
	public abstract void prepareHeader(JPanel header);
	
	
	/**
	 * Przygotowuje stopkê JPanelu.
	 * Nadpisuj¹c nale¿y podaæ
	 * dane jakimi ma zapelniæ siê
	 * JcomoboBox, zazwyczaj bêd¹ to dane z
	 * bazy danych, albo tylko sama opcja
	 * stworzenia nowego rekordu w bazie 
	 */
	public abstract void prepareFooter();
	
	/**
	 * Usuwa wszelk¹ zawartoœæ z Panela
	 */
	public  void clearGraphicsRepresentation()
	{
		graphicsRepresentation.removeAll();
		deleteDataRecordFromList();
		
	}
	
	/**
	 * Usuwa graficzn¹ reprezentacje danych, oraz
	 * stopkê, bêdzie przydatne przy sortowaniu,
	 * gdzie, do takiego wypatroszonego panelu, 
	 * bêdzie mo¿na dodaæ ponownie pobrane,
	 * ale ju¿ na przyklad posortowane dane
	 */
	public abstract void remoAllDataRows();
	
	/**
	 * Usuwa stopkê z panelu
	 */
	public void removeFooter()
	{
		graphicsRepresentation.remove(comboBox);
		graphicsRepresentation.remove(deletButton);
		graphicsRepresentation.remove(footerGab);
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
	/*
	public boolean isHighestLevel()
	{
		return highestLevel;
	}
	public void setHighestLevel (boolean highestLevel) {
		this.highestLevel=highestLevel;
	}
	*/
	
	/**
	 * 
	 * @return zwaraca panel, a ktorym
	 * znajduje siê  
	 * reprezentacja fragmentu danych
	 * zapisaych w bazie danych 
	 */
	
	
	
	public JPanel getGraphicsRepresentation()
	{
		return graphicsRepresentation;
	}
	
	public abstract String utwórzZapytanieSortuj¹ce(String kluczSortujacy);
	
	public abstract String dajNazweNaglowkaKolumny(String tekst);
	public abstract void wykonajSortowanie(String kluczSortowania);
	
	public void usunZListyDoUsuniecia(DataRecord record){
		toDelete.remove(record);
	}
	
	
	public void dodajDoListyUsuwanych(DataRecord record){
		toDelete.add(record);
	}
	public void ustawWidzialnoœæPrzyiskuDoUsuwania(){
		boolean visibility=!toDelete.isEmpty();
		deletButton.setVisible(visibility);
	}
	public abstract int ileMamRekordów();
	
	
	class SortAction implements MouseListener {

	
		private Color orginal=new Color(238, 238, 238);
		public SortAction() {
			stos=incjujStoc();
			poznajKolor=new HashMap<JLabel, SortingImporatnce>();
			poznajLabel=new TreeMap<SortingImporatnce, JLabel>();
		}
		
		@Override
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
					Warstwa.this.wykonajSortowanie(kluczdoSortowania.toString());
				// -wydaje sie byc nie potrzebne l.validate();
				// l.repaint();
			}// koniec if(com instance JLabel)
			else {
				System.out.println("Obiekt nie jest JLabelem");
			}

		}// Koniec metody OnClick
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	
	}
	
	class SortStrzalka implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
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
			Warstwa.this.wykonajSortowanie(kluczdoSortowania.toString());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}






