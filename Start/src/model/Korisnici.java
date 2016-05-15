package model;

import java.util.LinkedList;

import exception.NepostojeciKorisnikException;
import exception.PostojeciKorisnikException;

public class Korisnici {
	protected LinkedList<Korisnik> korisnici;
	private static Korisnici instanca;
	
	public static Korisnici getInstanca(){
		if(instanca==null){
			instanca=new Korisnici();
		}
		return instanca;
	}
	private Korisnici(){
		korisnici=new LinkedList<>();
	} 
	
	public LinkedList<Korisnik> getKorisnici(){
		return korisnici;
	}
	public Korisnik nadjiKorisnika(String korisnickoIme) throws NepostojeciKorisnikException{
		for (Korisnik korisnik : korisnici) {
			if(korisnik.getKorisnickoIme().equals(korisnickoIme)) return korisnik;
		}
		 throw new NepostojeciKorisnikException("Ne postoji taj korisnik");
	}
	public void registrujKorisnika(String korisnickoIme, String sifra, String biografija,String tip) throws  PostojeciKorisnikException{
		 try {
			nadjiKorisnika(korisnickoIme);
			throw new PostojeciKorisnikException("Postoji korisnik sa istim imenom");
		} catch (NepostojeciKorisnikException e) {
			if(!tip.equals("placa")){
				korisnici.add(new Korisnik(korisnickoIme,sifra));
			}else korisnici.add(new PlaceniKorisnik(korisnickoIme, sifra,biografija));
		}
		
		
	}
	public void dodajAuto(String model,int konjskaSnaga,String korisnickoIme) throws NepostojeciKorisnikException{
		nadjiKorisnika(korisnickoIme).dodajAutomobil(new Automobil(model, konjskaSnaga));
	}
}
