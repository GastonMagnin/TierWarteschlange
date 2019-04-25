package NichtTiere;

import java.util.Collections;
import Tiere.*;

public class Main {

	public static void main(String[] args) {
		final int TIERANZAHL = 10;

		// AUFGABE 1
		TierWarteschlange snake = new TierWarteschlange();
		for (int i = 0; i < TIERANZAHL; i++) {
			snake.add(Tiergenerator.getTier());
		}
		
		// Tasks for Aufgabe 1
		System.out.println(snake);
		Collections.sort(snake, (Tier first, Tier second) -> first.getTierart().compareTo(second.getTierart()));
		System.out.println(snake);
		Collections.sort(snake, (Tier first, Tier second) -> (first.getGeschlecht()+"").compareTo(second.getGeschlecht()+""));
		System.out.println(snake);
		snake.grossesFressen();
		System.out.println(snake);
		snake.sturm();
		System.out.println(snake);
		snake.grossesFressen();
		System.out.println(snake);
		System.out.println(snake);
	}

}
