package DrawAndGuess;


import ui.mergePanel.MainFrame;

import javax.swing.*;

public class launch {
    /**
     * ���������������ڣ�����MainFrame
     * @see MainFrame
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
