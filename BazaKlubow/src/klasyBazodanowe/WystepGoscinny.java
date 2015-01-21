package klasyBazodanowe;

public class WystepGoscinny {
	private int Id;
	private int IdMuzyka;
	private int IdWykonania;

	public WystepGoscinny(int Id, int IdMuzyka, int IdWykonania) {
		this.Id = Id;
		this.IdMuzyka = IdMuzyka;
		this.IdWykonania = IdWykonania;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdMuzyka() {
		return IdMuzyka;
	}

	public void setIdMuzyka(int idMuzyka) {
		IdMuzyka = idMuzyka;
	}

	public int getIdWykonania() {
		return IdWykonania;
	}

	public void setIdWykonania(int idWykonania) {
		IdWykonania = idWykonania;
	}

	public String toString() {
		return "WystepGoscinny | Id: " + Id + " IdM: " + IdMuzyka + " IdW: "
				+ IdWykonania;
	}
}
