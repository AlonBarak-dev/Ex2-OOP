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

public class mainTest {

    public static void main(String[] args) {
        Directed_Weighted_Graph grp = new Directed_Weighted_Graph("data/G1.json");

        System.out.println("Nodes : " + grp.nodeSize() + " , Edges : " + grp.edgeSize());



    }
}
