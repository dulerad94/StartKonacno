package controler;

import model.Korisnici;
import view.Konzola;
import view.OsnovniProzor;

public class Aplikacija {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Korisnici korisnici=Korisnici.getInstanca();
		Kontroler kontroler=Kontroler.getInstanca();
		kontroler.setKorisnici(korisnici);
		Konzola konzola=new Konzola(kontroler);
		OsnovniProzor prozorce=new OsnovniProzor(kontroler);
		kontroler.setProzorce(prozorce);
		kontroler.setKonzola(konzola);
	}

}
