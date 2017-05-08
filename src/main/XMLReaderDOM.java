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

public class XMLReaderDOM
{
	public static void run()
	{
		String filePath = "src/main/bestelling.xml";
		File xmlFile = new File(filePath);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder;

		try
		{
			dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("bestelling");

			List <Order> orderList = new ArrayList <>();

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				orderList.add(getOrder(nodeList.item(i)));
			}

			for (Order ord : orderList)
			{
				System.out.println(ord.toString());
			}
		}
		catch (SAXException | ParserConfigurationException | IOException e1)
		{
			e1.printStackTrace();
		}
	}

	private static Order getOrder(Node node)
	{
		Order ord = new Order ();
		if (node.getNodeType() == Node.ELEMENT_NODE)
		{
			Element element = (Element) node;

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

		return ord;
	}

	private static String getTagValue (String tag, Element element)
	{
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = nodeList.item(0);
		return node.getNodeValue();
	}
}
