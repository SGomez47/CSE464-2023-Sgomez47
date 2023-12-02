import guru.nidi.graphviz.model.Link;

import java.util.List;
import java.util.Random;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class RandomWalkAlgorithm implements GraphStrategy {

    @Override
    public Path search(String src, String dst) {
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
}
