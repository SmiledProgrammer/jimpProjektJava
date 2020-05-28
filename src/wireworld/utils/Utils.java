package wireworld.utils;

import java.awt.*;

public class Utils {

    public static Color inverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }

}