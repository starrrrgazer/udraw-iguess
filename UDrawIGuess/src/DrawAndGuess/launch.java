package DrawAndGuess;

import ui.mergeFace.DGMainFrame;

public class launch {
    public static DGMainFrame client;

    public static void main(String[] args)   //main����
    {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        client = new DGMainFrame();
        client.launchFrame();
    }
}
