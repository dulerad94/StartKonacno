package model;

import java.util.LinkedList;

public class Korisnik {

	protected String korisnickoIme;
	protected String sifra;
	protected LinkedList<Automobil> automobili;
	
	public Korisnik(String korisnickoIme, String sifra){
		this.korisnickoIme=korisnickoIme;
		this.sifra=sifra;
		automobili=new LinkedList<>();
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	

	public String getSifra() {
		return sifra;
	}

	

	public LinkedList<Automobil> getAutomobili() {
		return automobili;
	}

	public void dodajAutomobil(Automobil automobil){
		automobili.add(automobil);
	}
	public String toString(){
		return korisnickoIme;
	}
	
	
}
