/**
 * 13.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 1 - Erdös Number
 * @author Cecilia Casarella & Leo Peters
 */
package erdoesNumber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

/**
 * This class can read the authors and publications from an XML file. To see an
 * example see "Input.xml"
 *
 */
public class ReadXMLFile {
	private ArrayList<Vertex> vertices;
	private ArrayList<Vertex> tempVertices;
	private Document doc;
	private File fXmlFile = new File("src/erdoesNumber/Input.xml");
	private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder dBuilder;

	public ReadXMLFile() {
		vertices = new ArrayList<Vertex>();
		tempVertices = new ArrayList<Vertex>();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Reads the XML file and will add the authors as neighbours to the startNode.
	 * Also adds the publications to the corresponding edges as stated in the XML.
	 * 
	 * @param startNode
	 * @return
	 */
	public ArrayList<Vertex> readXML(Vertex startNode) {

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		// Get all authors and publications from file
		NodeList nList = doc.getElementsByTagName("Authors");
		NodeList pList = doc.getElementsByTagName("Publications");

		for (int i = 0; i < nList.getLength(); i++) {
			// Add vertices to return and clear temporaries
			addTemporaryVertices();
			tempVertices.clear();

			// Add Erdös
			tempVertices.add(startNode);

			// Get all authors and publications from the XML file
			Element allPublications = (Element) pList.item(i);
			Element allAuthors = (Element) nList.item(i);
			NodeList authors = allAuthors.getElementsByTagName("Author");
			NodeList publications = allPublications.getElementsByTagName("Publication");
			addNeighboursAndvertices(authors, publications);
		}
		return vertices;
	}

	/**
	 * Adds neighbours to the vertices and also adds the corresponding edges with
	 * the publications
	 * 
	 * @param authors      - all authors from one chain
	 * @param publications - corresponding publications from the same chain
	 */
	private void addNeighboursAndvertices(NodeList authors, NodeList publications) {
		for (int j = 0; j < authors.getLength(); j++) {
			Element author = (Element) authors.item(j);
			Element publication = (Element) publications.item(j);
			tempVertices.add(new Vertex(author.getTextContent()));
			tempVertices.get(j).addNeighbour(
					new Edge(1, tempVertices.get(j), tempVertices.get(j + 1), publication.getTextContent()));
		}
	}

	/**
	 * Adds the temporary vertices to the vertex list that is returned at the EOF
	 */
	private void addTemporaryVertices() {
		for (Vertex v : tempVertices) {
			if (!vertices.contains(v)) {
				vertices.add(v);
			}
		}
	}
}
