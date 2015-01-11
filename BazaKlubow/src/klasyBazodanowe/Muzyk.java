package klasyBazodanowe;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.List;

public class Muzyk {
	private int ID;
	private String imi�;
	private String nazwisko;
	private BufferedImage zdj�cie;
	private Timestamp birthD;
	private Timestamp deathD;
	

	public Muzyk(int ID, String imi�, String nazwisko, BufferedImage zdj�cie,
			Timestamp birthD, Timestamp deathD) {
		this.ID = ID;
		this.imi� = imi�;
		this.nazwisko = nazwisko;
		this.zdj�cie = zdj�cie;
		this.birthD = birthD;
		this.deathD = deathD;
		
	}

	public int getNrMuzyka()
	{
		return ID;
	}
	
	public String getImi�() {
		return imi�;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public BufferedImage getZdj�cie() {
		return zdj�cie;
	}

	public Timestamp getDataUrodzenia() {
		return birthD;
	}

	public Timestamp getData�mierci() {
		return deathD;
	}

	
	public String toString()
	{
		StringBuilder bob=new StringBuilder(String.format("%nID = %1$d, %2$s %3$s , urodzony %4$tF, zmar� %5$tF %n",ID,imi�,nazwisko,birthD,deathD ));
		bob.append("Czy mam poprawne zdj�cie "+!(zdj�cie==null)+"\n");
		
		return bob.toString();
	}

}
