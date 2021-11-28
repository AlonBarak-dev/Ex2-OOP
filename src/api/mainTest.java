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

        Directed_Weighted_Graph_Algorithms g = new Directed_Weighted_Graph_Algorithms();
        g.init(grp);

        System.out.println(g.isConnected());





    }
}
