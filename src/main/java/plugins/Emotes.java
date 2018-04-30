package plugins;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import core.EveeMain;
import xmlparser.ParseXML;

/**
 * Methods: 
 * 
**/

public class Emotes {
	
	private static Document xmldoc = ParseXML.buildDoc("src/main/resources/docs/emotes.xml");
	
	public static boolean OFF = false;
	
	
	public static void checkEmotes(String message, String channel) {
		
		message = message.toLowerCase();
		
		Node root = xmldoc.getElementsByTagName("emotes").item(0);
		Element rootElem = (Element) root;
		NodeList emotes = rootElem.getElementsByTagName("emote");
		
		String id;
		String name;
		Element current;
		
		for (int i = 0; i < emotes.getLength(); i++) {
			
			current = (Element) emotes.item(i);
			id = current.getElementsByTagName("id").item(0).getFirstChild().getTextContent();
			name = current.getElementsByTagName("name").item(0).getFirstChild().getTextContent();
			
			if (message.contains(name.toLowerCase())) {
				EveeMain.bot.sendIRC().message(channel, "https://static-cdn.jtvnw.net/emoticons/v1/" + id + "/3.0");
				return;
			}
		}
	}
	
}
