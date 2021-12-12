package api.tests;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_Graph_AlgorithmsTest {

    DirectedWeightedGraph g10000 = new Directed_Weighted_Graph("data/10000Nodes.json");
    DirectedWeightedGraph g1000 = new Directed_Weighted_Graph("data/1000Nodes.json");
    DirectedWeightedGraph g3 = new Directed_Weighted_Graph("data/G3.json");
    DirectedWeightedGraph g1 = new Directed_Weighted_Graph("data/G1.json");
    DirectedWeightedGraphAlgorithms alg = new Directed_Weighted_Graph_Algorithms();

    @Test
    void init() {
        alg.init(g1);
        assertNotNull(alg.getGraph());
        assertEquals(g1.getMC(),alg.getGraph().getMC());
        assertEquals(g1.nodeSize(),alg.getGraph().nodeSize());
        assertEquals(g1.edgeSize(),alg.getGraph().edgeSize());
        DirectedWeightedGraph g10000 = new Directed_Weighted_Graph("data/10000Nodes.json");
        assertNotNull(alg.getGraph());

    }

    @Test
    void getGraph() {
        alg.init(g1);
        assertNotNull(alg.getGraph());
        assertEquals(g1.getMC(),alg.getGraph().getMC());
        assertEquals(g1.nodeSize(),alg.getGraph().nodeSize());
        assertEquals(g1.edgeSize(),alg.getGraph().edgeSize());
    }

    @Test
    void copy() {
        alg.init(g1);
        DirectedWeightedGraph copy = alg.copy();
        assertNotNull(copy);
        assertEquals(g1.nodeSize(),copy.nodeSize());
        assertEquals(g1.edgeSize(),copy.edgeSize());
    }

    @Test
    void isConnected() {
        alg.init(g1);
        long start = System.currentTimeMillis();
        assertTrue(alg.isConnected());
        long end = System.currentTimeMillis();
        long delta = end - start;
        assert(delta < 30);
        DirectedWeightedGraph g1 = new Directed_Weighted_Graph("data/1000Nodes.json");
        alg.init(g1);
        long start1000 = System.currentTimeMillis();
        assertTrue(alg.isConnected());
        long end1000 = System.currentTimeMillis();
        long delta2 = end1000 - start1000;
        System.out.println(delta2);
        assert(delta2 < 300);

        DirectedWeightedGraph g2 = new Directed_Weighted_Graph("data/tsp2.json");
        alg.init(g2);
        assertTrue(alg.isConnected());

        alg.init(g10000);
        assertTrue(alg.isConnected());


    }

    @Test
    void shortestPathDist() {

        alg.init(g1);
        double dist0 = 1.3118716362419698;
        double dist1 = alg.shortestPathDist(0,16);
        assertEquals(dist0,dist1);


        DirectedWeightedGraph g100 = new Directed_Weighted_Graph("data/10000Nodes.json");
        alg.init(g100);
        double dist3 = 1165.776078062164;
        double dist2 = alg.shortestPathDist(0,9999);
        assertEquals(dist3,dist2);
        System.out.println(dist2);
    }

    @Test
    void shortestPath() {
        alg.init(g1);
        List<NodeData> sp = new LinkedList<>();
        sp.add(alg.getGraph().getNode(0));
        sp.add(alg.getGraph().getNode(1));
        sp.add(alg.getGraph().getNode(2));
        sp.add(alg.getGraph().getNode(6));
        sp.add(alg.getGraph().getNode(7));
        sp.add(alg.getGraph().getNode(8));
        long start1 = System.currentTimeMillis();
        List<NodeData> sp1 = alg.shortestPath(0,8);
        long delta = System.currentTimeMillis() - start1;
        assert (delta < 30);
        assertEquals(sp.size(),sp1.size());
        for (int i = 0; i < sp1.size();i++){
            if (sp.get(i).getKey() != sp1.get(i).getKey())
                fail("different nodes!");
        }

        DirectedWeightedGraph g2 = new Directed_Weighted_Graph("data/G2.json");
        alg.init(g2);
        List<NodeData> sp3 = new LinkedList<>();
        sp3.add(alg.getGraph().getNode(0));
        sp3.add(alg.getGraph().getNode(16));
        sp3.add(alg.getGraph().getNode(15));
        sp3.add(alg.getGraph().getNode(14));
        sp3.add(alg.getGraph().getNode(13));
        sp3.add(alg.getGraph().getNode(30));
        long start2 = System.currentTimeMillis();
        List<NodeData> sp2 = alg.shortestPath(0,30);
        long delta2 = System.currentTimeMillis() - start2;
        assert (delta2<30);
        assertEquals(sp3.size(),sp2.size());
        for (int i = 0; i < sp3.size();i++){
            if (sp2.get(i).getKey() != sp3.get(i).getKey())
                fail("different nodes!");
        }


        DirectedWeightedGraph g10 = new Directed_Weighted_Graph("data/1000Nodes.json");
        alg.init(g10);
        List<NodeData> sp4 = alg.shortestPath(0,1000);
        //System.out.println(sp4);
        assertNull(sp4);        // node not exits
        List<NodeData> sp6 = new LinkedList<>();
        sp6.add(g10.getNode(0));
        sp6.add(g10.getNode(769));
        sp6.add(g10.getNode(631));
        sp6.add(g10.getNode(195));
        sp6.add(g10.getNode(765));
        sp6.add(g10.getNode(661));
        sp6.add(g10.getNode(999));
        List<NodeData> sp5 = alg.shortestPath(0,999);
        assertEquals(sp6.size(),sp5.size());
        for (int i = 0; i < sp5.size();i++){
            if (sp5.get(i).getKey() != sp6.get(i).getKey())
                fail("different nodes!");
        }

        DirectedWeightedGraph g100 = new Directed_Weighted_Graph("data/10000Nodes.json");
        alg.init(g100);
        long start = System.currentTimeMillis();
        List<NodeData> list = alg.shortestPath(0,9999);
        long delta10000 = System.currentTimeMillis() - start;
        //System.out.println(delta10000);
        List<NodeData> list2 = alg.shortestPath(50,7959);

    }

    @Test
    void center() {
        alg.init(g1);
         NodeData center = alg.center();
         assertEquals(8,center.getKey());
         alg.init(g3);
         NodeData center2 = alg.center();
        assertEquals(40,center2.getKey());
        //alg.init(g10000);
        //NodeData center3 = alg.center();
        //assertEquals(3846,center3.getKey());

    }

    @Test
    void tsp() {
        alg.init(g1);
        List<NodeData> cities = new LinkedList<>();
        Iterator<NodeData> itr = alg.getGraph().nodeIter();
        List<NodeData> resCmp = new LinkedList<>();
        while(itr.hasNext()){
            NodeData n = itr.next();
            cities.add(n);
            resCmp.add(n);
        }
        resCmp.add(cities.get(0));
        List<NodeData> res = alg.tsp(cities);
        assertEquals(18,res.size());
        for (int i = 0; i < res.size();i++){
            if (resCmp.get(i).getKey() != res.get(i).getKey()){
                fail("wrong path");
            }
        }

        alg.init(g1000);
        List<NodeData> cities2 = new LinkedList<>();
        Iterator<NodeData> itr2 = alg.getGraph().nodeIter();
        while(itr2.hasNext()){
            cities2.add(itr2.next());
        }
        long start = System.currentTimeMillis();
        List<NodeData> res2 = alg.tsp(cities2);
        long delta = System.currentTimeMillis() - start;
        System.out.println(res2 + "\n" + res2.size() + "\n" + delta);
        //long time = 600000;
        assert (delta < 700000);

    }

    @Test
    void save_load() {
        alg.init(g1);
        int mc = alg.getGraph().getMC();
        alg.save("data/save.json");
        alg.load("data/save.json");
        int mc2 = alg.getGraph().getMC();
        assertEquals(mc,mc2);

        alg.load("data/10000Nodes.json");
        assertEquals(10000,alg.getGraph().nodeSize());
    }

}