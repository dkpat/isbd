package klasyBazodanowe;

public class OpisPiosenki {
	private int Id;
	private String tytul;
	private int IdZespolu;
	private String plyta;
	private String czasTrwania; // ? nie wiem jakiego typu u¿yæ...

	public OpisPiosenki(int Id, String tytul, int IdZespolu, String plyta,
			String czasTrwania) {
		this.Id = Id;
		this.tytul = tytul;
		this.IdZespolu = IdZespolu;
		this.plyta = plyta;
		this.czasTrwania = czasTrwania;
	}

	public int getId() {
		return Id;
	}

	public String getTytul() {
		return tytul;
	}

	public int getIdZespolu() {
		return IdZespolu;
	}

	public String getPlyta() {
		return plyta;
	}

	public String getCzasTrwania() {
		return czasTrwania;
	}

	public String toString() {
		return "Opis piosenki | Id: " + Id + " tytul: " + tytul + " IdZesp: "
				+ IdZespolu + " p³yta: " + plyta + " czas trwania: "
				+ czasTrwania;
	}
}
