package DrawAndGuess;


import ui.mergeFace.MainFrame;

import javax.swing.*;

public class launch {

    public static void main(String[] args)
    {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        client = new DGMainFrame();
//        client.launchFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
