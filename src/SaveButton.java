import java.awt.*;

public class SaveButton extends Button {

    public final static int width = 100;
    public final static int height = 100;

    SaveButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        setText("Save", 36, 11, 64);
        defaultColor = new Color(255, 40, 40);
        hoverColor = new Color(255, 90, 90);
    }

    private ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            //zapisywanie pliku
        }
    };

}