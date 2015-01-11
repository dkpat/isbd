package klasyBazodanowe;

public class Instrument 
{
	int ID;
	private String typInstr;
	private String nazwaInstr;
	
	public Instrument(int ID,String typInstr,String nazwaInstr )
	{
		this.ID=ID;
		this.typInstr=typInstr;
		this.nazwaInstr=nazwaInstr;
	}
	public String getTypInstrumentu()
	{
		return typInstr;
	}
	public String getNazwaInstrumentu()
	{
		return nazwaInstr;
	}
	public int getID()
	{
		return ID;
	}
	
	public String toString()
	{
		return String.format("%1$-10s,jestem instrumentem z rodziny %2$10s, mój indywidalnu numer to %3$d",nazwaInstr,typInstr,ID );
	}
	
}
