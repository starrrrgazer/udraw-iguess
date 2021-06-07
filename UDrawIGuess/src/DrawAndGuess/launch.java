package DrawAndGuess;

import javax.swing.*;

public class launch {
    public static DrawAndGuess client;

    public static void main(String[] args)   //main·½·¨
    {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        client = new DrawAndGuess();
        client.launchFrame();
    }
}
