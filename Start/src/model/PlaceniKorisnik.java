package model;

public class PlaceniKorisnik extends Korisnik {
	

	protected String biografija;
	public PlaceniKorisnik(String korisnickoIme, String sifra,String biografija) {
		super(korisnickoIme, sifra);
		this.biografija=biografija;
	}
	public String getBiografija(){
		return biografija;
	}
	public String[] podaci(){
		String korisnickoIme=getKorisnickoIme();
		String sifra=getSifra();
		String biografija=getBiografija();
		return new String[]{korisnickoIme,sifra,biografija};
	}
}
