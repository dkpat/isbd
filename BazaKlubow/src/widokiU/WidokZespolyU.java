package widokiU;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import customGraphics.GBC;
import polaczenieZbaz¹.DbProcessor;
import klasyBazodanowe.Instrument;
import klasyBazodanowe.Muzyk;
import klasyBazodanowe.Zespol;


public class WidokZespolyU extends JPanel
{
	public WidokZespolyU()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		createGUI();
	}
	
	public void createGUI()
	{
		List<Zespol> zespo³y=DbProcessor.getAllBands();
		
		for(Zespol zespó³:zespo³y)
		{
			
			JPanel bandContainer=new JPanel();
			bandContainer.setLayout(new GridBagLayout());
			bandContainer.setBorder(BorderFactory.createLineBorder(Color.CYAN));
			JLabel nazwaZ=new JLabel(zespó³.getNazwaZespolu());
			nazwaZ.setFont(nazwaZ.getFont().deriveFont(18.0f));
			JLabel rokZ=new JLabel(String.format("za³o¿ony w %1$tY",zespó³.getDataZalozenia()));
			JLabel sklad=new JLabel("Sk³ad");
			JLabel obecni=new JLabel("obecni cz³okowie");
			
			//zaczynamy dodawanie do GridBagLayout
			bandContainer.add(nazwaZ,new GBC(0,0,GBC.REMAINDER,1).setAnchor(GBC.CENTER));
			bandContainer.add(rokZ,new GBC(0, 1,GBC.REMAINDER,1).setAnchor(GBC.CENTER).setInsets(5, 0, 0, 0));
			bandContainer.add(sklad,new GBC(0, 2,GBC.REMAINDER,1).setInsets(10, 5, 20, 0));
			bandContainer.add(obecni,new GBC(0, 3,GBC.REMAINDER,1).setInsets(5, 10, 10, 0).setAnchor(GBC.WEST));
			
			List<Muzyk> czlonkowie=DbProcessor.createMusicianList(zespó³.getID());
			JPanel kontenerNaMuzykow=new JPanel();
			GridLayout siatkowy=new GridLayout(0,2);
			siatkowy.setHgap(50);
			siatkowy.setVgap(10);
			kontenerNaMuzykow.setLayout(siatkowy);
			
			
			for(int i=0;i<czlonkowie.size();i++)
			{	
				Muzyk m=czlonkowie.get(i);
				Timestamp[] dataAboutMember=DbProcessor.getDataAboutMembership(zespó³.getID(), m.getNrMuzyka());
				Timestamp enterD=dataAboutMember[0];
				Timestamp exitD=dataAboutMember[1];
				JPanel muzyk=createJPanelForMusician(m, enterD, exitD);
				
				kontenerNaMuzykow.add(muzyk);
				
			}
			bandContainer.add(kontenerNaMuzykow,new GBC(0, 4));
			add(bandContainer);
		}
		
	}
	public static JPanel createJPanelForMusician(Muzyk m,Timestamp enterD,Timestamp exitD )
	{
		JPanel muzyk=new JPanel();
		muzyk.setLayout(new GridBagLayout());
		muzyk.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		JLabel nameM=new JLabel(m.getImiê()+" "+m.getNazwisko());
		//tworzenie etykiety ze zdjêciem w odpowiednim rozmiarze
			Image pictureM=m.getZdjêcie().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
			Icon iconM=new ImageIcon(pictureM);
		
		JLabel iconLabel=new JLabel(iconM);
		JLabel birthLabel=new JLabel("Urodzony");
		birthLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		JLabel birthDate=new JLabel(String.format("%tF",m.getDataUrodzenia()));
		birthDate.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		Dimension defultHeight=new Dimension(0,(int)birthDate.getPreferredSize().getHeight());
		
		String deathD= (m.getDataŒmierci()!=null) ? String.format("%tF", m.getDataŒmierci()) : "";
		JLabel deathLabel=new JLabel();
		JLabel deathDate=new JLabel();
		if(deathD!=""){
			deathLabel.setText("Zmar³");
			deathDate.setText(String.format(deathD));
		}
		else 
			deathLabel.setPreferredSize(defultHeight);
		JLabel enterLabel=new JLabel("Do³¹czy³ do zespo³u");
		JLabel enterDate=new JLabel(String.format("%tY r.",enterD));
		String exitDString=(exitD!=null) ? String.format("%tY",exitD) : "";
		JLabel exitLabel=new JLabel();
		JLabel exitDate=new JLabel(exitDString);
		if(!exitDString.equals("")){
			exitLabel.setText("Opóœci³ zespó³");
			exitDate.setText(exitDString);
		}
		else
			exitLabel.setPreferredSize(defultHeight);
			
		
		
		//No to potworzyliœmy JLabele, czas je teraz dodaæ do kontenera
		
		muzyk.add(nameM,new GBC(0, 0,GBC.REMAINDER,1).setAnchor(GBC.CENTER));
		muzyk.add(iconLabel,new GBC(0, 1,GBC.REMAINDER,1).setInsets(5));
		muzyk.add(birthLabel,new GBC(0,2).setAnchor(GBC.WEST).setInsets(0, 5, 0, 0) );
		muzyk.add(birthDate,new GBC(1, 2).setAnchor(GBC.EAST).setInsets(0, 0,0, 5));
		muzyk.add(deathLabel,new GBC(0,3).setAnchor(GBC.WEST).setInsets(0, 5, 0, 0));
		muzyk.add(deathDate,new GBC(1,3).setAnchor(GBC.EAST).setInsets(0, 0, 0, 5));
		muzyk.add(enterLabel, new GBC(0,4).setAnchor(GBC.WEST).setInsets(0, 5, 0, 0));
		muzyk.add(enterDate, new GBC(1,4).setAnchor(GBC.EAST).setInsets(0, 0, 0, 5));
		muzyk.add(exitLabel,new GBC(0, 5).setAnchor(GBC.WEST).setInsets(0, 5, 0, 0));
		muzyk.add(exitDate,new GBC(1, 5).setAnchor(GBC.EAST).setInsets(0, 0, 0, 5));
		muzyk.add(Box.createRigidArea(new Dimension(10,0)),new GBC(0, 6));
		//Na czym gra muzyk
		JLabel rolaHeaderLabel=new JLabel("W zespole pe³ni nastêpuj¹ce role:");
		muzyk.add(rolaHeaderLabel,new GBC(0, 7).setAnchor(GBC.WEST));
		
		List<Instrument> role=DbProcessor.createInstrumentList(m.getNrMuzyka());
		for(Instrument in:role)
		{
			JLabel labelIn=new JLabel(in.getNazwaInstrumentu());
			muzyk.add(labelIn,new GBC(0, GBC.RELATIVE).setAnchor(GBC.EAST));
			
		}
		return muzyk;
	}
	
	
}
