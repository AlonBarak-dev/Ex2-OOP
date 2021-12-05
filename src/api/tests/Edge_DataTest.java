package api.tests;

import api.EdgeData;
import api.Edge_Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Edge_DataTest {


    EdgeData e1 = new Edge_Data(1,6,10.02);
    EdgeData e2 = new Edge_Data(0,5,15.14);
    EdgeData e3 = new Edge_Data(5,0,15.13);

    @Test
    void getSrc() {
        assertEquals(1,e1.getSrc());
        assertEquals(0,e2.getSrc());
        assertEquals(5,e3.getSrc());
    }

    @Test
    void getDest() {
        assertEquals(6,e1.getDest());
        assertEquals(5,e2.getDest());
        assertEquals(0,e3.getDest());
    }

    @Test
    void getWeight() {
        assertEquals(10.02,e1.getWeight());
        assertEquals(15.14,e2.getWeight());
        assertEquals(15.13,e3.getWeight());
    }

    @Test
    void getInfo() {
        assertEquals("SRC: 1 DEST: 6 WEIGHT: 10.02",e1.getInfo());
        assertEquals("SRC: 0 DEST: 5 WEIGHT: 15.14",e2.getInfo());
        assertEquals("SRC: 5 DEST: 0 WEIGHT: 15.13",e3.getInfo());
    }
}