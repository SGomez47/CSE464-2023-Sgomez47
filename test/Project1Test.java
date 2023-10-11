import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Project1Test {
    private Project1 test;
    @Before
    public void setUp(){
        test = new Project1();
    }

    @Test
    public void testParseGraph() throws IOException {
        test.parseGraph("src/main/resources/input.dot");
        System.out.println(test.toString());
    }

    @Test
    public void testOutputGraph() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.outputGraph("src/main/resources/output.dot");

        File outputFile = new File("src/main/resources/output.dot");
    }

    @Test
    public void testAddNode() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.addNode("Node1");
        test.addNode("Node1");      //Doesn't add duplicate
        System.out.println(test.toString());
    }

    @Test
    public void testAddNodes() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        String[] label = {"Node1", "Node2", "Node3"};
        test.addNodes(label);
        System.out.println(test.toString());
    }

    @Test
    public void testAddEdge() throws IOException{
        test.parseGraph("src/main/resources/input.dot");
        test.addNode("Node1");
        test.addNode("Node2");
        test.addEdge("Node1", "Node2");
        System.out.println(test.toString());
    }
}
