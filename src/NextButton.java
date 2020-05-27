import java.awt.*;

public class NextButton extends Button {

    public final static int width = 100;
    public final static int height = 100;

    NextButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        setText("Next", 36, 11, 64);
        defaultColor = new Color(0, 120, 0);
        hoverColor = new Color(0, 170, 0);
    }

    private ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            //tworzenie następnej generacji
        }
    };
}