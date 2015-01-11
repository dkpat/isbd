package klasyBazodanowe;
import java.sql.Time;
import java.sql.Timestamp;


public class Cz�onkostwo 
{
	private int ID;
	private Timestamp dataPrzyst;
	private Timestamp dataOpuszczenia;
	private int nrMuzyka;
	private int nrZespolu;

	public Cz�onkostwo(int ID,Timestamp dataPrzyst, Timestamp dataOpuszczenia, int nrMuzyka, int nrZespolu )
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
	
	public Timestamp getdataPrzyst�pienia()
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
