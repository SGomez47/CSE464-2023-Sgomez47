import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.*;
import guru.nidi.graphviz.engine.*;

import java.io.*;

import static guru.nidi.graphviz.model.Factory.*;

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

    public void addNodes(String[] label){
        for (String nodeLabel : label) {
            addNode(nodeLabel);
        }
    }

    public void addEdge(String srcLabel, String dstLabel){
        graph.add(Factory.mutNode(srcLabel).addLink(dstLabel));
    }

    public String toString(){
       return graph.toString();
    }


}
