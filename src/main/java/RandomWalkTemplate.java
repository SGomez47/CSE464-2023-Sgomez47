import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.LinkedList;
import java.util.Random;

public class RandomWalkTemplate extends SearchTemplate{
    public RandomWalkTemplate(MutableGraph graph) {
        super(graph);
        this.path = graph;
    }
    @Override
    public Path treeSearch(String src, String dst) {
        Path tempPath = new Path();
        MutableNode locateNode = tempPath.locateNode(path, src);
        tempPath.addNode(src);

        System.out.println(src);
        return randomWalk(tempPath, path, dst, locateNode);
    }

    public Path randomWalk(Path path, MutableGraph graph, String dst, MutableNode tempNode) {
        Random randNode = new Random();
        int linkSize = tempNode.links().size();
        MutableNode nextNode = path.locateNode(graph, tempNode.links().get(randNode.nextInt(linkSize)).to().name().toString());

        //if dst is found from randomizing then add to path and return
        if(nextNode.name().toString().equals(dst) || nextNode.links().isEmpty()){
            path.addNode(dst);
            return path;
        }

        path.addNode(nextNode.name().toString());
        System.out.println(path.toString());
        //Recursive call to randomize the next neighbor node
        return randomWalk(path, graph, dst, nextNode);
    }
}
