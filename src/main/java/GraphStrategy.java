import guru.nidi.graphviz.model.MutableGraph;

import java.util.List;

public interface GraphStrategy {
    Path search(String src, String dst, MutableGraph graph);
}
