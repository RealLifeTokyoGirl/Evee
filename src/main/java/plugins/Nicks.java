package plugins;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlparser.ParseXML;

public class Nicks {
	
	
	public static String getPrimaryNick(String input) {
		
		Node root = ParseXML.buildDoc("src/main/resources/docs/nicklist.xml").getElementsByTagName("nicklist").item(0);
		Element rootElem = (Element) root;
		NodeList users = rootElem.getElementsByTagName("user");
		
		NodeList nicks;
		Element currentUser;
		
		for (int i = 0; i < users.getLength(); i++) {
			currentUser = (Element) users.item(i);
			nicks = currentUser.getElementsByTagName("nick");
			for (int j = 0; j < nicks.getLength(); j++) {
				if (nicks.item(j).getTextContent().toLowerCase().equals(input.toLowerCase())) {
					return nicks.item(j).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
				}
			}
		}
		return input;
	}

}
