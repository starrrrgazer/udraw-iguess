package DrawAndGuess;


import ui.mergeFace.MainFrame;

import javax.swing.*;

public class launch {

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
