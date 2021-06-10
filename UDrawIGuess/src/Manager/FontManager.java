package Manager;

import java.awt.*;

/**
 * ��������࣬��������õ�����������
 */
public class FontManager {
    private static FontManager fontManager = new FontManager();

    private FontManager() {}

    /**
     * ����ģʽ������
     * @return FontManager������ģʽ
     */
    public static FontManager getDefaultFontManager() {
        return fontManager;
    }

    /**
     * ��ȡ��������
     * @return Font
     */
    public Font getTitleFont() {
        return new Font("����", Font.BOLD, 12);
    }

    /**
     * ��ȡ��ת��ť����
     * @return Font
     */
    public Font getJumpButtonFont() {
        return new Font("����", Font.BOLD, 16);
    }

    /**
     * ��ȡ�����ı�������
     * @return Font
     */
    public Font getInputFieldFont() {
        return new Font("����", Font.PLAIN, 16);
    }

    /**
     * ��ȡ�ǳƱ�ǩ����
     * @return Font
     */
    public Font getNameConfigLabelFont() {
        return new Font("����", Font.BOLD, 30);
    }

    /**
     * ��ȡѡ��ͷ������
     * @return Font
     */
    public Font getChooseHPFont() {
        return new Font("����", Font.BOLD, 18);
    }

    /**
     * ��ȡip��������
     * @return Font
     */
    public Font getIpConnectLabelFont() {
        return new Font("����", Font.BOLD, 16);
    }

    /**
     * ��ȡ��ť����
     * @return Font
     */
    public Font getButtonFont() {
        return new Font("����", Font.BOLD, 18);
    }

    /**
     * ��ȡ�����������
     * @return Font
     */
    public Font getChatTitleFont() {
        return new Font("����", Font.BOLD, 14);
    }

    /**
     * ��ȡ������Ϣ����
     * @return Font
     */
    public Font getRoomInfoFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    /**
     * ��ȡ�÷�����
     * @return Font
     */
    public Font getScorePanelFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    /**
     * ��ȡͷ����ʾ����
     * @return Font
     */
    public Font getHeaderNoticeFont() {
        return new Font("����", Font.PLAIN, 8);
    }

    /**
     * ��ȡ����ʱ����
     * @return Font
     */
    public Font getThreeCountdownFont() {
        return new Font("����", Font.BOLD, 60);
    }

    /**
     * ��ȡ��Ϸʱ������
     * @return Font
     */
    public Font getGameTimeFont() {
        return new Font("����", Font.BOLD, 25);
    }

    /**
     * ��ȡ�ӷ�����
     * @return Font
     */
    public Font getAddScoreFont() {
        return new Font("����", Font.BOLD, 20);
    }

    /**
     * ��ȡ��ʾ������
     * @return Font
     */
    public Font getToastNoticeFont() {
        return new Font("����", Font.PLAIN, 24);
    }

    /**
     * ��ȡ��������
     * @return Font
     */
    public Font getToastTopicPanelFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    /**
     * ��ȡ����չʾ����
     * @return Font
     */
    public Font getToastShowTopicFont() {
        return new Font("����", Font.BOLD, 16);
    }

    /**
     * ��ȡ�𰸽������
     * @return Font
     */
    public Font getToastResultPanelFont() {
        return new Font("����", Font.PLAIN, 12);
    }

    /**
     * ��ȡ���յ÷�����
     * @return Font
     */
    public Font getToastResultScoreFont() {
        return new Font("����", Font.BOLD, 20);
    }

}
