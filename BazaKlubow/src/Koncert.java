import java.math.BigDecimal;
import java.sql.*;

public class Koncert {

	private int ID;
	private String nazwaW;
	private Timestamp data;
	private int liczbaLudzi;
	private BigDecimal cenaBiletu;
	private int nrKlubu;
	
	public Koncert(int id, String nazwaW,Timestamp data, int liczbaLudzi,BigDecimal cenaBiletu, int nrKlubu)
	{
		this.ID=id;
		this.nazwaW=nazwaW;
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
	public String getNawzaW()
	{
		return nazwaW;
	}
	public String toString()
	{
		return "Koncert w klubie o nr. "+nrKlubu+" ,odbêdzie siê "+data;
	}
}
