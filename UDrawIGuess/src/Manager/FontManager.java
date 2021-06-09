package Manager;

import java.awt.*;

public class FontManager {
    private static FontManager fontManager = new FontManager();

    private FontManager() {}

    public static FontManager getDefaultFontManager() {
        return fontManager;
    }

    public Font getTitleFont() {
        return new Font("����", Font.BOLD, 12);
    }

    public Font getPaintLabelFont(){
        return new Font("����", Font.BOLD, 12);
    }

    public Font getJumpButtonFont() {
        return new Font("����", Font.BOLD, 16);
    }

    public Font getInputFieldFont() {
        return new Font("����", Font.BOLD, 16);
    }

    public Font getButtonFont() {
        return new Font("����", Font.BOLD, 18);
    }

    public Font getNameConfigLabelFont() {
        return new Font("����", Font.BOLD, 30);
    }

}
