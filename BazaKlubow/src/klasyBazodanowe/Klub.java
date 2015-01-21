package klasyBazodanowe;

import java.awt.image.BufferedImage;

public class Klub {
	private int Id;
	private String nazwa;
	private String adres;
	private String wlasciciel;
	private int pojemnosc;
	private BufferedImage zdjecie;

	public Klub(int Id, String nazwa, String adres, String wlasciciel,
			int pojemnosc, BufferedImage zdjecie) {
		this.Id = Id;
		this.nazwa = nazwa;
		this.adres = adres;
		this.wlasciciel = wlasciciel;
		this.pojemnosc = pojemnosc;
		this.zdjecie = zdjecie;
	}

	public int getId() {
		return Id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getAdres() {
		return adres;
	}

	public String wlasciciel() {
		return wlasciciel;
	}

	public int getPojemnosc() {
		return pojemnosc;
	}

	public BufferedImage getZdjecie() {
		return zdjecie;
	}

	public String toString() {
		return "Klub | Id: " + Id + " Nazwa: " + nazwa + " adres: " + adres
				+ " wlasciciel: " + wlasciciel + " poj: " + pojemnosc
				+ " Czy poprawne zdjêcie: " + !(zdjecie == null);
	}
}
