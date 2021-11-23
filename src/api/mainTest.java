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


//        JSONParser jsonParser = new JSONParser();
//        File f = new File("data/G1.json");
//        try(FileReader reader = new FileReader(f)){
//
//            Object obj =  jsonParser.parse(reader);
//            JSONObject NodeObject = (JSONObject) obj;
//            JSONArray NodeList = (JSONArray) NodeObject.get("Nodes");
//            JSONArray EdgeList = (JSONArray) NodeObject.get("Edges");
//            //System.out.println(NodeList);
//            NodeList.forEach(node -> pNode((JSONObject) node));
//            EdgeList.forEach(node -> pEdge((JSONObject) node));
//
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void pEdge(JSONObject node) {
//        int src = ((Long) node.get("src")).intValue();
//        int dest = ((Long) node.get("dest")).intValue();
//        double w = (double)node.get("w");
//        Edge_Data e = new Edge_Data(src,dest,w);
//        System.out.println(e);
//
//    }
//
//    private static void pNode(JSONObject node) {
//        int id = ((Long) node.get("id")).intValue();
//        String pos = (String) node.get("pos");
//        Geo_Location g = new Geo_Location(pos);
//        Node_Data n = new Node_Data(g,id);
//        System.out.println(n);
//    }


    }
}
