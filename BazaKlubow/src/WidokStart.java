import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class WidokStart extends JPanel {	
	private DbProcessor processor;
	private int columns=3;
	private ArrayList<KlubComponent> listaKlubow;
	private JPanel ciaglyPionowy;
	
	public WidokStart(DbProcessor proc){
		processor=proc;
		setLayout(new BorderLayout());
	
		/*
		 * To tylko panel pomocniczy
		 * na przycisk logowania, u góry po prawej
		 */
		JPanel con = new JPanel();
		/*
		 * To z kolei panel 
		 * na menu z lewej strony
		 */
		JPanel bocznyL=new JPanel();
		bocznyL.setLayout(new GridLayout(0,1));
		
		JButton mp=new JButton("Pracownik");
	
		mp.setUI(new MyButtonUI());
		mp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("Dzia³am");
				CardLayout c =(CardLayout)WidokStart.this.getParent().getLayout();
				
				c.show(getParent(),BazaFrame.LOG);
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("papa");
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
		con.setLayout(new FlowLayout(FlowLayout.TRAILING));
		con.add(mp);
		
		add(BorderLayout.NORTH,con);
		listaKlubow=processor.createKlubComponents();
		ciaglyPionowy=new JPanel();
		/*
		BoxLayout box=new BoxLayout(ciaglyPionowy, BoxLayout.Y_AXIS); 
		ciaglyPionowy.setLayout(box);
		
		ciaglyPionowy.add(new JButton("Cos tam"));
		for(KlubComponent k:listaKlubow)
		{
			ciaglyPionowy.add(Box.createRigidArea(new Dimension(0,10)));
			ciaglyPionowy.add(k);
			
		}
		*/
		
		/*
		 * przyciski dla bocznego panelu
		 */
		
		JButton koncerty=new JButton("Koncerty");
		JButton muzycy=new JButton("Zespo³y");
		JButton archiwum=new JButton("Archiwum");
		bocznyL.add(koncerty);
		bocznyL.add(muzycy);
		bocznyL.add(archiwum);
		add(BorderLayout.WEST,bocznyL);
		
		manageColumns(ciaglyPionowy);
		JScrollPane panelPrzewijany=new JScrollPane(ciaglyPionowy);
		add(BorderLayout.CENTER,panelPrzewijany);
	}
	public void setNumberOfColumns(int c)
	{
		if(c>0)
			this.columns=c;
	}
	public int getNumberOfColumns()
	{
		return columns;
	}
	private void manageColumns(Container c)
	{
		c.removeAll();
		c.setLayout(new GridBagLayout());
		
		for(int i=0;i<listaKlubow.size();i++)
		{
			int x=i%columns;
			int y=i/columns;
			c.add(listaKlubow.get(i),new GBC(x, y));
		}
		c.revalidate();
		c.repaint();
	}
	public void resize()
	{
		manageColumns(ciaglyPionowy);
	}
}
