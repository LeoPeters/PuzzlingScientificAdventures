/**
 * 13.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 1 - Erd�s Number
 * @author Cecilia Casarella & Leo Peters
 */
package erdoesNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * The Vertices for this Dijkstra hold an Author by his/her name.
 *
 */
public class Vertex implements Comparable<Vertex> {
    private String name;
    private List<Edge> edges;
    private boolean visited;
    private Vertex previosVertex;
    private double minDistance = Double.MAX_VALUE;

    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addNeighbour(Edge edge) {
        this.edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPreviosVertex() {
        return previosVertex;
    }

    public void setPreviosVertex(Vertex previosVertex) {
        this.previosVertex = previosVertex;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.minDistance, otherVertex.minDistance);
    }
}