package wireworld.gui;

import java.awt.*;

public class Button implements WindowComponent {

    private GenerationWindow window;
    protected int x, y;
    protected int width, height;

    protected Color defaultColor = new Color(51, 51, 255);
    protected Color hoverColor = new Color(153, 153, 255);
    protected Color textColor = Color.WHITE;

    private String text = "";
    private int fontSize = 0;
    private int textOffsetX = 0;
    private int textOffsetY = 0;

    ButtonAction action = null;

    Button(GenerationWindow window, int x, int y, int width, int height) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private boolean mouseOnButton() {
        Point p = window.getContentPane().getMousePosition();
        if (p != null)
            return (p.x >= x && p.x <= x + width && p.y >= y && p.y < y + height);
        return false;
    }

    @Override
    public void paint(Graphics g) {
        Color c = mouseOnButton() ? hoverColor : defaultColor;
        g.setColor(c);
        g.fillRect(x, y, width, height);
        g.setColor(textColor);
        g.setFont(new Font("czy to co tu napiszę naprawdę ma znaczenie?", Font.BOLD, fontSize));
        g.drawString(text, x + textOffsetX, y + textOffsetY);
    }

    @Override
    public void clickAction() {
        if (action != null && mouseOnButton()) {
            action.clickAction();
        }
    }

    public void setText(String text, int fontSize, int offsetX, int offsetY) {
        this.text = text;
        this.fontSize = fontSize;
        this.textOffsetX = offsetX;
        this.textOffsetY = offsetY;
    }

    public void addAction(ButtonAction action) {
        this.action = action;
    }

}