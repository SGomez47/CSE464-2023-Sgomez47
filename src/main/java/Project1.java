import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.*;
import guru.nidi.graphviz.engine.*;

import java.io.*;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.model.MutableGraph.*;

public class Project1 {
    private MutableGraph graph;



    public void parseGraph(String filepath) throws IOException {
       FileInputStream input = new FileInputStream(filepath);
       graph = new Parser().read(input);
       //System.out.println(graph.toString());
    }

    public void outputGraph(String filepath) throws IOException{
        File output = new File(filepath);
        Graphviz.fromGraph(graph).render(Format.DOT).toFile(output);
    }
    public void addNode(String label) {
        MutableNode graphNode = mutNode(label);
        graph.add(graphNode);
    }

    public String toString(){
       return graph.toString();
    }


}
