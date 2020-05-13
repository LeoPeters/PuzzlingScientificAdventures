package erdoesNumber;

import java.util.ListIterator;

public class App {

    public static void printchain(Vertex source ,Vertex dest){
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.computePath(source);
        Vertex start;
        ListIterator<Vertex> iterator = dijkstra.getShortestPathTo(dest).listIterator();


        if(iterator.hasNext()) {
            start = iterator.next();
        }
        else{
            start = null;
        }
        while(iterator.hasNext()){
            Vertex c = start;
            System.out.println(c.getName());
            if(iterator.hasNext()) {
                start = iterator.next();
                for (Edge e : c.getEdges()) {
                    if (e.getTargetVertex().equals(start)) {
                        System.out.println(e.getPaper());
                        System.out.println(start.getName());
                    }

                }
            }
        }

    }
    public static void main(String[] args) {


        Vertex v0 = new Vertex("Erdos");
        Vertex v1 = new Vertex("A. Pinkus");
        Vertex v2 = new Vertex("A. Wintner");
        Vertex v3 = new Vertex("S. Chowla");
        Vertex v4 = new Vertex("S. Karlin");
        Vertex v5 = new Vertex("N. Wiener");
        Vertex v6 = new Vertex("A. Selberg");
        Vertex v7 = new Vertex("P. Franklin");
        Vertex v8 = new Vertex("P. ssss");


        v0.addNeighbour(new Edge(1, v0, v1, "The closed linear span of{xk−ck}∞1,J. Approx. Theory43(1985) no. 1, 75–80 (J. M. Anderson;A. Pinkus; O. Shisha);MR86m:41005;Zbl.576.41022"));
        v0.addNeighbour(new Edge(1, v0, v2, "boh"));
        v0.addNeighbour(new Edge(1, v0, v3, "boh1"));
        v3.addNeighbour(new Edge(1, v3, v6, "boh2"));
        v6.addNeighbour(new Edge(1, v6, v7, "boh3"));
        v7.addNeighbour(new Edge(1, v7, v8, "boh4"));



        printchain(v0, v8);



    }
}
