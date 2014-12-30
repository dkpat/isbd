import java.sql.*;


public class Występ 
{

	private int nrWystępu;
	private String czasTrwaniaW;
	private Timestamp godzinaRozpoczęcia;
	private int nrKoncertu;
	private int nrZespołu;
	
	public Występ(int nrWystępu, String czasTrwaniaW, Timestamp godzinaRozpoczęcia, int nrKoncertu, int nrZespołu)
	{
		this.nrWystępu=nrWystępu;
		this.czasTrwaniaW=czasTrwaniaW;
		this.godzinaRozpoczęcia=godzinaRozpoczęcia;
		this.nrKoncertu=nrKoncertu;
		this.nrZespołu=nrZespołu;
	}
	
	public int getnrWystępu()
	{
		return nrWystępu;
	}
	public String getczasTrwaniaW()
	{
		return czasTrwaniaW;
	}
	public Timestamp getgodzinaRozpoczęcia()
	{
		return godzinaRozpoczęcia;
	}
	public int getNrKoncertu()
	{
		return nrKoncertu;
	}
	public int getNrZespołu()
	{
		return nrZespołu;
	}
	
}
