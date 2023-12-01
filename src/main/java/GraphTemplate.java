import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.Factory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
    public abstract class GraphTemplate {

        protected abstract Set<String> getPrevNodes();

        protected abstract void addToPrevNodes(String node);

        protected abstract boolean isPrevNode(String node);

        protected abstract Iterable<Link> getNeighbors(String node);

        public Path pathSearch(String src, String dst) {
            Set<String> visitedNodes = getPrevNodes();

            Queue<Path> queue = new LinkedList<>();
            Path initialPath = new Path();
            initialPath.addNode(src);
            queue.offer(initialPath);

            while (!queue.isEmpty()) {
                Path currentPath = queue.poll();
                String currentNode = currentPath.getNodes().get(currentPath.getNodes().size() - 1);

                if (currentNode.equals(dst)) {
                    return currentPath;
                }

                if (!isPrevNode(currentNode)) {
                    addToPrevNodes(currentNode);
                    for (Link nextNode : getNeighbors(currentNode)) {
                        String neighborLabel = nextNode.name().toString();
                        if (!isPrevNode(neighborLabel)) {
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

class BFS extends GraphTemplate {

    private Set<String> prevNodes;

    public BFS() {
        prevNodes = new HashSet<>();
    }

    @Override
    protected Set<String> getPrevNodes() {
        return prevNodes;
    }

    @Override
    protected void addToPrevNodes(String node) {
        prevNodes.add(node);
    }

    @Override
    protected boolean isPrevNode(String node) {
        return prevNodes.contains(node);
    }

    @Override
    protected Iterable<Link> getNeighbors(String node) {
        return Factory.mutNode(node).links();
    }
}

class DFS extends GraphTemplate {

    //Nodes we have visited
    private Set<String> prevNode;

    public DFS() {
        prevNode = new HashSet<>();
    }

    @Override
    protected Set<String> getPrevNodes() {
        return prevNode;
    }

    @Override
    protected void addToPrevNodes(String node) {
        prevNode.add(node);
    }

    @Override
    protected boolean isPrevNode(String node) {
        return prevNode.contains(node);
    }

    @Override
    protected Iterable<Link> getNeighbors(String node) {
        return Factory.mutNode(node).links();
    }
}
