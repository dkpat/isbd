package klasyBazodanowe;

public class UmiejetnoscGry {
	private int Id;
	private int IdMuzyka;
	private int IdInstrumentu;
	
	public UmiejetnoscGry(int Id, int IdMuzyka, int IdInstrumentu){
		this.Id = Id;
		this.IdMuzyka = IdMuzyka;
		this.IdInstrumentu = IdInstrumentu;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public int getIdMuzyka() {
		return IdMuzyka;
	}
	public void setIdMuzyka(int idMuzyka) {
		this.IdMuzyka = idMuzyka;
	}
	public int getIdInstrumentu() {
		return IdInstrumentu;
	}
	public void setIdInstrumentu(int idInstrumentu) {
		this.IdInstrumentu = idInstrumentu;
	}
	public String toString(){
		return "Umiejetnosc gry: | ID: " + Id + " IdM: " + IdMuzyka + " IdI: " + IdInstrumentu;
	}
}
