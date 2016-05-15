package view;

import controler.Kontroler;

public class Konzola {
	private Kontroler kontrolor;
	public Konzola(Kontroler kontrolor){
		this.kontrolor=kontrolor;
		System.out.println("Dobrodosli");
		System.out.println(kontrolor.listaKorisnikaIAutomobila());
	}
	public void primiObavestenje(String tekst){
		System.out.println("PROMENA");
		System.out.println(kontrolor.listaKorisnikaIAutomobila());
	}
}
