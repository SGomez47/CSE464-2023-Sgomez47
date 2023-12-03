import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class DfsSearch extends SearchTemplate {

    private Stack<String> stack;

    public DfsSearch(MutableGraph path) {
        super(path);
        this.stack = new Stack<>();
    }


    String getCurrentNode() {
        return stack.pop();
    }

    String getNextNode(List<Link> neighbors) {
        for (Link neighbor : neighbors) {
            String neighborLabel = neighbor.name().toString();
            if (!path.nodes().contains(neighborLabel)) {
                stack.push(neighborLabel);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
    @Override
    public Path treeSearch(String src, String dst) {
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
    }
}
