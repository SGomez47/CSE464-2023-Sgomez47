import guru.nidi.graphviz.model.Link;
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

    @Override
    public String toString() {
        Collections.reverse(nodes);
        return String.join(" -> ", nodes);
    }


    /*private Path bfs(String src, String dst) {

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
    }*/

    private String getNextNode(String currentNode) {
        List<Link> neighbors = mutNode(currentNode).links();
        if (neighbors.isEmpty()) {
            return null;  // No neighbors
        }

        // Choose a random neighbor
        Random rand = new Random();
        Link randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
        return randomNeighbor.name().toString();
    }

    /*private Path dfs(String src, String dst) {
        Set<String> lastNode = new HashSet<>();
        Stack<Path> stack = new Stack<>();

        Path initialPath = new Path();
        initialPath.addNode(src);
        stack.push(initialPath);

        while (!stack.isEmpty()) {
            Path currentPath = stack.pop();
            String currentNode = currentPath.getNodes().get(currentPath.getNodes().size() - 1);

            if (currentNode.equals(dst)) {
                return currentPath;
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
    }*/

    private Path randomWalk(String src, String dst) {
        // Implementation of the random walk algorithm
        Path path = new Path();
        path.addNode(src);

        String current = src;
        while (!current.equals(dst)) {
            List<Link> neighbors = mutNode(current).links();
            if (neighbors.isEmpty()) {
                // No neighbors, terminate the search
                break;
            }

            // Choose a random neighbor
            Random rand = new Random();
            Link randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
            String neighborLabel = randomNeighbor.name().toString();

            path.addNode(neighborLabel);
            current = neighborLabel;
        }

        return path;
    }


    private List<Link> getNeighbors(String nodeLabel) {
        List<Link> neighbors = new ArrayList<>();
        for (Link link : mutNode(nodeLabel).links()) {
            neighbors.add(link);
        }
        return neighbors;
    }
}




