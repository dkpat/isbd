package klasyBazodanowe;

import java.sql.Timestamp;

public class Pracownik {
	private int Id;
	private String imie;
	private String nazwisko;
	private Timestamp dataUrodzenia;
	private String pesel;
	private String login;
	/*
	 * Nie wiem czy przechowywanie has³a w prostym stringu to dobry pomys³, ale
	 * to jeszcze najwy¿ej zmienimy póŸniej
	 */
	private String haslo;

	public Pracownik(int Id, String imie, String nazwisko,
			Timestamp dataUrodzenia, String pesel, String login, String haslo) {
		this.Id = Id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.dataUrodzenia = dataUrodzenia;
		this.pesel = pesel;
		this.login = login;
		this.haslo = haslo;
	}

	public int getId() {
		return Id;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public Timestamp getDataUrodzenia() {
		return dataUrodzenia;
	}

	public String getPesel() {
		return pesel;
	}

	public String getLogin() {
		return login;
	}

	public String getHaslo() {
		return haslo;
	}

	public String toString() {
		return "Pracownik | ID: " + Id + " " + nazwisko + " " + imie
				+ " data ur: " + dataUrodzenia + " pesel: " + pesel + " l: "
				+ login + " h: " + haslo;
	}
}
