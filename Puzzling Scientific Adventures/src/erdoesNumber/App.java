/**
 * 13.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 1 - Erdös Number
 * @author Cecilia Casarella & Leo Peters
 */

package erdoesNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * This Application uses Dijkstra to search for the ErdösNumber <a href="https://en.wikipedia.org/wiki/Erd%C5%91s_number">(ErdösNumber)</a>
 * It prints the number and the chain with all publications from the authors. The vertices and edges are read from an XML-File "Input.xml"
 * 
 */
public class App {

	/**
	 * 
	 * @param source
	 * @param dest
	 */
	public static void printchain(Vertex source, Vertex dest) {
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.computePath(source);
		Vertex start;
		List<Vertex> l = dijkstra.getShortestPathTo(dest);
		ListIterator<Vertex> iterator = l.listIterator();
		System.out.println("Erdoes Number: " + (l.size() - 1));

		if (iterator.hasNext()) {
			start = iterator.next();
		} else {
			start = null;
		}
		while (iterator.hasNext()) {
			Vertex c = start;
			System.out.print(c.getName());
			if (iterator.hasNext()) {
				start = iterator.next();
				for (Edge e : c.getEdges()) {
					if (e.getTargetVertex().equals(start)) {
						System.out.println(" & " + start.getName());
						System.out.println(e.getPaper());
					}
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		ReadXMLFile xml = new ReadXMLFile();
		Vertex v0 = new Vertex("P. Erdös");
		ArrayList<Vertex> vertices = xml.readXML(v0);

		printchain(v0, vertices.get(6));

	}
}
