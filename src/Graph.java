//import api.DirectedWeightedGraphAlgorithms;
import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    JMenu instructions;
    JMenuItem save, load, add_node, add_edge, is_connected, shortest_path, tsp,remove_edge,remove_node,center;
    JMenuItem saveIns, loadIns, add_nodeIns, add_edgeIns, is_connectedIns, shortest_pathIns, tspIns,remove_edgeIns,remove_nodeIns,centerIns;
    CustomPaintComponent cmp;

    Graph(DirectedWeightedGraphAlgorithms alg){
        graph = alg;

        frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2,(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.80));

        menuBar = new JMenuBar();
        file_menu = new JMenu("File");
        edit_menu = new JMenu("Edit");
        algorithm_menu = new JMenu("Algorithms");
        instructions = new JMenu("Instructions");

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
        center = new JMenuItem("Center");
        algorithm_menu.add(is_connected); algorithm_menu.add(shortest_path); algorithm_menu.add(tsp); algorithm_menu.add(center);

        saveIns = new JMenuItem("SaveIns");
        loadIns = new JMenuItem("LoadIns");
        add_nodeIns = new JMenuItem("Add NodeIns");
        add_edgeIns = new JMenuItem("Add EdgeIns");
        remove_nodeIns = new JMenuItem("Remove NodeIns");
        remove_edgeIns = new JMenuItem("Remove EdgeIns");
        is_connectedIns = new JMenuItem("Is connectedIns");
        shortest_pathIns = new JMenuItem("ShortestPathIns");
        tspIns = new JMenuItem("TspIns");
        centerIns = new JMenuItem("CenterIns");
        instructions.add(saveIns); instructions.add(loadIns); instructions.add(add_nodeIns); instructions.add(add_edgeIns);
        instructions.add(remove_nodeIns); instructions.add(remove_edgeIns); instructions.add(is_connectedIns); instructions.add(shortest_pathIns);
        instructions.add(tspIns); instructions.add(centerIns);

        menuBar.add(file_menu); menuBar.add(edit_menu); menuBar.add(algorithm_menu); menuBar.add(instructions);



        frame.setJMenuBar(menuBar);
        // actions
        load.addActionListener(this);
        save.addActionListener(this);
        add_node.addActionListener(this);
        add_edge.addActionListener(this);
        remove_edge.addActionListener(this);
        remove_node.addActionListener(this);
        is_connected.addActionListener(this);
        shortest_path.addActionListener(this);
        tsp.addActionListener(this);
        center.addActionListener(this);
        // instructions
        loadIns.addActionListener(this);
        saveIns.addActionListener(this);
        add_nodeIns.addActionListener(this);
        add_edgeIns.addActionListener(this);
        remove_edgeIns.addActionListener(this);
        remove_nodeIns.addActionListener(this);
        is_connectedIns.addActionListener(this);
        shortest_pathIns.addActionListener(this);
        tspIns.addActionListener(this);
        centerIns.addActionListener(this);

        cmp = new CustomPaintComponent(graph);
        frame.add(cmp);


        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();
        if(com.equals("Save")){
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.showSaveDialog(null);
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            graph.save(path);
            System.out.println("work");
        }
        if (com.equals("Load")){
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.showSaveDialog(null);
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            this.graph.load(path);
            frame.remove(cmp);
            CustomPaintComponent newCmp = new CustomPaintComponent(graph);
            frame.add(newCmp);
            frame.setVisible(true);
            cmp = newCmp;
            System.out.println("work");
        }
        if (com.equals("Add Node")){
            // input should look like this: 10,35.1215,53.1288,0.0
            String input = JOptionPane.showInputDialog(frame,"Please enter the Node values like this : \n" +
                    "Key,X,Y,Z");
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
            String input = JOptionPane.showInputDialog(frame,"Please enter the Node key like this : \n" +
                    "Key");
            int key = Integer.parseInt(input);
            DirectedWeightedGraph newGraph = new Directed_Weighted_Graph(graph.getGraph());
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
            String input = JOptionPane.showInputDialog(frame,"Please enter the Edge values like this : \n" +
                    "SRC,DEST,WEIGHT");
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
            String input = JOptionPane.showInputDialog(frame,"Please enter the Edge values like this : \n" +
                    "SRC,DEST");
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
            String input = JOptionPane.showInputDialog(frame,"Please enter the Nodes keys like this : \n" +
                    "SRC key,DEST key");
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
                        if (i % 15 == 0 && i != 0){
                            pathStr += "\n";
                        }
                        pathStr += path.get(i).getKey() + "->";
                    }
                    JOptionPane.showMessageDialog(frame, pathStr, "PATH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (com.equals("TSP")) {
            // input should be like: 0,2,5,4,8,9...
            String input = JOptionPane.showInputDialog(frame,"Please enter the Nodes keys like this : \n" +
                    "n1,n2,n3,n4,...");
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
                }
                else {
                    String pathStr = "";
                    for (int i = 0; i < path.size() - 1; i++) {
                        if (i % 15 == 0 && i != 0){
                            pathStr += "\n";
                        }
                        pathStr += path.get(i).getKey() + "->";
                    }
                    pathStr += path.get(path.size()-1).getKey();
                    JOptionPane.showMessageDialog(frame, pathStr, "PATH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (com.equals("Center")){
            NodeData n = this.graph.center();
            int key = n.getKey();
            JOptionPane.showMessageDialog(frame, "THE CENTER NODE IS: " + key, "CENTER", JOptionPane.INFORMATION_MESSAGE);
        }

        // instructions start here:

        if (com.equals("SaveIns")){
            JOptionPane.showMessageDialog(frame,"In order to save a graph do the following: \n" +
                    "Go to FILE \n" +
                    "Click on SAVE \n" +
                    "Go to the wanted path in the file browser\n" +
                    "Click on save" , "HOW TO SAVE", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("LoadIns")){
            JOptionPane.showMessageDialog(frame,"In order to load a graph do the following: \n" +
                    "Go to FILE \n" +
                    "Click on LOAD\n" +
                    "Go to the wanted path in the file browser\n" +
                    "Click on Load" , "HOW TO LOAD", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("Add NodeIns")){
            JOptionPane.showMessageDialog(frame,"In order to add a node to the graph do the following: \n" +
                    "Go to EDIT \n" +
                    "Click on ADD NODE \n" +
                    "Write the values of the node like this:\n" +
                    "4,35.12,32.587,0.0 \n" +
                    "Which will do:" +
                    "KEY : 4, X : 35.12, Y : 32.587, Z : 0.0 \n"  , "HOW TO ADD A NODE", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("Add EdgeIns")){
            JOptionPane.showMessageDialog(frame,"In order to add an edge to the graph do the following: \n" +
                    "Go to EDIT \n" +
                    "Click on ADD EDGE\n" +
                    "Write the values of the edge like this:\n" +
                    "2,10,5.2 \n" +
                    "Which will do:" +
                    "SRC : 2, DEST : 10, WEIGHT : 5.2 \n"  , "HOW TO ADD AN EDGE", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("Remove NodeIns")){
            JOptionPane.showMessageDialog(frame,"In order to remove a node from the graph do the following: \n" +
                    "Go to EDIT \n" +
                    "Click on REMOVE NODE\n" +
                    "Write the key of the node like this:\n" +
                    "17 \n" +
                    "Which will do:" +
                    "KEY : 17 \n"  , "HOW TO REMOVE A NODE", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("Remove EdgeIns")){
            JOptionPane.showMessageDialog(frame,"In order to remove an edge from the graph do the following: \n" +
                    "Go to EDIT \n" +
                    "Click on REMOVE EDGE\n" +
                    "Write the key of the node like this:\n" +
                    "17 \n" +
                    "Which will do:" +
                    "KEY : 17 \n"  , "HOW TO REMOVE AN EDGE", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("Is connectedIns")){
            JOptionPane.showMessageDialog(frame,"In order to check if the graph is connected do the following: \n" +
                    "Go to ALGORITHMS \n" +
                    "Click on IS CONNECTED" , "IS CONNECTED:", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("ShortestPathIns")){
            JOptionPane.showMessageDialog(frame,"In order to calculate the shortest path from src to dest do the following: \n" +
                    "Go to ALGORITHMS \n" +
                    "Click on SHORTEST PATH\n" +
                    "Write the values of the nodes like this:\n" +
                    "2,10 \n" +
                    "Which will do:" +
                    "SRC : 2, DEST : 10 \n"  , "FIND SHORTEST PATH", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("TspIns")){
            JOptionPane.showMessageDialog(frame,"In order to calculate the a TSP of nodes do the following: \n" +
                    "Go to ALGORITHMS \n" +
                    "Click on TSP\n" +
                    "Write the values of the nodes like this:\n" +
                    "2,10,4,17,8,9,5 \n" +
                    "Which will do:" +
                    "CITIES: 2 , 10 , 4 , 17 , 8 , 9 , 5 \n"  , "FIND TSP", JOptionPane.INFORMATION_MESSAGE);
        }
        if (com.equals("CenterIns")){
            JOptionPane.showMessageDialog(frame,"In order to find the center node do the following: \n" +
                    "Go to ALGORITHMS \n" +
                    "Click on CENTER" , "FIND THE CENTER NODE", JOptionPane.INFORMATION_MESSAGE);
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

            Directed_Weighted_Graph DW2 = (Directed_Weighted_Graph) DG;
            Iterator<NodeData> it = DW2.nodeIterPrivate();
            while (it.hasNext()) {
                NodeData node = it.next();
                GeoLocation loc = node.getLocation();
                g2d.setColor(Color.RED);
                int nodesize = 12;
                g2d.fillOval((int)algoX(loc.x()) - (nodesize /2) , (int)algoY(loc.y()) - (nodesize /2), nodesize, nodesize);
                g2d.setColor(Color.blue);
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
                int x1 = (int)algoX(src.x());
                int y1 = (int)algoY(src.y());
                int x2 = (int)algoX(dest.x());
                int y2 = (int)algoY(dest.y());
                g2d.drawLine(x1, y1, x2, y2);
                int dx = x2 - x1, dy = y2 - y1;
                double D = Math.sqrt(dx*dx + dy*dy);
                double xm = D - 10, xn = xm, ym = 7, yn = - 7, x;
                double sin = dy / D, cos = dx / D;

                x = xm*cos - ym*sin + x1;
                ym = xm*sin + ym*cos + y1;
                xm = x;

                x = xn*cos - yn*sin + x1;
                yn = xn*sin + yn*cos + y1;
                xn = x;

                int[] xpoints = {x2, (int) xm, (int) xn};
                int[] ypoints = {y2, (int) ym, (int) yn};

                g2d.fillPolygon(xpoints,ypoints,3);
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
