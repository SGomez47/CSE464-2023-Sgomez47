import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutNode;

public abstract class SearchTemplate {

    public MutableGraph path;

    public SearchTemplate(MutableGraph path) {
        this.path = path;
    }

    /*public Path search(String src, String dst) {
        path.addNode(src);

        while (!path.getNodes().isEmpty()) {
            String currentNode = getCurrentNode();
            if (currentNode.equals(dst)) {
                return path;
            }

            List<Link> neighbors = getNeighbors(currentNode);
            String nextNode = getNextNode(neighbors);
            if (nextNode == null) {
                break;  // No more neighbors, terminate the search
            }

            path.addNode(nextNode);
        }

        return null;
    }*/


    public abstract Path treeSearch(String src, String dst);

}

