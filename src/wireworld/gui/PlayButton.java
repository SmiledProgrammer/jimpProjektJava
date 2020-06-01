package wireworld.gui;

import wireworld.system.WireWorld;

import java.awt.*;

public class PlayButton extends Button {

    public final static int width = 120;
    public final static int height = 100;

    private boolean playing;

    public PlayButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        pause();
    }

    public void pause() {
        playing = false;
        setText("Play", 48, 12, 68);
        defaultColor = new Color(0, 80, 0);
        hoverColor = new Color(0, 130, 0);

        WireWorld.playing = false;
    }

    public void play() {
        playing = true;
        setText("Pause", 36, 8, 64);
        defaultColor = new Color(0, 204, 0);
        hoverColor = new Color(0, 255, 0);

        WireWorld.playing = true;
    }

    private ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            if (mouseOnButton()) {
                if (playing)
                    pause();
                else
                    play();
            }
        }
    };

}