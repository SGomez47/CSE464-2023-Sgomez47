import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.List;
import java.util.Random;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class RandomWalkAlgorithm implements GraphStrategy {

    @Override
    public Path search(String src, String dst, MutableGraph graph) {
        Path path = new Path();
        MutableNode locateNode = path.locateNode(graph, src);
        path.addNode(src);

        System.out.println(src);
        return randomWalk(path, graph, dst, locateNode);
    }

    //RandomWalk method goes through the graph and outputs random paths to get from
    //source to destination
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
