package plugins;

import java.util.Random;

import org.pircbotx.hooks.events.MessageEvent;

/**
 * Methods:
 * decide(String message); [.decide] - decide between a set of options or gives a yes/no answer
**/

public class Decide {
	
	static Random random = new Random();

	public static void decide(MessageEvent event) {
		
			String[] input = event.getMessage().substring(8).split(" or ");
			if (input.length == 1) {
				boolean rdm = random.nextBoolean();
				if (rdm) {
					event.respondChannel("haihai~");
				}
				else {
					event.respondChannel("iie!");
				}
			}
			else if (input.length > 1) {
				int rdm = random.nextInt(input.length);
				event.respondChannel(input[rdm]);
			}
			else {
				event.respondChannel("I'm sorry, I didn't understand ;_;");
			}
	}
}
