package erdoesNumber;

public class Edge {
    private double weight;
    private String paper;
    private Vertex startVertex;
    private Vertex targetVertex;

    public Edge(double weight, Vertex startVertex, Vertex targetVertex, String paper) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
        this.paper = paper;
    }

    public double getWeight() {
        return weight;
    }

    public String getPaper() {
        return paper;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }
}