package plugins;

import java.util.Random;
import org.pircbotx.hooks.events.MessageEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

import xmlparser.ParseXML;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 * Methods: 
 * 
**/

public class Quotes {
	
	
	private static Document xmldoc = ParseXML.buildDoc("src/main/resources/docs/quotes.xml");
	
	
//		public static void main(String[] args) throws Exception {
//			parseRequest(".q Shizoon add test");
//		}
	
	
	public static void parseRequest(MessageEvent event) {
		
		String message = event.getMessage().trim();
		
		if (message.startsWith(".quote ")) {
			message = message.substring(7);
		}
		else if (message.startsWith(".q ")) {
			message = message.substring(3);
		}
		String[] split = message.split(" ", 2);
		
		String username = split[0];
		username = Nicks.getPrimaryNick(username);
		
		if (split.length > 1) {
			message = split[1];
		}
		else if (split.length == 1) {
			message = "0";
		}
		
		if (message.matches("\\d+")) {
			event.respondChannel(readQuote(username, Integer.parseInt(message)));
			return;
		}
		else if (message.toLowerCase().startsWith("add ")) {
			message = message.substring(4);
			event.respondChannel(addQuote(username, message));
			return;
		}
		else if (message.toLowerCase().matches("del \\d+")) {
			message = message.substring(4);
			event.respondChannel(delQuote(username, Integer.parseInt(message)));
			return;
		}
		else if (message.toLowerCase().startsWith("search ")) {
			//trim search and send to searchQuote
			return;
		}
		else {
			//send to searchQuote without trimming
			return;
		}
	}
	
	
	private static void writeDoc() {
		
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			Source input = new DOMSource(xmldoc);
			Result output = new StreamResult(new File("docs/quotes.xml"));
			
			StringWriter writer = new StringWriter();
			transformer.transform(input, new StreamResult(writer));
			String str = writer.getBuffer().toString().replaceAll("\n|\r|\t", "");
			str = str.replaceAll("<user name=", "\n\t<user name=").replaceAll("<quote id=", "\n\t\t<quote id=").replaceAll("</quote>", "</quote>\n")
					.replaceAll("</user>", "\n\t</user>\n").replaceAll("</users>", "\n</users>").replaceAll("\n\n", "\n");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();  
			DocumentBuilder builder = docFactory.newDocumentBuilder();  
	        xmldoc = builder.parse(new InputSource(new StringReader(str))); 
			
			input = new DOMSource(xmldoc);
			transformer.transform(input, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String readQuote(String username, int number) {
		
		Random random = new Random();
		
		Node root = xmldoc.getElementsByTagName("users").item(0);
		Element rootElem = (Element) root;
		NodeList users = rootElem.getElementsByTagName("user");
		
		for (int i = 0; i < users.getLength(); i++) {
			if (users.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(username)) {
				
				Element userElem = (Element) users.item(i);
				NodeList quotes = userElem.getElementsByTagName("quote");
				
				if (quotes.getLength() == 0) {
					return "I don't have any quotes for this user!";
				}
				if (number == 0) {
					number = random.nextInt(quotes.getLength()) + 1;
				}
				for (int j = 0; j < quotes.getLength(); j++) {
					if (Integer.parseInt(quotes.item(j).getAttributes().getNamedItem("id").getNodeValue()) == number) {
						return quotes.item(j).getTextContent().trim();
					}
				}
				return "I don't have that many quotes for " + username + "!";
			}
		}
		return "I don't have any quotes for this user!";	
	}	
	
	
	public static String addQuote(String username, String message) {
				
		Node root = xmldoc.getElementsByTagName("users").item(0);
		Element rootElem = (Element) root;
		NodeList users = rootElem.getElementsByTagName("user");
		
		for (int i = 0; i < users.getLength(); i++) {
			if (users.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(username)) {
				
				Element userElem = (Element) users.item(i);
				NodeList quotes = userElem.getElementsByTagName("quote");
				
				Element newQuote = xmldoc.createElement("quote");
				newQuote.setAttribute("id", Integer.toString(quotes.getLength() + 1));
				// set date
				newQuote.setTextContent(message);
				
				userElem.appendChild(newQuote);
				writeDoc();
				return "New quote added for " + username + "! (ID: " + Integer.toString(quotes.getLength()) + ")";
			}
		}
		
		Element newUser = xmldoc.createElement("user");
		newUser.setAttribute("name", username);
		root.appendChild(newUser);
		return addQuote(username, message);
	}
	
	public static String delQuote(String username, int number) {
		return "";
	}
}
