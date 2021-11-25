package api;
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

public class mainTest {

    public static void main(String[] args) {
        Directed_Weighted_Graph grp = new Directed_Weighted_Graph("data/G1.json");

        //System.out.println("Nodes : " + grp.nodeSize() + " , Edges : " + grp.edgeSize());
        Iterator<NodeData> itr = grp.nodeIter();
        System.out.println(grp.getMC());
        while(itr.hasNext()){
          System.out.println(itr.next());
        }
        GeoLocation g = new Geo_Location(20,32,0);
        NodeData n = new Node_Data(g,18);
        grp.addNode(n);
        System.out.println(grp.getMC());
        while(itr.hasNext()){
            System.out.println(itr.next());
        }



    }
}
