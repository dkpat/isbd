import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class WidokPracMenu extends JPanel {

	public final static String info="Witamy w programie"; 
	private DbProcessor processor;
	public WidokPracMenu(DbProcessor proc)
	{
		processor=proc;
	 setLayout(new BorderLayout());
	 JTextField t1=new JTextField(info);
	 add(BorderLayout.NORTH,t1);
	 JButton b1=new JButton("Dzia³aj");
	 b1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			CardLayout c =(CardLayout)WidokPracMenu.this.getParent().getLayout();
			c.show(getParent(), "Widok1");
			
		}
	});
	 add(BorderLayout.SOUTH,b1);
	}
}
