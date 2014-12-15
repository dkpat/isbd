import java.math.BigDecimal;
import java.sql.*;

public class Koncerty {

	private int ID;
	private Timestamp data;
	private int liczbaLudzi;
	private BigDecimal cenaBiletu;
	private int nrKlubu;
	
	public Koncerty(int id, Timestamp data, int liczbaLudzi,BigDecimal cenaBiletu, int nrKlubu)
	{
		this.ID=id;
		this.data=data;
		this.liczbaLudzi=liczbaLudzi;
		this.cenaBiletu=cenaBiletu;
		this.nrKlubu=nrKlubu;
	}
	
	public int getID()
	{
		return ID;
	}
	public Timestamp getData()
	{
		return data;
	}
	public int getliczbaLudzi()
	{
		return liczbaLudzi;
	}
	public BigDecimal getCenaBiletu()
	{
		return cenaBiletu;
	}
	public int getNrKlubu()
	{
		return nrKlubu;
	}
	
	public String toString()
	{
		return "Koncert w klubie o nr. "+nrKlubu+" ,odbêdzie siê "+data;
	}
}
