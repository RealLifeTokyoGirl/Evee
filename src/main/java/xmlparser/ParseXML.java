package xmlparser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Methods: 
 * buildDoc(String file); - builds and returns a DOM xml document from the provided file path
**/

public class ParseXML {
	
	public static Document buildDoc(String file) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmldoc = builder.parse(file);
		xmldoc.normalize();
		return xmldoc;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}		
		return null;
	}

}
