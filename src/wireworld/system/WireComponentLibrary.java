package wireworld.system;

import wireworld.utils.Vector2D;

import java.util.ArrayList;

public class WireComponentLibrary {

    public enum Type { OR_GATE, AND_GATE, XOR_GATE };

    private WireComponent orGate;
    private WireComponent andGate;
    private WireComponent xorGate;

    public WireComponentLibrary() {
        initializeOR();
        initializeAND();
        initializeXOR();
    }

    private void initializeOR() {
        orGate = new WireComponent();
        orGate.addPointToStructure(1, 0);
        orGate.addPointToStructure(2, 0);
        orGate.addPointToStructure(0, 1);
        orGate.addPointToStructure(3, 1);
        orGate.addPointToStructure(2, 2);
        orGate.addPointToStructure(3, 2);
        orGate.addPointToStructure(4, 2);
        orGate.addPointToStructure(0, 3);
        orGate.addPointToStructure(3, 3);
        orGate.addPointToStructure(1, 4);
        orGate.addPointToStructure(2, 4);
    }

    private void initializeAND() {
        andGate = new WireComponent();
        andGate.addPointToStructure(1, 0);
        andGate.addPointToStructure(2, 0);
        andGate.addPointToStructure(0,1);
        andGate.addPointToStructure(3,1);
        andGate.addPointToStructure(2,2);
        andGate.addPointToStructure(3,2);
        andGate.addPointToStructure(4,2);
        andGate.addPointToStructure(5,2);
        andGate.addPointToStructure(2,3);
        andGate.addPointToStructure(4,3);
        andGate.addPointToStructure(5,3);
        andGate.addPointToStructure(6,3);
        andGate.addPointToStructure(2,4);
        andGate.addPointToStructure(3,4);
        andGate.addPointToStructure(4,4);
        andGate.addPointToStructure(5,4);
        andGate.addPointToStructure(0,5);
        andGate.addPointToStructure(3,5);
        andGate.addPointToStructure(1,6);
        andGate.addPointToStructure(2,6);


    }

    private void initializeXOR()
    {
        xorGate = new WireComponent();
        xorGate.addPointToStructure(0,0);
 xorGate.addPointToStructure(1,0);
 xorGate.addPointToStructure(2,0);
 xorGate.addPointToStructure(3,0);
 xorGate.addPointToStructure(4,0);
 xorGate.addPointToStructure(5,0);
 xorGate.addPointToStructure(6,0);
 xorGate.addPointToStructure(7,0);
        xorGate.addPointToStructure(8,1);
        xorGate.addPointToStructure(9,1);
        xorGate.addPointToStructure(10,1);
        xorGate.addPointToStructure(5,2);
        xorGate.addPointToStructure(7,2);
        xorGate.addPointToStructure(11,2);
        xorGate.addPointToStructure(0,3);
        xorGate.addPointToStructure(4,3);
        xorGate.addPointToStructure(5,3);
        xorGate.addPointToStructure(6,3);
        xorGate.addPointToStructure(11,3);
        xorGate.addPointToStructure(1,4);
        xorGate.addPointToStructure(3,4);
        xorGate.addPointToStructure(5,4);
        xorGate.addPointToStructure(7,4);
        xorGate.addPointToStructure(9,4);
        xorGate.addPointToStructure(11,4);
        xorGate.addPointToStructure(14,4);
        xorGate.addPointToStructure(15,4);
        xorGate.addPointToStructure(1,5);
        xorGate.addPointToStructure(3,5);
        xorGate.addPointToStructure(8,5);
        xorGate.addPointToStructure(9,5);
        xorGate.addPointToStructure(10,5);
        xorGate.addPointToStructure(13,5);
        xorGate.addPointToStructure(1,6);
        xorGate.addPointToStructure(3,6);
        xorGate.addPointToStructure(9,6);
        xorGate.addPointToStructure(11,6);
        xorGate.addPointToStructure(12,6);
        xorGate.addPointToStructure(2,7);




    }

    public void placeComponent(Generation gen, int x, int y, Type type, WireComponent.Orientation orientation, boolean flipped) {
        switch (type) {
            case OR_GATE: {
                orGate.place(gen, x, y, orientation, flipped);
                break;
            }
            case AND_GATE: {
                andGate.place(gen, x, y, orientation, flipped);
                break;
            }
            case XOR_GATE: {
                xorGate.place(gen, x, y, orientation, flipped);
                break;
            }
        }
    }

    public ArrayList<Vector2D> getComponentPoints(Type type, WireComponent.Orientation orientation, boolean flipped) {
        switch (type) {
            case OR_GATE: return orGate.getPoints(orientation, flipped);
            case AND_GATE: return andGate.getPoints(orientation, flipped);
            case XOR_GATE: return xorGate.getPoints(orientation, flipped);
        }
        return null;
    }

}