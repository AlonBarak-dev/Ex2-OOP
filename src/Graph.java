//import api.DirectedWeightedGraphAlgorithms;
import api.*;
import org.junit.jupiter.api.condition.OS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graph implements ActionListener {

    private DirectedWeightedGraphAlgorithms graph;
    JFrame frame;
    JMenuBar menuBar;
    JMenu file_menu;
    JMenu edit_menu;
    JMenu algorithm_menu;
    JMenu test_menu;
    JMenuItem save, load, add_node, add_edge, is_connected, shortest_path, tsp,remove_edge,remove_node;
    JTextField textField;
    CustomPaintComponent cmp;
    JOptionPane pop;

    Graph(DirectedWeightedGraphAlgorithms alg){
        graph = alg;

//        JFrame frame;
//        JMenuBar menuBar;
//        JMenu file_menu;
//        JMenu edit_menu;
//        JMenu algorithm_menu;
//        JMenu test_menu;
//        JMenuItem save, load, add_node, add_edge, is_connected, shortest_path, tsp,remove_edge,remove_node;

        textField = new JTextField("data/G3.json");
        textField.setSize(160,40);
        textField.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3,(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.60));

        frame = new JFrame("My GUI");
        //frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2,(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.80));

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

//        pop = new JOptionPane("ANSWER:");
//        pop.

        frame.setJMenuBar(menuBar);
        frame.add(textField);

        load.addActionListener(this);
        save.addActionListener(this);
        add_node.addActionListener(this);
        add_edge.addActionListener(this);
        remove_edge.addActionListener(this);
        remove_node.addActionListener(this);
        is_connected.addActionListener(this);
        shortest_path.addActionListener(this);
        tsp.addActionListener(this);

        cmp = new CustomPaintComponent(graph);
        frame.add(cmp);


        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();
        if(com.equals("Save")){
            //frame.add(textField);
            String file = textField.getText();
            graph.save(file);
            System.out.println("work");
        }
        if (com.equals("Load")){
            String file = textField.getText();
            graph.load(file);
            frame.remove(cmp);
            CustomPaintComponent newCmp = new CustomPaintComponent(graph);
            frame.add(newCmp);
            frame.setVisible(true);
            cmp = newCmp;
            System.out.println("work");
        }
        if (com.equals("Add Node")){
            // input should look like this: 10,35.1215,53.1288,0.0
            String input = textField.getText();
            String[] values = input.split(",");
            int newKey = Integer.parseInt(values[0]);
            double x = Double.parseDouble(values[1]);
            double y = Double.parseDouble(values[2]);
            double z = Double.parseDouble(values[3]);
            GeoLocation g = new Geo_Location(x,y,z);
            NodeData n = new Node_Data(g,newKey);
            DirectedWeightedGraph newGraph = graph.getGraph();
            newGraph.addNode(n);
            graph.init(newGraph);
            frame.remove(cmp);
            CustomPaintComponent newCmp = new CustomPaintComponent(graph);
            frame.add(newCmp);
            frame.setVisible(true);
            cmp = newCmp;
            System.out.println("work");
        }
        if (com.equals("Remove Node")){
            // input should look like this: 5
            String input = textField.getText();
            int key = Integer.parseInt(input);
            DirectedWeightedGraph newGraph = graph.getGraph();
            newGraph.removeNode(key);
            graph.init(newGraph);
            frame.remove(cmp);
            CustomPaintComponent newCmp = new CustomPaintComponent(graph);
            frame.add(newCmp);
            frame.setVisible(true);
            cmp = newCmp;
            System.out.println("work");
        }
        if(com.equals("Add Edge")){
            // input should look like this: 0,5,2.5
            String input = textField.getText();
            String[] values = input.split(",");
            int src = Integer.parseInt(values[0]);
            int dest = Integer.parseInt(values[1]);
            double w = Double.parseDouble(values[2]);
            DirectedWeightedGraph newGraph = graph.getGraph();
            newGraph.connect(src,dest,w);
            graph.init(newGraph);
            frame.remove(cmp);
            CustomPaintComponent newCmp = new CustomPaintComponent(graph);
            frame.add(newCmp);
            frame.setVisible(true);
            cmp = newCmp;
            System.out.println("work");
        }
        if (com.equals("Remove Edge")){
            // input should look like this: 0,5
            String input = textField.getText();
            String[] values = input.split(",");
            int src = Integer.parseInt(values[0]);
            int dest = Integer.parseInt(values[1]);
            DirectedWeightedGraph newGraph = graph.getGraph();
            newGraph.removeEdge(src,dest);
            graph.init(newGraph);
            frame.remove(cmp);
            CustomPaintComponent newCmp = new CustomPaintComponent(graph);
            frame.add(newCmp);
            frame.setVisible(true);
            cmp = newCmp;
            System.out.println("work");
        }
        if(com.equals("Is Connected")){
            if(this.graph.isConnected()){
                JOptionPane.showMessageDialog(frame,"TRUE!", "ANSWER",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(frame,"FALSE", "ANSWER",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (com.equals("Shortest path")){
            // input should be like: 0,2
            String input = textField.getText();
            String[] values = input.split(",");
            if (values.length == 0) {
                JOptionPane.showMessageDialog(frame, "PLEASE CHOOSE NODES", "ERROR", JOptionPane.INFORMATION_MESSAGE);

            } else {
                int src = Integer.parseInt(values[0]);
                int dest = Integer.parseInt(values[1]);
                List<NodeData> path = this.graph.shortestPath(src, dest);
                if (path == null) {
                    JOptionPane.showMessageDialog(frame, "NO PATH BETWEEN THE CHOSEN NODES!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String pathStr = "";
                    for (int i = 0; i < path.size(); i++) {
                        pathStr += path.get(i).getKey() + "->";
                    }
                    JOptionPane.showMessageDialog(frame, pathStr, "PATH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (com.equals("TSP")) {
            // input should be like: 0,2,5,4,8,9...
            String input = textField.getText();
            String[] values = input.split(",");
            if (values.length == 0) {
                JOptionPane.showMessageDialog(frame, "PLEASE CHOOSE NODES", "ERROR", JOptionPane.INFORMATION_MESSAGE);

            } else {
                List<NodeData> cities = new LinkedList<>();
                for (int i = 0; i < values.length; i++) {
                    int key = Integer.parseInt(values[i]);
                    NodeData n = this.graph.getGraph().getNode(key);
                    cities.add(n);
                }
                List<NodeData> path = this.graph.tsp(cities);
                if (path == null) {
                    JOptionPane.showMessageDialog(frame, "NO PATH BETWEEN THE CHOSEN NODES!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String pathStr = "";
                    for (int i = 0; i < path.size(); i++) {
                        pathStr += path.get(i).getKey() + "->";
                    }
                    JOptionPane.showMessageDialog(frame, pathStr, "PATH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }

    static class CustomPaintComponent extends Component {


        private double min_x = Double.MAX_VALUE;
        private double min_y = Double.MAX_VALUE;
        private double max_x = 0;
        private double max_y = 0;

        private final int margin = 50;

        private final DirectedWeightedGraphAlgorithms DWG = new Directed_Weighted_Graph_Algorithms();

        public CustomPaintComponent(DirectedWeightedGraphAlgorithms graph){
            this.DWG.init(graph.getGraph());
        }


        public void paint(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(28, 17, 17));
            g2d.setStroke(new BasicStroke(2));

            updatePrivateValues(DWG.getGraph());
            drawEdges(g2d, DWG.getGraph());
            drawNodes(g2d, DWG.getGraph());

        }
        public void drawNodes(Graphics2D g2d, DirectedWeightedGraph DG) {


            Iterator<NodeData> it = DG.nodeIter();
            while (it.hasNext()) {
                NodeData node = it.next();
                GeoLocation loc = node.getLocation();
                g2d.setColor(Color.RED);
                int nodesize = 12;
                g2d.fillOval((int)algoX(loc.x()) - (nodesize /2) , (int)algoY(loc.y()) - (nodesize /2), nodesize, nodesize);
                g2d.setColor(Color.black);
                int offset = 15;
                g2d.drawString(""+node.getKey(),(int)algoX(loc.x()) - (nodesize /2) + offset,(int)algoY(loc.y()) - (nodesize /2) + offset + 8);
            }
        }
        public void drawEdges(Graphics2D g2d, DirectedWeightedGraph DW) {

            Directed_Weighted_Graph DW2 = (Directed_Weighted_Graph) DW;
            Iterator<EdgeData> it = DW2.edgeIterPrivate();
            g2d.setColor(new Color(28, 17, 17));
            while (it.hasNext()) {
                EdgeData edge = it.next();
                GeoLocation src = DW.getNode(edge.getSrc()).getLocation();
                GeoLocation dest = DW.getNode(edge.getDest()).getLocation();
                g2d.drawLine((int)algoX(src.x()), (int)algoY(src.y()), (int)algoX(dest.x()), (int)algoY(dest.y()));
            }
        }
        public double algoX(double x) {
            x = x - this.min_x;
            x = (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 * 0.80) * (x / (this.max_x - this.min_x));
            return x + margin;
        }
        public double algoY(double y) {
            y = y - this.min_y;
            y = (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 * 0.80) * (y / (this.max_y - this.min_y));
            return y + margin;
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
