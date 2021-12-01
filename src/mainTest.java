import api.Directed_Weighted_Graph;
import api.Directed_Weighted_Graph_Algorithms;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class mainTest {

    public static void main(String[] args) {
        Directed_Weighted_Graph grp = new Directed_Weighted_Graph("data/tsp.json");

        Directed_Weighted_Graph_Algorithms g = new Directed_Weighted_Graph_Algorithms();
        g.init(grp);
        List<NodeData> list = new LinkedList<>();
        Iterator<NodeData> itr = grp.nodeIter();
        while(itr.hasNext()){
            list.add(itr.next());
        }
        g.save("data/test.json");
        g.load("data/G1.json");
        g.load("data/test.json");

        Ex2.runGUI("data/test.json");

        System.out.println("ALL GOOD!");

    }
}
