package wireworld.system;

public class WireComponentLibrary {

    public enum Type { OR_GATE, AND_GATE, XOR_GATE };

    private WireComponent orGate;

    public WireComponentLibrary() {
        initializeOR();
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

    public void placeComponent(Generation gen, int x, int y, Type type, WireComponent.Orientation orientation, boolean flipped) {
        switch (type) {
            case OR_GATE: orGate.place(gen, x, y, orientation, flipped);
            case AND_GATE:
            case XOR_GATE:
        }
    }

}