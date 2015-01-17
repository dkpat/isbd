package customCotroller;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JPanel;



public class DataRecord<T,Z> {
	Warstwa<T> parent;
	
	private int ID_Recordu;//czy jest mi potrzebne aby coœ wiêcej ni¿ ID, szczegolnie dla tabel 
						  //,które maj¹ zdjêcie!!!!!!!
						  //Nie !!!!!!!!!!!	
	
	JCheckBox box;
	JPanel functionBars;
	private Component[] tab;
	private Warstwa<Z> child;
	JPanel gab;
	
	public DataRecord(Warstwa<T> p,int ID_Recordu,JCheckBox b,Component[] components,JPanel funcionBars,Warstwa<Z> child,JPanel gab) {
		parent=p;
		this.ID_Recordu=ID_Recordu;
		box=b;
		this.functionBars=funcionBars;
		tab=components;
		this.child=child;
		this.gab=gab;
	}
	public DataRecord(Warstwa<T> p,int ID_Recordu,JCheckBox b,Component[] components,JPanel funcionBars,JPanel gab){
		new DataRecord<T,Z>(p,ID_Recordu,b,components,funcionBars,null,gab);
	}
	public Component[] getComponents() {
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
	
	
	

}

