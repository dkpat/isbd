import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


public class KlubComponent extends JComponent {

	private JLabel nLabel;
	private JButton koncertyButton;
	private JLabel adresLabel;
	private JLabel wLabel;
	private JLabel pLabel;
	private Icon zdj�cie;
	JTextArea textArea;
	
	public final static int verGab=10;
	public final static int horGab=5;
	public final static int verMarg=10;
	public final static int horMarg=10;
	public final static int weightOner=150;
	
	public KlubComponent(String nazwa, String adres, String w�a�ciciel, int pojemno��, byte[] zdj�cie)
	{
		
		nLabel=new JLabel(nazwa);
		koncertyButton=new JButton("Koncerty");
		koncertyButton.setUI(new MyButtonUI());
		JLabel opis=new JLabel("W�a�ciciel");
		wLabel=new JLabel(w�a�ciciel);
		wLabel.setMaximumSize(new Dimension(weightOner,wLabel.getPreferredSize().height));
		/*
		textArea = new JTextArea("W�a�ciciel: "+w�a�ciciel);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setBorder(BorderFactory.createEmptyBorder());
		textArea.setMaximumSize(new Dimension(200, 100));
		*/
		
		adresLabel=new JLabel(adres);
		pLabel=new JLabel("pojemno�� "+pojemno��);
		if(zdj�cie!=null)
		{
			InputStream in=new ByteArrayInputStream(zdj�cie);
			try {
				BufferedImage bImageFromConvert =ImageIO.read(in);
				
				if(bImageFromConvert!=null)
					this.zdj�cie=new ImageIcon(bImageFromConvert.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Border blackLine=BorderFactory.createLineBorder(Color.BLACK);
		//setBorder(blackLine);
		
		
		
		JLabel imgLabel=new JLabel(this.zdj�cie);
		
		imgLabel.setBorder(blackLine);
		
		setLayout(new GridBagLayout());
		add(nLabel,new GBC(0, 0).setWeight(0, 10).setAnchor(GBC.SOUTH));
		add(koncertyButton,new GBC(0, 1).setWeight(0, 10).setAnchor(GBC.CENTER));
		add(opis,new GBC(0, 2).setAnchor(GBC.SOUTH).setWeight(0, 5));
		add(wLabel,new GBC(0, 3).setAnchor(GBC.NORTH).setWeight(10, 2));
		add(adresLabel,new GBC(0, 4).setInsets(5, 0, 0, 0));
		add(pLabel,new GBC(1, 4).setAnchor(GBC.EAST).setInsets(5, 0, 0, KlubComponent.verMarg/2));
		add(imgLabel,new GBC(1, 0, 1, 4).setAnchor(GBC.CENTER).setWeight(2, 0));
		System.out.println(HEIGHT);
	}
	
	
	@Override
	public Dimension getPreferredSize(){
		
		int width=verGab+wLabel.getMaximumSize().width+zdj�cie.getIconWidth()+verMarg;
		int height=horGab+zdj�cie.getIconHeight()+pLabel.getPreferredSize().height+horMarg;
		
		return new Dimension(width,height);
	}
	@Override
	public Dimension getMaximumSize(){
		return getPreferredSize();
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawLine(weightOner, 5, weightOner, this.getPreferredSize().height-5);
	}
}
