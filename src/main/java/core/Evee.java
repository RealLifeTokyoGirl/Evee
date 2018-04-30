package core;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import plugins.*;

public class Evee extends ListenerAdapter {
	
	
	/** Variables **/
	
	private static String[] botOwner = new String[] {"nick", "hostname"};

	
	/** Utility methods **/
		
	public void setOwner(String nick, String hostname) {
		botOwner[0] = nick;
		botOwner[1] = hostname;
	}
	
	public String[] getOwner() {
		return botOwner;
	}
	
	public String getOwner(int index) {
		return botOwner[index];
	}
	
	public String getOwner(String str) {
		if (str.equalsIgnoreCase("nick")) {
			return botOwner[0];
		}
		else if (str.matches("host*")) {
			return botOwner[1];
		}
		else {
			return botOwner[0];
		}
	}

	
	/** Actions on channel message **/
	
	public void onMessage(MessageEvent event) {
		
		String message = event.getMessage().toLowerCase();
		String sender = event.getUser().getNick();
		String hostname = event.getUser().getHostname();
		String channel = event.getChannel().getName();
		
		// Nicks blacklist
		if (!sender.equals(EveeMain.bot.getNick())) {
			
						
			// Decides between a set of options or gives a yes/no answer [.decide]
			if (message.startsWith(".decide")) {
				Decide.decide(event);
			}
			
			else if (message.startsWith(".notify ")) {
				Notify.setNotification(event);
			}
			
			// Returns the scoreboard, or increments it [.score (name +-number)]
			else if (message.startsWith(".score")) {
				Score.score(event);
			}
			
			// Stores, returns, or adds/deletes quotes [.q/.quote name<>(add/del) (number/search term/quote to add)]
			else if (message.startsWith(".q ") || message.startsWith(".quote ")) {
				Quotes.parseRequest(event);
			}
			
			// Rolls dice and returns a result [.roll [dice]d[sides] (repeat z)]
			else if (message.startsWith(".roll ")) {
				Dice.roll(event);
			}
			
			// Reactive
			else {
				
				// "Honk"
				if (message.toLowerCase().contains("honk")) {
					event.respondChannel(Honk.honk());
				}

				// Emotes
				if (!Emotes.OFF) {
					Emotes.checkEmotes(message, channel);
				}
			}
		}
		
		// Admin Commands
		if (Nicks.getPrimaryNick(sender).matches(botOwner[0]) || hostname.matches(botOwner[1])) {
			
			// Turn emotes off and on
			if (message.startsWith(".emotesoff")) {
				Emotes.OFF = true;
			}
			if (message.startsWith(".emoteson")) {
				Emotes.OFF = false;
			}
			
			// Quit server 
			if (message.startsWith(".quit")) {
				EveeMain.bot.stopBotReconnect();
				EveeMain.bot.sendIRC().quitServer("byebye~");
			}
		}
	}
	
	
	/** Actions on private message **/
	
	public void onPrivateMessage(PrivateMessageEvent event) {
		
		String message = event.getMessage();
		String sender = event.getUser().getNick();
		String hostname = event.getUser().getHostname();
		
		
		// Admin commands
		if (Nicks.getPrimaryNick(sender).matches(botOwner[0]) || hostname.matches(botOwner[1])) {
			
			// Send a message in a specific channel remotely [.say]
			if (message.startsWith(".say")) {
				Say.say(event, message);
			}
			
		}
		
	}
	
	
	/** Actions on join **/
	
	public void onJoin(String channel, String sender, String login, String hostname) {
		
		
	}

}
