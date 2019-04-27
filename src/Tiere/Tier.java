package Tiere;

import java.util.Random;

@SuppressWarnings("rawtypes")
public class Tier implements Comparable{

	private static final char MALE = 'M';
	private static final char FEMALE = 'W';
	
	private int alter;
	private int gewicht;
	private char geschlecht;
	private boolean raubtier = true;
	private String tierart = null;
		
	public Tier() {
		Random rand = new Random();
		alter = rand.nextInt(11);
		
		if (rand.nextInt(2) < 1) {
			geschlecht = MALE;
		}
		else {
			geschlecht = FEMALE;
		}
	}
	
	public Tier(String tierart, int alter, int gewicht, char geschlecht, boolean raubtier) {
		this.tierart = tierart;
		this.alter = alter;
		this.gewicht = gewicht;
		this.geschlecht = geschlecht;
		this.raubtier = raubtier;
	}
	
	
	public int getAlter() {
		return alter;
	}
	
	public int getGewicht() {
		return gewicht;
	}
	
	public char getGeschlecht() {
		return geschlecht;
	}
	
	public boolean getRaubtier () {
		return raubtier;
	}
	
	public String getTierart() {
		return tierart;
	}
	
	public void setRaubtier(boolean raubtier) {
		this.raubtier = raubtier;
	}
	
	public void setTierart(String tierart) {
		if (this.tierart == null) {
			this.tierart = tierart;
		}
	}
	
	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}
	
	@Override
	public String toString() {
		return getTierart() + " | Geschlecht: " + getGeschlecht() + " | Alter: " + getAlter() + " | Gewicht: " + getGewicht() + "| Raubtier: " + getRaubtier();
	}


	//compares two Tier objects
	@Override
	public int compareTo(Object o) {
		if(this.getTierart() == ((Tier)o).getTierart() && this.getGeschlecht() == ((Tier)o).getGeschlecht()) {
			return 0;
		}else if(this.getTierart() == ((Tier)o).getTierart() && this.getGeschlecht() == 'M' && ((Tier)o).getGeschlecht() == 'W') {
			return 1;
		}else if(this.getTierart() == ((Tier)o).getTierart() && this.getGeschlecht() == 'W' && ((Tier)o).getGeschlecht() == 'M') {
			return -1;
		}else if(this.getTierart() != ((Tier)o).getTierart()) {
			int comp = this.getTierart().compareTo(((Tier)o).getTierart());
			if(comp == 0) return 1;
			else return comp;
		}else {
			throw new IllegalArgumentException();
		}
	}

}
