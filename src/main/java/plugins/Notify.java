package plugins;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.pircbotx.hooks.events.MessageEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import xmlparser.ParseXML;

/**
 * Methods: 
 * 
**/

public class Notify {
	
	//private variables
	private static Document xmldoc = ParseXML.buildDoc("src/main/resources/docs/notifications.xml");
	
	
	public static void main(String[] args){
		String timeStamp = new SimpleDateFormat("EEEE, MMMM d 'at' HH:mm z").format(new Date());
		System.out.println(timeStamp);
	}
	
	
	public static void setNotification(MessageEvent event) {
		
		String message = event.getMessage().trim();
		String sentBy = event.getUser().getNick();
		String timestamp = new SimpleDateFormat("EEEE, MMMM d 'at' HH:mm z").format(new Date());
		
		String[] array = message.split(" ", 3);
		String targetUser = array[1];
		String messageContent = array[2];
		
		addNotification(targetUser, sentBy, timestamp, messageContent);
		writeDoc();
	}
	
	
	public static void addNotification(String targetUser, String from, String timestamp, String message) {
		
		Node root = xmldoc.getElementsByTagName("notifications").item(0);
		
		Element newNotification = xmldoc.createElement("notification");
		newNotification.setAttribute("targetUser", targetUser);
		
		Element messageElem = xmldoc.createElement("message");
		messageElem.setAttribute("from", from);
		messageElem.setAttribute("timestamp", timestamp);
		messageElem.setTextContent(message);
		newNotification.appendChild(messageElem);
		
		root.appendChild(newNotification);
	}
	
	
	private static void writeDoc() {
		
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			Source input = new DOMSource(xmldoc);
			Result output = new StreamResult(new File("docs/notifications.xml"));
			
			StringWriter writer = new StringWriter();
			transformer.transform(input, new StreamResult(writer));
			String str = writer.getBuffer().toString().replaceAll("\n|\r|\t", "");
			str = str.replaceAll("<notification targetUser=", "\n\t<notification targetUser=").replaceAll("<message from=", "\n\t\t<message from=").replaceAll("</message>", "</message>\n")
					.replaceAll("</notification>", "\n\t</notification>\n").replaceAll("</notifications>", "\n</notifications>").replaceAll("\n\n", "\n");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();  
			DocumentBuilder builder = docFactory.newDocumentBuilder();  
	        xmldoc = builder.parse(new InputSource(new StringReader(str))); 
			
			input = new DOMSource(xmldoc);
			transformer.transform(input, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
