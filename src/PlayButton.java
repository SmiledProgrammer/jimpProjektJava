import java.awt.*;

public class PlayButton implements WindowComponent {

    private final static int width = 200;
    private final static int height = 100;
    private GenerationWindow window;
    private int x, y;

    private boolean playing;

    public PlayButton(GenerationWindow window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
        playing = false;
    }

    private boolean mouseOnButton() {
        Point p = window.getContentPane().getMousePosition();
        if (p != null)
            return (p.x >= x && p.x <= x + width && p.y >= y && p.y < y + height);
        return false;
    }

    @Override
    public void paint(Graphics g) {
        Color c = mouseOnButton() ? new Color(153, 153, 255) : new Color(51, 51, 255);
        g.setColor(c);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("czy to co tu napisze naprawde ma znaczenie?", Font.BOLD, 56));
        if (!playing)
            g.drawString("Play", x + 43, y + 70);
        else
            g.drawString("Pause", x + 18, y + 70);
    }

    @Override
    public void clickAction() {
        if (mouseOnButton()) {
            if (playing)
                playing = false;
            else
                playing = true;
        }
    }
}