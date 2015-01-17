package customStructure;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class PairFooter {

	JComboBox<String> comboBox;
	JButton deletButt;
	public PairFooter(JComboBox<String> comboBox,JButton deletButt) {
		this.comboBox=comboBox;
		this.deletButt=deletButt;
	}
	public JComboBox< String> getComboBox()
	{
		return comboBox;
	}
	public JButton getDeletButton()
	{
		return deletButt;
	}
	

}
