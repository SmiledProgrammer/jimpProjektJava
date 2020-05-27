import java.awt.*;

public class PlayButton extends Button {

    public final static int width = 160;
    public final static int height = 100;

    private boolean playing;

    public PlayButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        pause();
    }

    public void pause() {
        playing = false;
        setText("Play", 56, 23, 70);
        defaultColor = new Color(0, 80, 0);
        hoverColor = new Color(0, 130, 0);

        WireWorld.playing = false;
    }

    public void play() {
        playing = true;
        setText("Pause", 48, 10, 67);
        defaultColor = new Color(0, 204, 0);
        hoverColor = new Color(0, 255, 0);

        WireWorld.playing = true;
    }

    private ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            if (playing)
                pause();
            else
                play();
        }
    };

}