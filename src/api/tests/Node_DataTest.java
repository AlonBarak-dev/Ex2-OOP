package api.tests;

import api.Geo_Location;
import api.Node_Data;

import static org.junit.jupiter.api.Assertions.*;

class Node_DataTest {

    Node_Data n1 = new Node_Data(new Geo_Location(2.0,3.0,0.0),0);
    Node_Data n2 = new Node_Data(new Geo_Location(4.0,6.0,0.0),1);

    @org.junit.jupiter.api.Test
    void getKey() {
        int key1 = 0;
        int key2 = 1;
        assertEquals(key1,n1.getKey());
        assertEquals(key2,n2.getKey());
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        double x1 = 2.0;
        double x2 = 4.0;
        double y1 = 3.0;
        double y2 = 6.0;

        assertEquals(x1,n1.getLocation().x());
        assertEquals(y1,n1.getLocation().y());
        assertEquals(x2,n2.getLocation().x());
        assertEquals(y2,n2.getLocation().y());

    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        Node_Data n3 = new Node_Data(new Geo_Location(1.0,2.0,0.0),2);
        n3.setLocation(new Geo_Location(2.0,1.0,0.0));
        assertEquals(2.0,n3.getLocation().x());
        assertEquals(1.0,n3.getLocation().y());
        assertEquals(0.0,n3.getLocation().z());
    }

    @org.junit.jupiter.api.Test
    void getWeight_setWeight() {
        double w1 = 0.0;
        assertEquals(w1,n1.getWeight());
        n1.setWeight(5);
        w1 = 5;
        assertEquals(w1,n1.getWeight());
        w1 = 8;
        assertNotEquals(w1, n1.getWeight(), 0.0);

    }


}