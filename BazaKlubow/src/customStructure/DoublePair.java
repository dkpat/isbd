package customStructure;

import java.awt.Checkbox;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class DoublePair {

	private JCheckBox box;
	private HashMap<Integer, JTextComponent> components;
	private JPanel funcionButton;
	JPanel gab;
	public DoublePair(JCheckBox box,HashMap<Integer, JTextComponent> components,JPanel funcionButton,JPanel gab) {
		
		this.box=box;
		this.components=components;
		this.funcionButton=funcionButton;
		this.gab=gab;
	}

	public JCheckBox getBox()
	{
		return box;
	}
	public HashMap<Integer, JTextComponent>  getMapOFComponents()
	{
		return components;
	}
	public JPanel getFuncionButtons()
	{
		return funcionButton;
	}
	public JPanel getGap() {
		return gab;
	}
}
