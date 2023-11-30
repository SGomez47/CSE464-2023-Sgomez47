import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.*;
import guru.nidi.graphviz.engine.*;

import java.io.*;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;

public class Project1 {
    private MutableGraph graph;

    //Refactor #4 Extract Variable
    boolean nodeExist = false;

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

    public MutableNode getNode(String label) {
        for (MutableNode node : graph.nodes()) {
            if (node.name().toString().equals(label)) {
                return node;
            }
        }
        return null; // Return null if the node with the given label does not exist.
    }

    public void outputNewGraph(String filepath, MutableGraph newGraph) throws IOException{
        File output = new File(filepath);
        BufferedWriter fileOutput = new BufferedWriter(new FileWriter(filepath));


        String[] nodes = new String[newGraph.nodes().size()];
        int i = 0;
        for(MutableNode outputNode : newGraph.nodes()){
            nodes[i] = outputNode.name().toString();
            i++;
        }
        Arrays.sort(nodes);

        String numNode = "Number of Nodes: " + newGraph.nodes().size() + "\n";
        String numEdges = "Number of Edges: " + newGraph.edges().size() + "\n";
        String outputGraph =  numNode + "Nodes: " + Arrays.toString(nodes) + "\n" + numEdges + "\n" + newGraph.toString();

        //String outputGraph = outputString(newGraph, nodes);

        fileOutput.write(outputGraph);
        fileOutput.close();
    }

    //removes the nodes input by user and deletes any edges with the node
    public void removeNode(String label) {
        MutableGraph newGraph = mutGraph().setDirected(true);
        //check if the node exist within the file

        //Refactor Method #1 Extract method change
        nodeExist = nodeSearch(label);

        //if it does delete the edges first with the node
        if(nodeExist) {
            for(MutableNode tempNode: graph.nodes()){
                int index = 0;
                ArrayList<Link> nodeEdges = new ArrayList<Link>(tempNode.links());
                for(Link link: nodeEdges){
                    if(link.from().name().toString().equals(label) || link.to().name().toString().equals(label)){
                        tempNode.links().remove(tempNode.links().get(index));
                    }
                    index++;
                }
                //then we loop through the graph and delete the node by creating a new graph
                if(!tempNode.name().toString().equals(label)){
                    newGraph.add(tempNode);
                }
            }
            //add the temp graph to the graph
          graph = newGraph;
        }else{
            //if it node doesnt exist print an message in terminal and return without doing the test
            System.out.println(label + " does not exist in graph");
            return;
        }

    }
    //removes nodes that are within the array and deletes any corresponding edges
    //same as removeNode method but we loop through the array doing every node
    //if one doesnt exist we return
    public void removeNodes(String[] labels) {
        MutableGraph newGraph = mutGraph().setDirected(true);
        for (String label : labels) {

            //Refactored Code #2
            nodeExist = nodeSearch(label);

            if (nodeExist) {
                removeNode(label);
            } else {
                System.out.println(label + " does not exist in graph");
                return;
            }
        }
    }


    //remove edges takes in a src and dst node and only deletes that edge we are looking for
    public void removeEdges(String srcLabel, String dstLabel){
        //sourceAndTarget.add(dstLabel + srcLabel);
        //check if the src and dst label exist and if the edge exist

        //Refactor #3 Extract Method to search if an edge exist
        nodeExist = edgeSearch(srcLabel, dstLabel);

        //if it does we add the edges of every node into an array
        //then iterate through each node till we find the link then delete it
        MutableGraph newGraph = mutGraph().setDirected(true);
        if(nodeExist) {
            for(MutableNode tempNode: graph.nodes()) {
                int index = 0;
                ArrayList<Link> nodeEdges = new ArrayList<Link>(tempNode.links());
                for (Link link : nodeEdges) {
                    if (link.from().name().toString().equals(srcLabel) && link.to().name().toString().equals(dstLabel)) {
                        tempNode.links().remove(tempNode.links().get(index));
                    }
                    index++;
                }
            }
        }else{
            //if neither the edge nor node exist print out in terminal and stop deleting
            System.out.println("Edge from " + srcLabel + " to " + dstLabel + " does not exist");
            return;
        }
    }


    //-------------------------------------------------------------------
    //Refactor Methods
    //Refactor #1 & 2 Extract Method to search for a node within the graph if it exists then return true
    public boolean nodeSearch(String label) {
        for (MutableNode node : graph.nodes()) {
            if (node.name().toString().equals(label)) {
                return true;
            }
        }
        return false;
    }

    //Refactor #3 Extract Method to search if an edge exist
    public boolean edgeSearch(String srcLabel, String dstLabel){

        for(Link link: graph.edges()){
            if(link.from().name().toString().equals(srcLabel) && link.to().name().toString().equals(dstLabel)){
                return true;
            }
        }
        return false;
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
