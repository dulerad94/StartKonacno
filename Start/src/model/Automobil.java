package model;

public class Automobil {
	protected String model;
	protected int konjskaSnaga;
	
	public Automobil(String model, int konjskaSnaga){
		this.model=model;
		this.konjskaSnaga=konjskaSnaga;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Model je:"+model+" a konjska snaga:"+konjskaSnaga;
	}
}
