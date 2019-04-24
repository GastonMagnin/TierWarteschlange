package Tiere;

@SuppressWarnings("WeakerAccess")
public class Reptil extends Tier {
	
	private boolean giftig;
		
	public Reptil(String tierart, int alter, int gewicht, char geschlecht, boolean raubtier, boolean giftig) {
		super(tierart, alter, gewicht, geschlecht, raubtier);
		this.giftig = giftig;
	}
		
	public boolean getGiftig() {
		return giftig;
	}
	
	@Override
	public String toString() {
		return super.getTierart() + " | Geschlecht: " + super.getGeschlecht() + " | Alter: " + super.getAlter() + " | Raubtier: " + super.getRaubtier() + " | giftig: " + getGiftig() ;
	}

}
