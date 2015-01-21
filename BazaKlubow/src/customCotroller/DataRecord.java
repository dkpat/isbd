package customCotroller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import customStructure.ZmiennaWartosc;



public class DataRecord<T,Z> {
	Warstwa<T> parent;
	
	private int ID_Recordu;//czy jest mi potrzebne aby coœ wiêcej ni¿ ID, szczegolnie dla tabel 
						  //,które maj¹ zdjêcie!!!!!!!
 	                      //Nie !!!!!!!!!!!	
	
	HashMap<Integer, ZmiennaWartosc<Object>> wartoœæIndex=new HashMap<Integer, ZmiennaWartosc<Object>>();//HashMapa, w której s¹ przechowywane informacje
																										 //o tym czy zmieni³a o zmianach wartoœci (para stara wartoœæ, nowa wartoœæ)
																										//oraz o numerze kolumny w tabeli z bazy
	
	;
	JCheckBox box;
	JPanel functionBars;
	private HashMap<Integer,JTextComponent> tab;
	private Warstwa<Z> child;
	JPanel gab;
	
	public DataRecord(){
		
	}
	
	public DataRecord(Warstwa<T> p,int ID_Recordu,JCheckBox b,HashMap<Integer,JTextComponent> components,JPanel funcionBars,Warstwa<Z> child,JPanel gab) {
		parent=p;
		this.ID_Recordu=ID_Recordu;
		box=b;
		box.addItemListener(new BoxListener());
		this.functionBars=funcionBars;
		tab=components;
		this.child=child;
		this.gab=gab;
	}
	public DataRecord(Warstwa<T> p,int ID_Recordu,JCheckBox b,HashMap<Integer,JTextComponent> components,JPanel funcionBars,JPanel gab){
		parent=p;
		this.ID_Recordu=ID_Recordu;
		box=b;
		box.addItemListener(new BoxListener());
		this.functionBars=funcionBars;
		tab=components;
		this.child=null;
		this.gab=gab;
	}
	public void setAllWithoutChild(Warstwa<T> p,int ID_Recordu,JCheckBox b,HashMap<Integer,JTextComponent> components,JPanel funcionBars,JPanel gab){
		parent=p;
		this.ID_Recordu=ID_Recordu;
		box=b;
		box.addItemListener(new BoxListener());
		this.functionBars=funcionBars;
		tab=components;
		this.child=null;
		this.gab=gab;
	}
	public void setAll(Warstwa<T> p,int ID_Recordu,JCheckBox b,HashMap<Integer,JTextComponent> components,JPanel funcionBars,Warstwa<Z> child,JPanel gab){
		parent=p;
		this.ID_Recordu=ID_Recordu;
		box=b;
		box.addItemListener(new BoxListener());
		this.functionBars=funcionBars;
		tab=components;
		this.child=child;
		this.gab=gab;
	}
	public HashMap<Integer,JTextComponent> getComponents() {
		return tab;
	}
	public JCheckBox getCheckBox() {
		return box;
	}
	
	public JPanel getFunctionBars() {
		return functionBars;
	}
	public JPanel getGap() {
		return gab;
	}
	public int getID_Recordu(){
		return ID_Recordu;
	}
	
	public Warstwa<Z> getChild()
	{
		return child;
	}
	
	public void dodajDoNowyWpisDoMapyWartosci(int Index,ZmiennaWartosc<Object> value) {
		wartoœæIndex.put(Index, value);
	}
	
	public void uaktualnijWpisWMapieWartosci(int Index,Object newValue) {
		
		System.out.println(Index);
		
		wartoœæIndex.get(Index).setNewValue(newValue);
	}
	
	public void ustwaWidocznoœæCheckBoxa(boolean visibility)
	{
		box.setEnabled(visibility);
	}
	
	class BoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			int stanZanaczenia =e.getStateChange();
			DataRecord<T, Z> record=DataRecord.this;
			if(stanZanaczenia==ItemEvent.SELECTED)
			{
				parent.dodajDoListyUsuwanych(record);
				parent.ustawWidzialnoœæPrzyiskuDoUsuwania();
			}
			else
			{
				parent.usunZListyDoUsuniecia(record);
				parent.ustawWidzialnoœæPrzyiskuDoUsuwania();
			}
			
		}

		
		
	}
	
	public void setVisibelForFunctionBar(){
		
		
		Iterator<ZmiennaWartosc<Object>> it= wartoœæIndex.values().iterator();
		
		boolean czyUnikalny=false;
		while(it.hasNext()&&!czyUnikalny){
			ZmiennaWartosc<Object> z=it.next();
			czyUnikalny=z.czyRzczywiscieMamNowaWartosc();
		}
		functionBars.setVisible(czyUnikalny);
		
		
	}
	
	

}
	

