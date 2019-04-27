package NichtTiere;

import Tiere.Tier;

public class Arche {
	
	Stall<Tier>[] staelle = null;
	
	@SuppressWarnings("unchecked")
	public Arche(int staelle) {
		this.staelle = (Stall<Tier>[]) new Stall<?>[staelle];
	}
	
	@SuppressWarnings("unchecked")
	public Arche() {
		this.staelle = (Stall<Tier>[]) new Stall<?>[3];
	}
	
	/**
	 * Calculates the amount of objects inside the Arche
	 * @return the amount of objects
	 */
	public int size() {
		int sum = 0;
		for(int i = 0; i < staelle.length; i++) {
			if(!(staelle[i] == null || staelle[i].isEmpty())) {
				sum += staelle[i].size();
			}
		}
		return sum;
	}
	
	/**
	 * Saves an Tier object from the big flood
	 * @param the animal to save
	 * @return true if the animal was added to the Arche
	 */
	public boolean add (Tier animal) {
		int empty = 0;
		for(int i = 0; i < staelle.length; i++) {
			if(staelle[i] == null || staelle[i].isEmpty()) {
				empty = i;
				continue;
			}else if(staelle[i].first().getClass() == animal.getClass()){
				return staelle[i].add(animal);
			}
		}
		staelle[empty] = new Stall<Tier>();
		return staelle[empty].add(animal);
	}
	
	/**
	 * Print all Stall objects with all Tier objects
	 */
	public void printStallbelegung() {
		for(int i = 0; i < staelle.length; i++) {
			if(staelle[i] == null || staelle[i].isEmpty())
				System.out.println("Empty-Stall\n\n");
			else
				System.out.println(staelle[i].first().getClass().getSimpleName()+"-Stall\n"+staelle[i]+"\n");
		}
	}
	
	/**
	 * Create two Tierwarteschlange objects and split up the Tier objects
	 * depending on getRaubtier(). Prints the Tierwarteschlange objects as well.
	 */
	public void erstelleFuetterungsWarteschlangen() {
		TierWarteschlange raubtiere 	= new TierWarteschlange();
		TierWarteschlange harmloseTiere = new TierWarteschlange();
		for(int i = 0; i < staelle.length; i++) {
			staelle[i].raubtiertrennung(raubtiere, true);
			staelle[i].raubtiertrennung(harmloseTiere, false);
		}
		System.out.println("Raubtiere: "+raubtiere);
		System.out.println("Harmlose Tiere: "+harmloseTiere);
	}
}
