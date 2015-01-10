import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;


public class BazaFrame extends JFrame {

	public final static int DEAFAULT_WIDTH=800;
	public final static int DEAFAULT_HEIGHT=600;
	public final static String MENUSTART="planszaGlowna";
	public final static String PRAC_START="WidokPracownik";
	public final static String LOG="logowanie";
	
	
	public BazaFrame()
	{
		 
		
		setSize(DEAFAULT_WIDTH, DEAFAULT_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DbProcessor.doConnection();
		JPanel zmienny=new JPanel();
		zmienny.setLayout(new CardLayout());
		add(zmienny);
		final WidokStart powitaj=new WidokStart(); 
		zmienny.add(powitaj,MENUSTART);
		WidokPracMenu w1=new WidokPracMenu();
		zmienny.add(w1,PRAC_START);
		WidokLogowanie log=new WidokLogowanie();
		zmienny.add(log,LOG);
		
		
		
		
		
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			
			public void componentResized(ComponentEvent arg0) {
			
				Toolkit kit=Toolkit.getDefaultToolkit();
				Dimension d=kit.getScreenSize();
				int widthScreen=d.width;
				if(getWidth()<0.6*widthScreen&&powitaj.getNumberOfColumns()!=2)
				{
					powitaj.setNumberOfColumns(2);
					powitaj.resize();
				}
				else
					if(getWidth()>=0.6*widthScreen&&getWidth()<0.8*widthScreen&&powitaj.getNumberOfColumns()!=3)
					{
						powitaj.setNumberOfColumns(3);
						powitaj.resize();
					}
					else
						if(getWidth()>=0.8*widthScreen&&powitaj.getNumberOfColumns()!=4){
							powitaj.setNumberOfColumns(4);
							powitaj.resize();
						}
					
				
				
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
				setDefaultLookAndFeelDecorated(true);
				BazaFrame b=new BazaFrame();
				b.setVisible(true);
				
			}
		});

	}

}
