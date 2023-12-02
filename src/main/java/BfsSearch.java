
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

import java.util.*;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class BfsSearch extends SearchTemplate {

    public Queue<String> queue;

    public BfsSearch(MutableGraph path) {
        super(path);
        this.queue = new LinkedList<>();
    }


    public String getCurrentNode() {
        return queue.poll();
    }


    public String getNextNode(List<Link> neighbors) {
        for (Link neighbor : neighbors) {
            String neighborLabel = neighbor.name().toString();
            if (!path.nodes().contains(neighborLabel)) {
                queue.offer(neighborLabel);
            }
        }
        return queue.peek();
    }

    @Override
    public Path treeSearch(String src, String dst) {

        Queue<Path> queue = new LinkedList<>();
        Set<String> lastNodes = new HashSet<>();
        Path path = new Path();
        path.addNode(src);
        queue.offer(path);

        while (!queue.isEmpty()) {
            Path currentPath = queue.poll();
            String currentNode = currentPath.getNodes().get(currentPath.getNodes().size() - 1);

            if (currentNode.equals(dst)) {
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
        return null;
    }

}
