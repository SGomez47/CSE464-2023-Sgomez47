import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.*;


import java.util.*;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Factory.node;

public class Path {

    private static List<String> nodes;

    public Path() {
        nodes = new ArrayList<>();
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    public static List<String> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return String.join(" -> " + nodes);
    }

}
