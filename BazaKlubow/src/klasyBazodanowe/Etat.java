package klasyBazodanowe;

import java.sql.Timestamp;

public class Etat {
	private int Id;
	private int IdPracownika;
	private int IdKlubu;
	private int IdStanowiska;
	private Timestamp dataZatrudnienia;
	private Timestamp dataZwolnienia;
	private double p�aca; // nie powinien by� double

	public Etat(int Id, int IdPracownika, int IdKlubu, int IdStanowiska,
			Timestamp dataZatrudnienia, Timestamp dataZwolnienia, double p�aca) {
		this.Id = Id;
		this.IdPracownika = IdPracownika;
		this.IdKlubu = IdKlubu;
		this.IdStanowiska = IdStanowiska;
		this.dataZatrudnienia = dataZatrudnienia;
		this.dataZwolnienia = dataZwolnienia;
		this.p�aca = p�aca;
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

	public double getP�aca() {
		return p�aca;
	}

	public String toString() {
		return "Etat | Id: " + Id + " IdP: " + IdPracownika + " IdK: "
				+ IdKlubu + " IdS: " + IdStanowiska + " dataOd: "
				+ dataZatrudnienia + " dataDo: " + dataZwolnienia + " p�aca: "
				+ p�aca;
	}
}
