import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class BfsStrategy implements GraphStrategy{
    @Override
    public Path search(String src, String dst, MutableGraph graph) {

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
