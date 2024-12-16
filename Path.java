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

    public Path pathGraphSearch(String src, String dst) {
        // Implement DFS search
        Set<String> lastNode = new HashSet<>();
        Stack<Path> stack = new Stack<>();

        Path initialPath = new Path();
        initialPath.addNode(src);
        stack.push(initialPath);

        while (!stack.isEmpty()) {
            Path currentPath = stack.pop();
            String currentNode = currentPath.getNodes().get(currentPath.getNodes().size() - 1);

            if (currentNode.equals(dst)) {
                return currentPath; // Found a path to the destination
            }

            if (!lastNode.contains(currentNode)) {
                lastNode.add(currentNode);
                for (Link nextNode : mutNode(currentNode).links()) {
                    String neighborLabel = nextNode.name().toString();
                    if (!lastNode.contains(neighborLabel)) {
                        Path newPath = new Path();
                        newPath.getNodes().addAll(currentPath.getNodes());
                        newPath.addNode(neighborLabel);
                        stack.push(newPath);
                    }
                }
            }
        }
    return null;
    }
}
