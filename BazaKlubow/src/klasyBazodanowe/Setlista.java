package klasyBazodanowe;

public class Setlista {
	private int Id;
	private String nazwa;
	private int IdWystepu;

	public Setlista(int Id, String nazwa, int IdWystepu) {
		this.Id = Id;
		this.nazwa = nazwa;
		this.IdWystepu = IdWystepu;
	}

	public int getId() {
		return Id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getIdWystepu() {
		return IdWystepu;
	}

	public String toString() {
		return "Wystep | Id: " + Id + " nazwa: " + nazwa + " IdWyst: "
				+ IdWystepu;
	}
}
