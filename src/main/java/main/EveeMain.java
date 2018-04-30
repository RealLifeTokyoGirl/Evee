package main;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class EveeMain {
	
    public static PircBotX bot;
    
    static Evee Evee = new Evee();

	public static void main(String[] args) throws Exception {
    	
    	// Configure bot
    	Configuration config = new Configuration.Builder()
    			
    		// Set nick
    		.setName("Evee")
    		
    		// Set login portion of hostname [nick!login@host]
    		.setLogin("Evee")
    		
    		//Set real name
    		.setRealName("Evee")
    		
    		//Set nickserv password
    		.setNickservPassword("swordfish")
    		
    		
    		// Set auto-rejoin
    		.setAutoReconnect(true)
    		
    		// Do not join channels until identified
    		.setNickservDelayJoin(true)
    		
    		
    		// Add main listener class
    		.addListener(Evee)
    		
    		// Set server to connect to
    		.addServer("irc.rizon.net")
    		
    		// Add channels to connect on launch
    		.addAutoJoinChannel("#ShizoonsSecretFort", "love")
    		.addAutoJoinChannel("#KSGGames")
    		
    	.buildConfiguration();
    	
    		// Set owner (nick, hostname regex)
    		Evee.setOwner("Mari", "~uid25401@*.irccloud.com");
    		
    	
    	bot = new PircBotX(config);
    	
    	// Run bot
    	bot.startBot();
    		
    }
	
}
