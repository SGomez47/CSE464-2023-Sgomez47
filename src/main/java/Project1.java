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
    //parseGraph parses the input dot into a graph
    public void parseGraph(String filepath) throws IOException {
       FileInputStream input = new FileInputStream(filepath);
       graph = new Parser().read(input);
       //System.out.println(graph.toString());
    }

    //Outputs the graph parsed into a text file (output.txt) located in resource folder
    public void outputGraph(String filepath) throws IOException{
        File output = new File(filepath);
        BufferedWriter fileOutput = new BufferedWriter(new FileWriter(filepath));

        String outputGraph = toString();
        fileOutput.write(outputGraph);
        fileOutput.close();
    }

    //toString that prints out the number of nodes, nodes, number of edges and the graph with edges
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
    //adds a node and doesn't add duplicate this was done automatically for my graph
    //Windows 11
    public void addNode(String label) {
        MutableNode graphNode = mutNode(label);
        graph.add(graphNode);
    }
    //adds nodes from a list of nodes
    public void addNodes(String[] label){
        for (String nodeLabel : label) {
            addNode(nodeLabel);
        }
    }
    //------------------------------------------------
    //Feature 3
    //adds edges and automatically removes duplicates
    public void addEdge(String srcLabel, String dstLabel){
        MutableNode node = mutNode(srcLabel);
        graph.add(node.addLink(dstLabel));
    }
    //------------------------------------------------
    //Feature 4
    //uses the graph and outputs the graph into a dot file
    public void outputDotGraph(String path) throws IOException {
        Graphviz.fromGraph(graph).width(200).render(Format.DOT).toFile(new File(path));
    }
    //outputs the graph into a png
    public void outputGraphics(String path, String format) throws IOException{
        if(format.equals("PNG")) {
            Graphviz.fromGraph(graph).width(200).render(Format.PNG).toFile(new File(path));
        }
    }
}
