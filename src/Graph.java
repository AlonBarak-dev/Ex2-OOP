import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Graph extends JFrame {

    JMenuBar menuBar;
    JMenu file_menu;
    JMenu edit_menu;
    JMenu algorithm_menu;
    JMenu test_menu;
    JMenuItem save, load, add_node, add_edge, is_connected, shortest_path, tsp;
    Graphics page;

    public Graph(){

        this.setLayout(null);

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
        edit_menu.add(add_node); edit_menu.add(add_edge);

        is_connected = new JMenuItem("Is Connected");
        shortest_path = new JMenuItem("Shortest path");
        tsp = new JMenuItem("TSP");
        algorithm_menu.add(is_connected); algorithm_menu.add(shortest_path); algorithm_menu.add(tsp);

        menuBar.add(file_menu); menuBar.add(edit_menu); menuBar.add(algorithm_menu); menuBar.add(test_menu);

        this.setJMenuBar(menuBar);


    }







}
