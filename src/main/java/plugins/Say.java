package plugins;

import org.pircbotx.Colors;
import org.pircbotx.hooks.events.PrivateMessageEvent;

/**
 * Methods: 
 * say(PrivateMessageEvent event, String message); [.say #channel] - repeats a message in channel which has been relayed through pm
**/

public class Say {
		
	public static void say(PrivateMessageEvent event, String message) {
		
		if (message.indexOf('#') == 5) {
			String channel = message.substring(message.indexOf('#'), message.indexOf(' ', 5));
			event.getBot().sendIRC().message(channel, message.substring(message.indexOf(' ', 5) + 1));
		}
		else {
			event.respondWith("I'm sorry, I couldn't understand!! ;_;	--	 FORMAT:" + Colors.UNDERLINE + ".say #channel Your message here.");
		}
	} 
}