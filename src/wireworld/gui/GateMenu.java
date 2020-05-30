package wireworld.gui;

import wireworld.system.Generation;
import wireworld.system.WireComponentLibrary;

import java.awt.*;

public class GateMenu implements WindowComponent {

    private GenerationWindow window;
    private int x, y;
    public static final int width = GateButton.width;
    public static final int height = 210;

    private boolean visible = false;
    private GateOption orGate;
    private GateOption andGate;
    private GateOption xorGate;

    public GateMenu(GenerationWindow window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
        setupOptions();
    }

    private boolean mouseOnMenu() {
        Point p = window.getContentPane().getMousePosition();
        if (p != null)
            return (p.x >= x && p.x <= x + width && p.y >= y && p.y < y + height + GateButton.height);
        return false;
    }

    private void setupOptions() {
        int optionHeight = height / 3;
        orGate = new GateOption(window, this, x, y, width, optionHeight, "OR Gate", WireComponentLibrary.Type.OR_GATE);
        andGate = new GateOption(window, this, x, y + optionHeight, width, optionHeight, "AND Gate", WireComponentLibrary.Type.AND_GATE);
        xorGate = new GateOption(window, this, x, y + optionHeight * 2, width, optionHeight, "XOR Gate", WireComponentLibrary.Type.XOR_GATE);
    }

    @Override
    public void paint(Graphics g) {
        if (!mouseOnMenu()) {
            visible = false;
            window.setBlockingEditing(false);
        }
        if (visible) {
            orGate.paint(g);
            andGate.paint(g);
            xorGate.paint(g);
        }
    }

    @Override
    public void clickAction() {
        if (visible) {
            orGate.clickAction();
            andGate.clickAction();
            xorGate.clickAction();
        }
    }

    public void setVisible(boolean value) {
        visible = value;
        window.setBlockingEditing(value);
    }

    public boolean isVisible() {
        return visible;
    }

}