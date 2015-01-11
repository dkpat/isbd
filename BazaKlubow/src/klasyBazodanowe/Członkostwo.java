package klasyBazodanowe;
import java.sql.Time;
import java.sql.Timestamp;


public class Cz³onkostwo 
{
	private int ID;
	private Timestamp dataPrzyst;
	private Timestamp dataOpuszczenia;
	private int nrMuzyka;
	private int nrZespolu;

	public Cz³onkostwo(int ID,Timestamp dataPrzyst, Timestamp dataOpuszczenia, int nrMuzyka, int nrZespolu )
	{
		this.ID=ID;
		this.dataPrzyst=dataPrzyst;
		this.dataOpuszczenia=dataOpuszczenia;
		this.nrMuzyka=nrMuzyka;
		this.nrZespolu=nrZespolu;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public Timestamp getdataPrzyst¹pienia()
	{
		return dataPrzyst;
	}
	
	public Timestamp getDataOpuszczenia()
	{
		return dataOpuszczenia;
	}
	
	public int getNrMuzyka(){
		return nrMuzyka;
	}
	public int getNrZespolu()
	{
		return nrZespolu;
	}
	
}
