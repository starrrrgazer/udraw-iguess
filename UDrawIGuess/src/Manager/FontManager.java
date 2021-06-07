package Manager;

import java.awt.*;

public class FontManager {
    private static FontManager fontManager = new FontManager();

    private FontManager() {}

    public static FontManager getDefaultFontManager() {
        return fontManager;
    }

    public Font getTitleFont() {
        return new Font("Ώ¬Με", Font.BOLD, 12);
    }

    public Font getPaintLabelFont(){
        return new Font("Ώ¬Με", Font.BOLD, 12);
    }

}
