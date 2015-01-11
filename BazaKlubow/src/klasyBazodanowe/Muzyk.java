package klasyBazodanowe;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.List;

public class Muzyk {
	private int ID;
	private String imiê;
	private String nazwisko;
	private BufferedImage zdjêcie;
	private Timestamp birthD;
	private Timestamp deathD;
	

	public Muzyk(int ID, String imiê, String nazwisko, BufferedImage zdjêcie,
			Timestamp birthD, Timestamp deathD) {
		this.ID = ID;
		this.imiê = imiê;
		this.nazwisko = nazwisko;
		this.zdjêcie = zdjêcie;
		this.birthD = birthD;
		this.deathD = deathD;
		
	}

	public int getNrMuzyka()
	{
		return ID;
	}
	
	public String getImiê() {
		return imiê;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public BufferedImage getZdjêcie() {
		return zdjêcie;
	}

	public Timestamp getDataUrodzenia() {
		return birthD;
	}

	public Timestamp getDataŒmierci() {
		return deathD;
	}

	
	public String toString()
	{
		StringBuilder bob=new StringBuilder(String.format("%nID = %1$d, %2$s %3$s , urodzony %4$tF, zmar³ %5$tF %n",ID,imiê,nazwisko,birthD,deathD ));
		bob.append("Czy mam poprawne zdjêcie "+!(zdjêcie==null)+"\n");
		
		return bob.toString();
	}

}
