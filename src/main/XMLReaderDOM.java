package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by: Ian Hildebrand
 * Date: 08-May-17.
 */

class XMLReaderDOM
{
	private ArrayList<Order> orderList = new ArrayList<>();

	/**
	 * will run XMLReaderDOM with "src/main/bestelling.xml" as path.
	 */
	XMLReaderDOM()
	{
		this("src/main/bestelling.xml");
	}

	/**
	 * this will read the XML file
	 * @param pathToFile
	 * The path to the file it needs to read.
	 */
	XMLReaderDOM(String pathToFile)
	{
		// file path to the xml file
		File xmlFile = new File(pathToFile);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder;

		try
		{
			dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			//file is now loaded into the memory (do not use large files)

			// Separate the list on this xml tag.
			NodeList nodeList = doc.getElementsByTagName("bestelling");
			List <Order> orderList = new ArrayList <>();

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				orderList.add(getOrder(nodeList.item(i)));
			}

			ArrayList <Order> orders = new ArrayList<>();
			orders.addAll(orderList);
			this.orderList = orders;
		}
		catch (SAXException | ParserConfigurationException | IOException e1)
		{
			e1.printStackTrace();
		}
	}

	/**
	 * This returns the information that was in the XML file.
	 * @return ArrayList<Order>
	 * A list with all information that was in the XML file.
	 */
	ArrayList<Order> getOrderList() {
		return orderList;
	}

	/**
	 * extracts the information from the xml file.
	 * @param node the selected peace of the xml (bestelling)
	 * @return Order object.
	 */
	private static Order getOrder(Node node)
	{
		Order ord = new Order ();
		if (node.getNodeType() == Node.ELEMENT_NODE)
		{
			Element element = (Element) node;

			//add the values of the tags to the object.
			ord.setOrderId(Integer.parseInt(getTagValue("ordernummer", element)));
			ord.setName(getTagValue("voornaam", element));
			ord.setSurName(getTagValue("achternaam", element));
			ord.setAddress(getTagValue("adres", element));
			ord.setZipcode(getTagValue("postcode", element));
			ord.setPlace(getTagValue("plaats", element));
			ord.setDate(getTagValue("datum", element));

			NodeList artids = element.getElementsByTagName("artikelnr");
			ArrayList <Integer> articleId = new ArrayList<>();
			for (int i = 0; i < artids.getLength(); i++) {
				articleId.add(Integer.parseInt(artids.item(i).getTextContent()));
			}

			ord.setArticleId(articleId);
		}

		// return object
		return ord;
	}

	/**
	 * gets the tag's value
	 * @param tag tag name
	 * @param element node element list
	 * @return Value of the tag
	 */
	private static String getTagValue (String tag, Element element)
	{
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = nodeList.item(0);
		return node.getNodeValue();
	}
}
