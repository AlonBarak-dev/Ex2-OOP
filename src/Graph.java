//import api.DirectedWeightedGraphAlgorithms;
import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Graph extends Thread {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file_menu;
    JMenu edit_menu;
    JMenu algorithm_menu;
    JMenu test_menu;
    JMenuItem save, load, add_node, add_edge, is_connected, shortest_path, tsp,remove_edge,remove_node;
    Graphics page;
    String filename;

    public Graph(){
        //this.filename = filename;
        this.frame = new JFrame();
        frame.setLayout(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        file_menu = new JMenu("File");
        edit_menu = new JMenu("Edit");
        algorithm_menu = new JMenu("Algorithms");
        test_menu = new JMenu("Test");

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        file_menu.add(save); file_menu.add(load);

        add_node = new JMenuItem("Add Node");
        add_edge = new JMenuItem("Add Edge");
        remove_edge = new JMenuItem("Remove Edge");
        remove_node = new JMenuItem("Remove Node");
        edit_menu.add(add_node); edit_menu.add(add_edge); edit_menu.add(remove_edge); edit_menu.add(remove_node);

        is_connected = new JMenuItem("Is Connected");
        shortest_path = new JMenuItem("Shortest path");
        tsp = new JMenuItem("TSP");
        algorithm_menu.add(is_connected); algorithm_menu.add(shortest_path); algorithm_menu.add(tsp);

        menuBar.add(file_menu); menuBar.add(edit_menu); menuBar.add(algorithm_menu); menuBar.add(test_menu);

        this.frame.setJMenuBar(menuBar);
        frame.setSize(500,500);
        this.frame.add(new CustomPaintComponent());
        frame.setVisible(true);


    }

    static class CustomPaintComponent extends Component {

        private double min_x = Double.MAX_VALUE;
        private double min_y = Double.MAX_VALUE;
        private double max_x = 0;
        private double max_y = 0;

        public void paint(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 45, 255));
            g2d.setStroke(new BasicStroke(2));

            DirectedWeightedGraphAlgorithms DWG = new Directed_Weighted_Graph_Algorithms();
            DirectedWeightedGraph g1 = new Directed_Weighted_Graph("data/G1.json");
            DWG.init(g1);

            updatePrivateValues(DWG.getGraph());
            drawNodes(g2d, DWG.getGraph());
            drawEdges(g2d, DWG.getGraph());
            g2d.drawLine(0,487, 500, 487);
            g2d.drawLine(487,0, 487, 440);
        }
        public void drawNodes(Graphics2D g2d, DirectedWeightedGraph DG) {

            Iterator<NodeData> it = DG.nodeIter();
            while (it.hasNext()) {
                NodeData node = it.next();
                GeoLocation loc = node.getLocation();
                g2d.drawOval((int)algoX(loc.x()), (int)algoY(loc.y()), 5, 5);
            }
        }
        public void drawEdges(Graphics2D g2d, DirectedWeightedGraph DW) {

            Iterator<EdgeData> it = DW.edgeIter();
            while (it.hasNext()) {
                EdgeData edge = it.next();
                GeoLocation src = DW.getNode(edge.getSrc()).getLocation();
                GeoLocation dest = DW.getNode(edge.getDest()).getLocation();
                g2d.drawLine((int)algoX(src.x()), (int)algoY(src.y()), (int)algoX(dest.x()), (int)algoY(dest.y()));
            }
        }
        public double algoX(double x) {
            x = x - this.min_x;
            x = (500 * 0.934) * (x / (this.max_x - this.min_x));
            return x;
        }
        public double algoY(double y) {
            y = y - this.min_y;
            y = (500 * 0.934) * (y / (this.max_y - this.min_y));
            return y;
        }
        public void updatePrivateValues(DirectedWeightedGraph DWG) {
            Iterator<NodeData> it = DWG.nodeIter();
            while (it.hasNext()) {
                NodeData node = it.next();
                GeoLocation loc = node.getLocation();
                if (this.min_x > loc.x())
                    this.min_x = loc.x();
                if (this.min_y > loc.y())
                    this.min_y = loc.y();
                if (this.max_x < loc.x())
                    this.max_x = loc.x();
                if (this.max_y < loc.y())
                    this.max_y = loc.y();
            }
        }
    }






}
