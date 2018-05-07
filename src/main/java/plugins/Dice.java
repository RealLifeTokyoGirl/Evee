package plugins;

import java.util.Random;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Methods: 
 * 
**/

public class Dice {
	
	private static Random random = new Random();
	
	public static void roll(MessageEvent event) {
		
		String message = event.getMessage().substring(6).trim();
		
		if (message.startsWith("d")) {			
			message = "1" + message;
		}
		
		if (message.matches("\\d+[d]\\d+( repeat \\d+)?")) {			
			String[] args = message.split(" ");
			String[] diceSides = args[0].split("d");
			int dice = Integer.parseInt(diceSides[0]);
			int sides = Integer.parseInt(diceSides[1]);
			int repeats = 1;
			int rdm;
			String output = "[";
			
			if (args.length == 3) {	
				repeats = Integer.parseInt(args[2]);
			}
			if (dice > 32 || sides > 100000 || repeats > 16) {
				event.respondChannel("Hey!! One of your inputs is waaay too big! Please keep your requests reasonable to avoid spamming!");
				return;
			}
			
			for (int i = 0; i < repeats; i++) {
				for (int j = 0; j < dice;) {
					rdm = random.nextInt(sides) + 1;
					output = output + rdm;
					j++;
					if (j < dice) {
						output = output + ", ";
					}
					else if (j == dice) {
						output = output + "]";
					}
				}
				event.respondChannel(output);
				output = "[";
			}
		}
		
		else {
			event.respondChannel("I'm sorry, I couldn't understand!! ;_;	--	 FORMAT:" + Colors.UNDERLINE + ".roll [number of dice]d[sides] (repeat [number of times])");
		}
		
	}
	
}
