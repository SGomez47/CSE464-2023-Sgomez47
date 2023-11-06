import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableNode;

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
            //Create the queue for first in first out
            Queue<Path> queue = new LinkedList<>();
            Set<String> lastNodes = new HashSet<>();
            Path path = new Path();
            path.addNode(src);
            queue.offer(path);

            //while the queue isn't empty we search through the graph nodes to find the fastest path to the
            //destination node
            while (!queue.isEmpty()) {
                Path currentPath = queue.poll();
                String currentNode = currentPath.getNodes().get(currentPath.getNodes().size() - 1);

                if (currentNode.equals(dst)) {
                    //path was found and output
                    return currentPath;
                }

                if (!lastNodes.contains(currentNode)) {
                    lastNodes.add(currentNode);
                    for (Link nextNode : mutNode(currentNode).links()) {
                        String neighborLabel = nextNode.name().toString();
                        if (!lastNodes.contains(neighborLabel)) {
                            Path newPath = new Path();
                            newPath.getNodes().addAll(currentPath.getNodes());
                            newPath.addNode(neighborLabel);
                            queue.offer(newPath);
                        }
                    }
                }
            }
            return null; // No path found
    }
}
