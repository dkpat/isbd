package klasyBazodanowe;
import java.sql.*;


public class Wystêp 
{

	private int nrWystêpu;
	private String czasTrwaniaW;
	private Timestamp godzinaRozpoczêcia;
	private int nrKoncertu;
	private int nrZespo³u;
	
	public Wystêp(int nrWystêpu, String czasTrwaniaW, Timestamp godzinaRozpoczêcia, int nrKoncertu, int nrZespo³u)
	{
		this.nrWystêpu=nrWystêpu;
		this.czasTrwaniaW=czasTrwaniaW;
		this.godzinaRozpoczêcia=godzinaRozpoczêcia;
		this.nrKoncertu=nrKoncertu;
		this.nrZespo³u=nrZespo³u;
	}
	
	public int getnrWystêpu()
	{
		return nrWystêpu;
	}
	public String getczasTrwaniaW()
	{
		return czasTrwaniaW;
	}
	public Timestamp getgodzinaRozpoczêcia()
	{
		return godzinaRozpoczêcia;
	}
	public int getNrKoncertu()
	{
		return nrKoncertu;
	}
	public int getNrZespo³u()
	{
		return nrZespo³u;
	}
	
}
