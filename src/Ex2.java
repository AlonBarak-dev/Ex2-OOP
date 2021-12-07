import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.Directed_Weighted_Graph;
import api.Directed_Weighted_Graph_Algorithms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2{

    public static void main(String[] args){
        String filename = args[0];
        Path path = Paths.get(filename);
        runGUI(path.toString());
    }

    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******
        ans = new Directed_Weighted_Graph(json_file);
        // ********************************
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        Directed_Weighted_Graph graph = new Directed_Weighted_Graph(json_file);
        ans = new Directed_Weighted_Graph_Algorithms();
        ans.init(graph);
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******

        Graph graph = new Graph(alg);


        // ********************************
    }
}