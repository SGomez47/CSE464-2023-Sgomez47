import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.*;
import guru.nidi.graphviz.engine.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Project1 {
    private Graph graph;

    public void parseGraph(String filepath) throws IOException {
       FileInputStream input = new FileInputStream(filepath);
       graph = (Graph) new Parser().read(input);
    }

    public void outputGraph(String filepath) throws IOException{
        File output = new File(filepath);
        Graphviz.fromGraph(graph).render(Format.DOT).toFile(output);
    }

    @Override
    public String toString(){
        return graph.toString();
    }

}
