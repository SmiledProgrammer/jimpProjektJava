package wireworld.gui;

import java.awt.*;

public class GateOption implements WindowComponent {

    private GenerationWindow window;
    private GateMenu menu;
    private int x, y;
    private int width, height;

    private Color defaultColor = new Color(0, 50, 153);
    private Color hoverColor = new Color(0, 90, 255);
    private Color textColor = Color.WHITE;

    private String gateName;
    private static final int fontSize = 32;
    private static final int textOffsetX = 20;
    private static final int textOffsetY = 48;

    public GateOption(GenerationWindow window, GateMenu menu, int x, int y, int width, int height, String gateName) {
        this.window = window;
        this.menu = menu;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gateName = gateName;
    }

    private boolean mouseOnOption() {
        Point p = window.getContentPane().getMousePosition();
        if (p != null)
            return (p.x >= x && p.x <= x + width && p.y >= y && p.y < y + height);
        return false;
    }

    @Override
    public void paint(Graphics g) {
        Color c = mouseOnOption() ? hoverColor : defaultColor;
        g.setColor(c);
        g.fillRect(x, y, width, height);
        g.setColor(textColor);
        g.setFont(new Font("czy to co tu napiszę naprawdę ma znaczenie?", Font.PLAIN, fontSize));
        g.drawString(gateName, x + textOffsetX, y + textOffsetY);
    }

    @Override
    public void clickAction() {
        if (mouseOnOption()) {
            //komunikacja z okienkiem w jakiś sposób, żeby zaczęło stawiać
            menu.setVisible(false);
        }
    }

}