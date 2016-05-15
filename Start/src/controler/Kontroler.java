package controler;

import java.util.LinkedList;

import exception.LosaSifraException;
import exception.NepostojeciKorisnikException;
import exception.PostojeciKorisnikException;
import model.Korisnici;
import model.Korisnik;
import model.PlaceniKorisnik;
import view.GlavniProzor;
import view.GlavniProzorPlaceniKorisnik;
import view.Konzola;
import view.LogIn;
import view.LogInSignUp;
import view.OsnovniProzor;
import view.Profil;
import view.SignUp;
public class Kontroler {
	
	private static Kontroler instanca;
	private OsnovniProzor prozorce;
	private Konzola konzola;
	private Korisnici korisnici;
	private Korisnik trenutniKorisnik;
	
	
	public static Kontroler getInstanca(){
		if(instanca==null){
			instanca=new Kontroler();
		}
		return instanca;
	}
	private Kontroler(){
		predjiNaLogInSignUp();
	}
	public OsnovniProzor getProzorce() {
		return prozorce;
	}
	public void setProzorce(OsnovniProzor prozorce) {
		this.prozorce = prozorce;
	}
	public Konzola getKonzola() {
		return konzola;
	}
	public void setKonzola(Konzola konzola) {
		this.konzola = konzola;
	}
	public Korisnici getKorisnici() {
		return korisnici;
	}
	public void setKorisnici(Korisnici korisnici) {
		this.korisnici = korisnici;
	}
	public void predjiNaLogInSignUp(){
		prozorce=new LogInSignUp(this);
		omoguciProzorce();
	}
	public void predjiNaLogIn(){
		prozorce=new LogIn(this);
		omoguciProzorce();
	}
	public void predjiNaGlavniProzor(){
		if(trenutniKorisnik instanceof PlaceniKorisnik)
			prozorce=new GlavniProzorPlaceniKorisnik(this);
		else prozorce=new GlavniProzor(this);
		omoguciProzorce();
	}
	public void predjiNaSignUp(){
		prozorce=new SignUp(this);
		omoguciProzorce();
	}
	public void predjiNaProfil(){
		prozorce=new Profil(this);
		omoguciProzorce();
	}
	public String[] dajPodatkeOKorisniku(){
		return ((PlaceniKorisnik) trenutniKorisnik).podaci();
	}
	private void omoguciProzorce(){
		prozorce.setLocationRelativeTo(null);
		prozorce.setVisible(true);
	}
	public LinkedList<Korisnik> dajListuKorisnika(){
		return korisnici.getKorisnici();
	}
	public void ulogujSe(String korisnickoIme,String sifra) throws LosaSifraException, NepostojeciKorisnikException{
		Korisnik k=korisnici.nadjiKorisnika(korisnickoIme);
		if(!k.getSifra().equals(sifra)){
			throw new LosaSifraException("Uneli ste pogresnu sifru");
		}
		trenutniKorisnik=k;
		predjiNaGlavniProzor();
	}
	public void registrujSe(String korisnickoIme,String sifra,String ponovljenaSifra,String biografija,String tip) throws LosaSifraException,  PostojeciKorisnikException{
		if(!sifra.equals(ponovljenaSifra)) throw new LosaSifraException("Sifra i ponovljena sifra moraju da budu iste");
		korisnici.registrujKorisnika(korisnickoIme,sifra,biografija,tip);
		obavestiZainteresovane("K;"+korisnickoIme);
	}
	public void izlogujSe(){
		trenutniKorisnik=null;
		predjiNaLogInSignUp();
	}
	public String listaAutomobila(String korisnickoIme) throws NepostojeciKorisnikException{
		Korisnik k=korisnici.nadjiKorisnika(korisnickoIme);
		String tekst="";
		for (int i = 0; i < k.getAutomobili().size(); i++) {
			tekst+=k.getAutomobili().get(i)+"\n";
		}
		return tekst;
	}
	public String listaAutomobila() throws NepostojeciKorisnikException{
		return listaAutomobila(trenutniKorisnik.getKorisnickoIme());
	}
	public void dodajAuto(String model,int konjskaSnaga) throws NepostojeciKorisnikException{
		dodajAuto(model,konjskaSnaga,trenutniKorisnik.getKorisnickoIme());
	}
	public void dodajAuto(String model,int konjskaSnaga,String korisnickoIme) throws NepostojeciKorisnikException{
		korisnici.dodajAuto(model,konjskaSnaga,korisnickoIme);
		obavestiZainteresovane(model+";"+konjskaSnaga);
	}
	private void obavestiZainteresovane(String tekst){
		prozorce.primiObavestenje(tekst);
		konzola.primiObavestenje(tekst);
	}
	public String listaKorisnikaIAutomobila(){
		String tekst="";
		
		for (int i = 0; i < korisnici.getKorisnici().size(); i++) {
			Korisnik k=korisnici.getKorisnici().get(i);
			tekst+=k+"\n";
			for (int j = 0; j < k.getAutomobili().size(); j++) {
				tekst+="\t"+k.getAutomobili().get(j)+"\n";
			}
		}
		return tekst;
	}
}
