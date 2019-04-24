package NichtTiere;

import Tiere.*;

public class Main {

	public static void main(String[] args) {
		final int TIERANZAHL = 10;
		//AUFGABE 1

		TierWarteschlange snake = new TierWarteschlange();
		for(int i = 0; i < TIERANZAHL; i++) {
			snake.add(Tiergenerator.getTier());
		}
		snake.printWarteschlange();
		snake.grossesFressen();
		snake.printWarteschlange();
		snake.sturm();
		snake.printWarteschlange();
		snake.grossesFressen();
		snake.printWarteschlange();
	}

}
