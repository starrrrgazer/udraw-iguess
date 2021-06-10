package Manager;

import java.awt.*;

/**
 * 字体控制类，方便管理用到的所有字体
 */
public class FontManager {
    private static FontManager fontManager = new FontManager();

    private FontManager() {}

    /**
     * 单例模式的引用
     * @return FontManager，单例模式
     */
    public static FontManager getDefaultFontManager() {
        return fontManager;
    }

    /**
     * 获取标题字体
     * @return Font
     */
    public Font getTitleFont() {
        return new Font("楷体", Font.BOLD, 12);
    }

    /**
     * 获取跳转按钮字体
     * @return Font
     */
    public Font getJumpButtonFont() {
        return new Font("楷体", Font.BOLD, 16);
    }

    /**
     * 获取输入文本框字体
     * @return Font
     */
    public Font getInputFieldFont() {
        return new Font("楷体", Font.PLAIN, 16);
    }

    /**
     * 获取昵称标签字体
     * @return Font
     */
    public Font getNameConfigLabelFont() {
        return new Font("楷体", Font.BOLD, 30);
    }

    /**
     * 获取选择头像字体
     * @return Font
     */
    public Font getChooseHPFont() {
        return new Font("楷体", Font.BOLD, 18);
    }

    /**
     * 获取ip连接字体
     * @return Font
     */
    public Font getIpConnectLabelFont() {
        return new Font("楷体", Font.BOLD, 16);
    }

    /**
     * 获取按钮字体
     * @return Font
     */
    public Font getButtonFont() {
        return new Font("楷体", Font.BOLD, 18);
    }

    /**
     * 获取聊天标题字体
     * @return Font
     */
    public Font getChatTitleFont() {
        return new Font("楷体", Font.BOLD, 14);
    }

    /**
     * 获取房间信息字体
     * @return Font
     */
    public Font getRoomInfoFont() {
        return new Font("楷体", Font.PLAIN, 12);
    }

    /**
     * 获取得分字体
     * @return Font
     */
    public Font getScorePanelFont() {
        return new Font("楷体", Font.PLAIN, 12);
    }

    /**
     * 获取头部提示字体
     * @return Font
     */
    public Font getHeaderNoticeFont() {
        return new Font("楷体", Font.PLAIN, 8);
    }

    /**
     * 获取倒计时字体
     * @return Font
     */
    public Font getThreeCountdownFont() {
        return new Font("楷体", Font.BOLD, 60);
    }

    /**
     * 获取游戏时间字体
     * @return Font
     */
    public Font getGameTimeFont() {
        return new Font("楷体", Font.BOLD, 25);
    }

    /**
     * 获取加分字体
     * @return Font
     */
    public Font getAddScoreFont() {
        return new Font("楷体", Font.BOLD, 20);
    }

    /**
     * 获取提示板字体
     * @return Font
     */
    public Font getToastNoticeFont() {
        return new Font("楷体", Font.PLAIN, 24);
    }

    /**
     * 获取问题字体
     * @return Font
     */
    public Font getToastTopicPanelFont() {
        return new Font("楷体", Font.PLAIN, 12);
    }

    /**
     * 获取问题展示字体
     * @return Font
     */
    public Font getToastShowTopicFont() {
        return new Font("楷体", Font.BOLD, 16);
    }

    /**
     * 获取答案结果字体
     * @return Font
     */
    public Font getToastResultPanelFont() {
        return new Font("楷体", Font.PLAIN, 12);
    }

    /**
     * 获取最终得分字体
     * @return Font
     */
    public Font getToastResultScoreFont() {
        return new Font("楷体", Font.BOLD, 20);
    }

}
