package api.tests;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_GraphTest {

    DirectedWeightedGraph g1 = new Directed_Weighted_Graph("data/tsp.json");

    @Test
    void getNode() {
        NodeData n1 = new Node_Data(new Geo_Location(35.19589389346247,32.10152879327731,0.0),0);
        NodeData n1Cmp = g1.getNode(0);
        assertEquals(n1.getKey(),n1Cmp.getKey());
        assertEquals(n1.getWeight(),n1Cmp.getWeight());
        assertEquals(n1.getTag(),n1Cmp.getTag());
    }

    @Test
    void getEdge() {
        EdgeData e = new Edge_Data(0,1,1.0);
        EdgeData eCmp = g1.getEdge(0,1);
        assertEquals(e.getSrc(),eCmp.getSrc());
        assertEquals(e.getDest(),eCmp.getDest());
        assertEquals(e.getWeight(),eCmp.getWeight());
        assertEquals(e.getInfo(),eCmp.getInfo());
        assertNotEquals(e.getSrc(),eCmp.getDest());
    }

    @Test
    void addNode() {
        NodeData n = new Node_Data(new Geo_Location(35.19589389346247,32.10152879327731,0.0),0);
        int mcOld = g1.getMC();
        g1.addNode(n);
        int mcNew = g1.getMC();
        assertEquals(mcOld,mcNew);      // node should not be added
        int size = g1.nodeSize();
        NodeData n1 = new Node_Data(new Geo_Location(35.19589389346247,32.10152879327731,0.0),size);
        g1.addNode(n1);
        int mcNew2 = g1.getMC();
        assertEquals(mcOld+1,mcNew2);
        NodeData n2 = g1.getNode(size);
        assertEquals(n1.getKey(),n2.getKey());
        assertEquals(n1.getWeight(),n2.getWeight());
        assertEquals(n1.getTag(),n2.getTag());
    }

    @Test
    void connect() {

        EdgeData e1 = g1.getEdge(0,1);
        //EdgeData e2 = new Edge_Data(0,1,50.0);
        int mcOld = g1.getMC();
        g1.connect(0,1,50);
        EdgeData e2 = g1.getEdge(0,1);
        assertNotEquals(mcOld,g1.getMC());
        assertNotEquals(e2.getWeight(),e1.getWeight());
        int mcNew = g1.getMC();
        g1.connect(0,1,50);
        assertEquals(mcNew,g1.getMC());

    }

    @Test
    void nodeIter() {
        Iterator<NodeData> itr = g1.nodeIter();
        while(itr.hasNext()){
            if (itr.next() == null)
                fail("received null");
        }
        int size = g1.nodeSize();
        g1.addNode(new Node_Data(new Geo_Location(35.19589389346247,32.10152879327731,0.0),size));
        try {
            NodeData n1 = itr.next();
            fail();
        } catch (NoSuchElementException e){
            System.out.println(e.toString());
        }

    }

    @Test
    void edgeIter() {

        g1.addNode(new Node_Data(new Geo_Location(1.1,2.2,0.0),4));
        Iterator<EdgeData> itr = g1.edgeIter();
        while(itr.hasNext()){
            if (itr.next() == null)
                fail("received null");
        }
        g1.connect(0,4,5);
        try {
            EdgeData edge = itr.next();
            fail();
        } catch (NoSuchElementException e){
            System.out.println(e.toString());
        }
    }

    @Test
    void testEdgeIter() {
        Iterator<EdgeData> itr = g1.edgeIter(0);
        g1.removeEdge(0,1);
        try {
            EdgeData edge = itr.next();
            fail();
        } catch (NoSuchElementException e){
            System.out.println(e.toString());
        }
    }

    @Test
    void removeNode() {
        assertEquals(4,g1.nodeSize());
        g1.removeNode(0);
        assertEquals(3,g1.nodeSize());
        g1.removeNode(0);
        assertEquals(3,g1.nodeSize());
        g1.removeNode(1);
        assertEquals(2,g1.nodeSize());
    }

    @Test
    void removeEdge() {
        assertEquals(12,g1.edgeSize());
        g1.removeEdge(0,1);
        assertEquals(11,g1.edgeSize());
        g1.removeEdge(0,1);
        assertEquals(11,g1.edgeSize());
        g1.removeEdge(0,2);
        assertEquals(10,g1.edgeSize());
    }

    @Test
    void nodeSize() {
        assertEquals(4,g1.nodeSize());
        g1.removeNode(0);
        assertEquals(3,g1.nodeSize());
        g1.addNode(new Node_Data(new Geo_Location(35,35,0.0),0));
        assertEquals(4,g1.nodeSize());

    }

    @Test
    void edgeSize() {
        assertEquals(12,g1.edgeSize());
        g1.removeEdge(0,1);
        assertEquals(11,g1.edgeSize());
        g1.connect(0,1,5);
        assertEquals(12,g1.edgeSize());
    }

    @Test
    void getMC() {
        int mode = g1.nodeSize() + g1.edgeSize();
        assertEquals(mode,g1.getMC());
    }
}