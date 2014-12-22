import java.sql.*;


public class Wyst�p 
{

	private int nrWyst�pu;
	private String czasTrwaniaW;
	private Timestamp godzinaRozpocz�cia;
	private int nrKoncertu;
	private int nrZespo�u;
	
	public Wyst�p(int nrWyst�pu, String czasTrwaniaW, Timestamp godzinaRozpocz�cia, int nrKoncertu, int nrZespo�u)
	{
		this.nrWyst�pu=nrWyst�pu;
		this.czasTrwaniaW=czasTrwaniaW;
		this.godzinaRozpocz�cia=this.godzinaRozpocz�cia;
		this.nrKoncertu=nrKoncertu;
		this.nrZespo�u=this.nrZespo�u;
	}
	
	public int getnrWyst�pu()
	{
		return nrWyst�pu;
	}
	public String getczasTrwaniaW()
	{
		return czasTrwaniaW;
	}
	public Timestamp getgodzinaRozpocz�cia()
	{
		return godzinaRozpocz�cia;
	}
	public int getnrKoncertu()
	{
		return nrKoncertu;
	}
	public int getnrZespo�u()
	{
		return nrZespo�u;
	}
	
}
