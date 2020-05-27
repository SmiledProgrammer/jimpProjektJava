import java.awt.*;

public class GateButton extends Button {

    public final static int width = 280;
    public final static int height = 100;

    GateButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        super.defaultColor = new Color(0, 76, 153);
        super.hoverColor = new Color(0, 128, 255);
        setText("Logic Gates", 36, 11, 64);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(textColor);
        int[] xPoints = { x + 245, x + 230, x + 260 };
        int[] yPoints = { y + 40, y + 60, y + 60 };
        g.fillPolygon(xPoints, yPoints, 3);
    }

    private ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            System.out.println("=)))");
        }
    };

}