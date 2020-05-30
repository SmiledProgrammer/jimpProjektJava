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
    }

    private void initializeXOR() {
        xorGate = new WireComponent();
    }

    public void placeComponent(Generation gen, int x, int y, Type type, WireComponent.Orientation orientation, boolean flipped) {
        switch (type) {
            case OR_GATE: orGate.place(gen, x, y, orientation, flipped);
            case AND_GATE: andGate.place(gen, x, y, orientation, flipped);
            case XOR_GATE: xorGate.place(gen, x, y, orientation, flipped);
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