package Tiere;

import java.util.Random;

public class Tiergenerator {	
	private static final char MALE = 'M';
	private static final char FEMALE = 'W';

	private static String [] raubvogel = {"Adler", "Falke", "Eule"};
	private static String[] vogel = {"Huhn", "Papagei", "Amsel", "Taube", "Specht", "Fink"};
	
	private static String[] raubSaeuger = { "Löwe", "Tiger", "Bär", "Leopard"};
	private static String[] saeuger = {"Ziege", "Schaf", "Kuh", "Affe", "Giraffe"};
	
	private static String[] giftReptil = {"Kobra", "Kreuzotter"};
	private static String[] raubReptil = {"Phyton", "Krokodil"};
	private static String[] reptil = {"Schildkröte", "Echse"};
	
	
	public static Tier getTier() {
		Random rand = new Random();
		char geschlecht;
		String[] tierart;
		int alter;
		int gewicht;


		boolean raubtier = rand.nextBoolean();

		if (rand.nextBoolean()) {
			geschlecht = MALE;
		}
		else {
			geschlecht = FEMALE;
		}


		switch (rand.nextInt(3)) {
			//Säuger
			case 0:	
				if (raubtier) {
					tierart = raubSaeuger;
					gewicht = rand.nextInt(50)+5;
				}
				else {
					tierart = saeuger;
					gewicht = rand.nextInt(15)+5;
				}
				alter = rand.nextInt(8);
				return new Saeuger(tierart[rand.nextInt(tierart.length)], alter, gewicht, geschlecht, raubtier);			
			
			//Reptil
			case 1: 
				boolean giftig = rand.nextBoolean();
				if (giftig) {
					tierart = giftReptil;
					gewicht = rand.nextInt(6);
					raubtier = true;
				} else if (raubtier) {
					tierart = raubReptil;
					gewicht = rand.nextInt(50)+5;
				}
				else {
					tierart = reptil;
					gewicht = rand.nextInt(6);
				}
				alter = rand.nextInt(8);								
				return new Reptil(tierart[rand.nextInt(tierart.length)], alter, gewicht, geschlecht, raubtier, giftig);			
			
			//Vogel
			case 2: 
				if (raubtier) {
					tierart = raubvogel;
				}
				else {
					tierart = vogel;
				}
				alter = rand.nextInt(8);				
				gewicht = 1;
				return new Vogel(tierart[rand.nextInt(tierart.length)], alter, gewicht, geschlecht, raubtier);
			
			default: return null;		
		}
	}
	
}
