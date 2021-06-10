package DrawAndGuess;


import ui.mergePanel.MainFrame;

import javax.swing.*;

public class launch {
    /**
     * 这是整个程序的入口，调用MainFrame
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
