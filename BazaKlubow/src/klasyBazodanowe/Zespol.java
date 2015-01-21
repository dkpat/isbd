package klasyBazodanowe;
import java.sql.Timestamp;
import java.util.List;

import javax.management.MXBean;


public class Zespol 
{
	private int ID;
	private String nazwa;
	private Timestamp poczatekD;
	
	public Zespol(int ID, String nazwa, Timestamp poczatekD)
	{
		this.ID=ID;
		this.nazwa=nazwa;
		this.poczatekD=poczatekD;
	}
	public int getID()
	{
		return ID;
	}
	public String getNazwaZespolu()
	{
		return nazwa;
	}
	public Timestamp getDataZalozenia()
	{
		return poczatekD;
	}
	
	
	public String toString()
	{
		StringBuilder bob=new StringBuilder(String.format("ID = %1$d, %2$s - za³o¿ony w %3$tF muzycy: %n", ID,nazwa,poczatekD));
		//ddd
			bob.append("\n");
		return bob.toString();
	}

}
