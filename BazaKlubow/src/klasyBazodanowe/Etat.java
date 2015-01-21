package klasyBazodanowe;

import java.sql.Timestamp;

public class Etat {
	private int Id;
	private int IdPracownika;
	private int IdKlubu;
	private int IdStanowiska;
	private Timestamp dataZatrudnienia;
	private Timestamp dataZwolnienia;
	private double p³aca; // nie powinien byæ double

	public Etat(int Id, int IdPracownika, int IdKlubu, int IdStanowiska,
			Timestamp dataZatrudnienia, Timestamp dataZwolnienia, double p³aca) {
		this.Id = Id;
		this.IdPracownika = IdPracownika;
		this.IdKlubu = IdKlubu;
		this.IdStanowiska = IdStanowiska;
		this.dataZatrudnienia = dataZatrudnienia;
		this.dataZwolnienia = dataZwolnienia;
		this.p³aca = p³aca;
	}

	public int getId() {
		return Id;
	}

	public int getIdPracownika() {
		return IdPracownika;
	}

	public int getIdKlubu() {
		return IdKlubu;
	}

	public int getIdStanowiska() {
		return IdStanowiska;
	}

	public Timestamp getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public Timestamp getDataZwolniani() {
		return dataZwolnienia;
	}

	public double getP³aca() {
		return p³aca;
	}

	public String toString() {
		return "Etat | Id: " + Id + " IdP: " + IdPracownika + " IdK: "
				+ IdKlubu + " IdS: " + IdStanowiska + " dataOd: "
				+ dataZatrudnienia + " dataDo: " + dataZwolnienia + " p³aca: "
				+ p³aca;
	}
}
