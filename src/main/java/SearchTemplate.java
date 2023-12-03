import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutNode;

public abstract class SearchTemplate {

    public MutableGraph path;

    public SearchTemplate(MutableGraph path) {
        this.path = path;
    }

    public abstract Path treeSearch(String src, String dst);

}

