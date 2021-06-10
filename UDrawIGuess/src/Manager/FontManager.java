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
        return new Font("����", Font.PLAIN, 16);
    }

    public Font getNameConfigLabelFont() {
        return new Font("����", Font.BOLD, 30);
    }

    public Font getChooseHPFont() {
        return new Font("����", Font.BOLD, 18);
    }

    public Font getIpConnectLabelFont() {
        return new Font("����", Font.BOLD, 16);
    }

    public Font getButtonFont() {
        return new Font("����", Font.BOLD, 18);
    }

    public Font getChatTitleFont() {
        return new Font("����", Font.BOLD, 14);
    }

    public Font getRoomInfoFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    public Font getScorePanelFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    public Font getHeaderNoticeFont() {
        return new Font("����", Font.PLAIN, 8);
    }

    public Font getThreeCountdownFont() {
        return new Font("����", Font.BOLD, 60);
    }

    public Font getGameTimeFont() {
        return new Font("����", Font.BOLD, 25);
    }

    public Font getAddScoreFont() {
        return new Font("����", Font.BOLD, 20);
    }

    public Font getToastNoticeFont() {
        return new Font("����", Font.PLAIN, 24);
    }

    public Font getToastTopicPanelFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    public Font getToastShowTopicFont() {
        return new Font("����", Font.BOLD, 16);
    }

    public Font getToastResultPanelFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    public Font getToastResultScoreFont() {
        return new Font("����", Font.BOLD, 20);
    }

}
