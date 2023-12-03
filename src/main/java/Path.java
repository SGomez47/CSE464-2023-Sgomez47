import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.*;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class Path {

    public enum Algorithm {
        BFS,
        DFS,
        RANDOM_WALK
    }

    private static List<String> nodes;

    private GraphStrategy searchAlgorithm;

    public Path(GraphStrategy searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
    public Path() {
        nodes = new ArrayList<>();
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    public static List<String> getNodes() {
        return nodes;
    }

    public MutableNode locateNode(MutableGraph graph, String node){
        return graph.nodes().stream().filter(n -> n.name().toString().equals(node)).toList().get(0);
    }

    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }

    private String getNextNode(String currentNode) {
        List<Link> neighbors = mutNode(currentNode).links();
        if (neighbors.isEmpty()) {
            return null;
        }

        Random rand = new Random();
        Link randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
        return randomNeighbor.name().toString();
    }

}




