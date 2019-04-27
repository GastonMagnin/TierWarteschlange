package NichtTiere;

import Tiere.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int TIERANZAHLAUFG1 = 10;
		final int TIERANZAHLAUFG2 = 20;

		//AUFGABE 1
		final TierWarteschlange snake = new TierWarteschlange();
		for (int i = 0; i < TIERANZAHLAUFG1; i++) {
			snake.add(Tiergenerator.getTier());
		}

		System.out.println(TIERANZAHLAUFG1 + " random generated TierWarteschlange:\n" + snake);
		snake.grossesFressen();
		System.out.println("TierWarteschlange after \"grosses Fressen\":\n" + snake);
		snake.sturm();
		System.out.println("TierWarteschlange after \"Sturm\":\n" + snake);
		snake.grossesFressen();
		System.out.println("TierWarteschlange after another \"grosses Fressen\":\n" + snake);
		snake.sort((Tier first, Tier second) -> first.getTierart().compareTo(second.getTierart()));
		System.out.println("TierWarteschlange sorted by Tierart:\n" + snake);
		snake.sort((Tier first, Tier second) -> (first.getGeschlecht() + "").compareTo(second.getGeschlecht() + ""));
		System.out.println("TierWarteschlange sorted by Geschlecht:\n" + snake);

		//AUFGABE 2
		snake.clear();
		Arche arche = new Arche();
		Thread generator = new Thread(() -> {
			//place Tier objects in Tierwarteschlange
			while(arche.size() < TIERANZAHLAUFG2) {
				snake.add(Tiergenerator.getTier());
			}});
		Thread putter = new Thread(() -> {
			//place Tier objects from Tierwarteschlange to Arche
			while(arche.size() < TIERANZAHLAUFG2) {
				Tier t = snake.remove();
				if(t != null)
					arche.add(t);
			}});
		generator.start();
		putter.start();
		generator.join();
		putter.join();
		
		arche.printStallbelegung();
		arche.erstelleFuetterungsWarteschlangen();
	}

}
