package DrawAndGuess;

import ui.mergeFace.DGMainFrame;

public class launch {
    public static DGMainFrame client;

    public static void main(String[] args)   //main·½·¨
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
