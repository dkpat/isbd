package customStructure;

import java.awt.Checkbox;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class DoublePair {

	private JCheckBox box;
	private Component[] components;
	private JPanel funcionButton;
	JPanel gab;
	public DoublePair(JCheckBox box,Component[] components,JPanel funcionButton,JPanel gab) {
		
		this.box=box;
		this.components=components;
		this.funcionButton=funcionButton;
		this.gab=gab;
	}

	public JCheckBox getBox()
	{
		return box;
	}
	public Component[] getTabOFComponents()
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
