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
        Directed_Weighted_Graph grp = new Directed_Weighted_Graph("data/test.json");

        Directed_Weighted_Graph_Algorithms g = new Directed_Weighted_Graph_Algorithms();
        g.init(grp);
        System.out.println(g.isConnected());

        System.out.println(g.shortestPath(0,3) + " W: " + g.shortestPathDist(0,3));

        grp.removeEdge(3,0);
        Directed_Weighted_Graph_Algorithms g1 = new Directed_Weighted_Graph_Algorithms();
        g1.init(grp);
        System.out.println(g1.isConnected());

        grp.connect(3,0,1.0);
        grp.removeNode(0);

        Directed_Weighted_Graph_Algorithms g2 = new Directed_Weighted_Graph_Algorithms();
        g2.init(grp);

        System.out.println(g2.isConnected());


        Directed_Weighted_Graph grp_1 = new Directed_Weighted_Graph("data/G3.json");
        Directed_Weighted_Graph_Algorithms g_1 = new Directed_Weighted_Graph_Algorithms();
        g_1.init(grp_1);
        System.out.println(g_1.isConnected());

        System.out.println(g_1.shortestPath(0,10)+ " W: " + g_1.shortestPathDist(0,10));





    }
}
