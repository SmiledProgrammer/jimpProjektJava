package wireworld.gui;

import java.awt.*;

public class GateButton extends Button {

    public final static int width = 170;
    public final static int height = 100;

    private GateMenu menu;

    GateButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        super.defaultColor = new Color(0, 76, 153);
        super.hoverColor = new Color(0, 128, 255);
        setText("Gates", 36, 11, 64);

        menu = new GateMenu(window, x, y - GateMenu.height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(textColor); //rysowanie trójkącika
        int[] xPoints = { x + 140, x + 125, x + 155 };
        int[] yPoints = { y + 40, y + 60, y + 60 };
        g.fillPolygon(xPoints, yPoints, 3);

        menu.paint(g);
    }

    private ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            if (mouseOnButton()) {
                if (menu.isVisible())
                    menu.setVisible(false);
                else
                    menu.setVisible(true);
            }
            window.resetChosenGate();
            menu.clickAction();
        }
    };

}