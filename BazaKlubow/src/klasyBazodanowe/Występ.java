package klasyBazodanowe;
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
		this.godzinaRozpocz�cia=godzinaRozpocz�cia;
		this.nrKoncertu=nrKoncertu;
		this.nrZespo�u=nrZespo�u;
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
	public int getNrKoncertu()
	{
		return nrKoncertu;
	}
	public int getNrZespo�u()
	{
		return nrZespo�u;
	}
	
}
