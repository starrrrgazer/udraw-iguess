package ui.mergeFace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Manager.FontManager;
import Manager.ImageManager;
import ui.part.PaintPanel;
import ui.part.PaintToolPanel;
import ui.part.component.FrameButton;


//@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {

    public static MainFrame mainFrame;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Point location;
    private Point frameLocation;

    private JPanel toastPanel = new JPanel();
    private JPanel showPanel = new JPanel();
    private JPanel framePanel = new JPanel() {
        private Image img = ImageManager.getDefaultImageManager().getFrameBg();
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JPanel titlePanel = new JPanel() {
        private Image img = ImageManager.getDefaultImageManager().getTitleBg();
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JPanel viewPanel = new JPanel() {
        private Image img = ImageManager.getDefaultImageManager().getViewBg();
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };

    private JLabel title = new JLabel("你 画 我 猜");
    private FrameButton minimize = new FrameButton(FrameButton.Type.MINIMIZE);
    private FrameButton maximize = new FrameButton(FrameButton.Type.MAXIMIZE);
    private FrameButton close = new FrameButton(FrameButton.Type.CLOSE);

    private JPanel currentPanel = null;
/* 这里需要定义一些panel*/


    private Font titleFont = FontManager.getDefaultFontManager().getTitleFont();

    public MainFrame() {
        mainFrame = this;


        initFrameDecorated();
        initFace();

        framePanel.setLayout(null);
        framePanel.add(titlePanel);
        framePanel.add(viewPanel);

        this.setContentPane(framePanel);
        this.setTitle("你画我猜");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });

        setColor(new Color(211, 211, 211));

    }

    private void initFace() {

        viewPanel.setBounds(0, 30, WIDTH, HEIGHT - 30);
        viewPanel.setBackground(Color.WHITE);
        viewPanel.setLayout(null);
        viewPanel.add(toastPanel);
        viewPanel.add(showPanel);
        viewPanel.add(PaintPanel.getPaintPanel());
        viewPanel.add(PaintToolPanel.getPaintToolPanel());

        toastPanel.setBounds(0, 0, WIDTH, HEIGHT - 30);
        toastPanel.setOpaque(false);
        toastPanel.setLayout(null);
        showPanel.setBounds(0, 0, WIDTH, HEIGHT - 30);
        showPanel.setOpaque(false);
        showPanel.setLayout(null);

    }

    private void initFrameDecorated() {

        titlePanel.setBounds(0, 0, WIDTH, 30);
        titlePanel.setLayout(null);
        titlePanel.add(title);
        titlePanel.add(minimize);
        titlePanel.add(maximize);
        titlePanel.add(close);

        title.setBounds(20, 0, 200, 30);
        title.setFont(titleFont);

        minimize.setBounds(WIDTH - 99, 0, 29, 15);
        minimize.setToolTipText("最小化");
        minimize.addMouseListener(this);


        maximize.setBounds(WIDTH - 70, 0, 29, 15);
        maximize.setToolTipText(null);
        maximize.setEnabled(false);

        close.setBounds(WIDTH - 41, 0, 35, 15);
        close.setToolTipText("关闭");
        close.addMouseListener(this);

    }

    private void setColor(Color color) {
        titlePanel.setBackground(color);
        viewPanel.setBackground(color);
    }

    public void quit() {
        System.exit(0);
    }


    public JPanel getToastPanel() {
        return toastPanel;
    }


    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == this) {
            location = e.getLocationOnScreen();
            frameLocation = this.getLocation();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == minimize) {
            this.setExtendedState(ICONIFIED);
        } else if (e.getSource() == close) {
            quit();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == this) {
            Point moved = new Point(e.getLocationOnScreen().x - location.x, e.getLocationOnScreen().y - location.y);
            this.setLocation(frameLocation.x + moved.x, frameLocation.y + moved.y);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {}



}
