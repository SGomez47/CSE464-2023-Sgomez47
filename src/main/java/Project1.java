import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.*;
import guru.nidi.graphviz.engine.*;

import java.io.*;
import java.util.Arrays;

import static guru.nidi.graphviz.model.Factory.*;

public class Project1 {
    private MutableGraph graph;

    //---------------------------------------------
    //Feature 1
    public void parseGraph(String filepath) throws IOException {
       FileInputStream input = new FileInputStream(filepath);
       graph = new Parser().read(input);
       //System.out.println(graph.toString());
    }

    public void outputGraph(String filepath) throws IOException{
        File output = new File(filepath);
        BufferedWriter fileOutput = new BufferedWriter(new FileWriter(filepath));

        String outputGraph = toString();
        fileOutput.write(outputGraph);
        fileOutput.close();
    }

    public String toString(){
        String[] nodes = new String[graph.nodes().size()];
        int i = 0;
        for(MutableNode outputNode : graph.nodes()){
            nodes[i] = outputNode.name().toString();
            i++;
        }
        Arrays.sort(nodes);

        String numNode = "Number of Nodes: " + graph.nodes().size() + "\n";
        String numEdges = "Number of Edges: " + graph.edges().size() + "\n";
        return numNode + "Nodes: " + Arrays.toString(nodes) + "\n" + numEdges + "\n" + graph.toString();
    }
    //------------------------------------------------
    //Feature 2
    public void addNode(String label) {
        MutableNode graphNode = mutNode(label);
        graph.add(graphNode);
    }

    public void addNodes(String[] label){
        for (String nodeLabel : label) {
            addNode(nodeLabel);
        }
    }
    //------------------------------------------------
    //Feature 3
    public void addEdge(String srcLabel, String dstLabel){
        MutableNode node = mutNode(srcLabel);
        graph.add(node.addLink(dstLabel));
    }
    //------------------------------------------------
    //Feature 4

    public void outputDotGraph(String path) throws IOException {
        Graphviz.fromGraph(graph).width(200).render(Format.DOT).toFile(new File(path));
    }

    public void outputGraphics(String path, String format) throws IOException{
        if(format.equals("PNG")) {
            Graphviz.fromGraph(graph).width(200).render(Format.PNG).toFile(new File(path));
        }
    }
}
