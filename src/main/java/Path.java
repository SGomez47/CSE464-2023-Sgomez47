import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Path {

    private List<String> nodes;

    public Path() {
        nodes = new ArrayList<>();
    }

    public void addNode(String node) {
        nodes.add(node);
    }

    public List<String> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return String.join(" -> " + nodes);
    }

}
