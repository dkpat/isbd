import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class WidokLogowanie extends JComponent {

	private DbProcessor processor;
	private JLabel labelLog=new JLabel("Login:");
	private JLabel labelPass=new JLabel("Has³o:");
	private JTextField login=new JTextField();
	
	private final JPasswordField password=new JPasswordField();
	private final JButton cofnij=new JButton("Powrót");
	private final JLabel blad=new JLabel("Nieporawny login lub has³o");
	private final JButton logIN=new JButton("Zaloguj");
	
	
	
	public WidokLogowanie(DbProcessor proc) {
		processor=proc;
addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				WidokLogowanie.this.addComponents();
				login.setText("");
				password.setText("");
				
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				WidokLogowanie.this.removeAll();
				
				validate();
				repaint();
				
			}
		});
		
		login.setColumns(7);
		password.setColumns(7);
		blad.setForeground(Color.red);
		cofnij.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)WidokLogowanie.this.getParent().getLayout();
				c.show(getParent(), BazaFrame.MENUSTART);
				
			}
		});
		
		logIN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			 boolean aprove=processor.chekingPassword(login.getText(), password.getText());
			 if(aprove)
			 {
				 CardLayout c =(CardLayout)WidokLogowanie.this.getParent().getLayout();
					c.show(getParent(), BazaFrame.PRAC_START);
			 }
			 else
				 addComunicat();
				
			}
		});
		
		
	}
	public void addComunicat()
	{
		 WidokLogowanie.this.remove(logIN);
		 WidokLogowanie.this.remove(cofnij);
		 WidokLogowanie.this.add(blad,new GBC(0, 2, 2, 1));
		 WidokLogowanie.this.add(logIN,new GBC(0, 3, 2, 1).setFill(GBC.HORIZONTAL));
		 WidokLogowanie.this.add(cofnij,new GBC(0, 4, 2, 1).setFill(GBC.HORIZONTAL));
		 validate();
		 repaint();
	}
	
	public void addComponents()
	{
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		add(labelLog,new GBC(0, 0).setAnchor(GBC.EAST).setInsets(5));
		add(login,new GBC(1, 0).setFill(GBC.HORIZONTAL));
		add(labelPass,new GBC(0,1).setAnchor(GBC.EAST).setInsets(5));
		add(password,new GBC(1,1).setWeight(0, 0).setFill(GBC.HORIZONTAL));
		add(logIN,new GBC(0, 2, 2, 1).setFill(GBC.HORIZONTAL));
		add(cofnij,new GBC(0, 3, 2, 1).setFill(GBC.HORIZONTAL));
		validate();
		repaint();
	}
}
