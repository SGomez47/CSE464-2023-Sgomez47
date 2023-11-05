import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Project1Test {
    private Project1 test;
    @Before
    public void setUp(){
        test = new Project1();
    }

    //Test to parse the graph
    @Test
    public void testParseGraph() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
    }

    //Test to print out the graph into the terminal and outputs the graph into a txt file located in resource folder
    @Test
    public void testOutputGraph() throws IOException{
        test.parseGraph("src/main/resources/input.dot");        //Feature 1 parseGraph
        System.out.println(test.toString());
        test.outputGraph("src/main/resources/output.txt");
        File outputFile = new File("src/main/resources/output.txt");
    }

    //addNode is called and outputs the file in a txt file in resource folder
    //compares the expected and output
    @Test
    public void testAddNode() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.addNode("Node1");
        test.addNode("Node1"); //Doesn't add duplicate node
        test.addNode("Node2");
        test.outputGraph("src/main/resources/addNode.txt");
        String output = Files.readString(Paths.get("src/main/resources/addNode.txt"));
        String expected = Files.readString(Paths.get("src/main/resources/expectedNode.txt"));
        Assert.assertEquals(expected.trim(), output.trim());
    }

    //addNodes is called and outputs the file in a txt file in resource folder
    //compares the expected and output
    @Test
    public void testAddNodes() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        String[] label = {"Node1", "Node2", "Node3"};
        test.addNodes(label);
        test.outputGraph("src/main/resources/addNodes.txt");
        String output = Files.readString(Paths.get("src/main/resources/addNodes.txt"));
        String expected = Files.readString(Paths.get("src/main/resources/expectedNodes.txt"));
        Assert.assertEquals(expected.trim(), output.trim());
    }

    //addEdge is called and outputs the file in a txt file in resource folder
    //compares the expected and output
    @Test
    public void testAddEdge() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.addEdge("Node1", "Node2");
        test.addEdge("Node1", "Node2"); //Doesn't add duplicate edge
        test.outputGraph("src/main/resources/addEdge.txt");
        String output = Files.readString(Paths.get("src/main/resources/addEdge.txt"));
        String expected = Files.readString(Paths.get("src/main/resources/expectedEdges.txt"));
        Assert.assertEquals(expected.trim(), output.trim());
    }

    @Test
    public void testRemoveNode() throws IOException {
        test.parseGraph("src/main/resources/input.dot");
        test.addNode("Node1");
        //test.addNode("Node2");
        test.addEdge("Node1", "Node2");
        MutableGraph newGraph = test.removeNode("Node1");
        test.outputNewGraph("src/main/resources/removeNode.txt", newGraph);
    }

    @Test
    public void testRemoveNodes() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        String[] label = {"Node1", "Node2", "Node3"};
        test.addNodes(label);
        test.addEdge("Node1", "Node2");
        String[] removeLabels = {"Node1", "Node2"};
        MutableGraph newGraph = test.removeNodes(removeLabels);
        test.outputNewGraph("src/main/resources/removeNodes.txt", newGraph);
    }

    @Test
    public void testRemoveEdge() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.addEdge("Node1", "Node2");
        test.addEdge("a", "Node3");
        MutableGraph newGraph = test.removeEdges("Node1", "Node2");
        test.outputNewGraph("src/main/resources/removeEdge.txt", newGraph);
    }

    //outputDotGraph is called and outputs the file in a dot file in resource folder
    //compares the expected and output
    @Test
    public void testOutputDotGraph() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.outputDotGraph("src/main/resources/outputDotGraph.dot");
        String output = Files.readString(Paths.get("src/main/resources/input.dot"));
        String expected = Files.readString(Paths.get("src/main/resources/outputDotGraph.dot"));
        Assert.assertEquals(expected.trim(), output.trim());
    }

    //outputGraphics is called and outputs the file in a png file in resource folder
    @Test
    public void testOutputGraphics() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.outputGraphics("src/main/resources/pngGraph.png", "PNG");
    }
}
