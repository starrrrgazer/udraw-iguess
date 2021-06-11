package ui.mergePanel;

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
import ui.part.ComponentDropper;
import ui.part.FrameButton;

/**
 * 这是程序的主要的Frame，其他界面都是作为Panel显示在里面
 * 下面的各个Panel是总的Panel，可以互相跳转
 * @see OnLinePanel
 * @see GamePanel
 * @see IpConnectPanel
 */

public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {
    private static MainFrame mainFrame;

    /**
     * mainFrame的宽度
     */
    public static final int WIDTH = 800;
    /**
     * mainFrame的高度
     */
    public static final int HEIGHT = 600;

    private Point location;
    private Point frameLocation;

    private JPanel toastPanel = new JPanel();
    private JPanel framePanel = new JPanel();
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
    private OnLinePanel onLinePanel = new OnLinePanel(this);
    private IpConnectPanel ipConnectPanel = new IpConnectPanel(this);
    private GamePanel gamePanel = new GamePanel(this);

    private Font titleFont = FontManager.getDefaultFontManager().getTitleFont();


    /**
     * MainFrame的构造函数
     * framePanel是覆盖整个MainFrame的Panel，里面有titlePanel：标题栏，和viewPanel：标题下面的界面
     * 通过ComponentDropper的切换界面的方法，将当前界面切换到进入的第一个界面onlinePanel
     * @see ComponentDropper
     * @see OnLinePanel
     */
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

        ComponentDropper.getDefaultDropper().switchPanel(getCurrentPanel(), getOnLinePanel());

    }

    /**
     * 初始化各个界面，并加入各个界面
     * @see ui.part.gamepart.ToastPanel
     * @see OnLinePanel
     * @see GamePanel
     * @see IpConnectPanel
     */

    private void initFace() {

        viewPanel.setBounds(0, 30, WIDTH, HEIGHT - 30);
        viewPanel.setBackground(Color.WHITE);
        viewPanel.setLayout(null);
        viewPanel.add(toastPanel);
        viewPanel.add(onLinePanel);
        viewPanel.add(gamePanel);
        viewPanel.add(ipConnectPanel);

        toastPanel.setBounds(0, 0, WIDTH, HEIGHT - 30);
        toastPanel.setOpaque(false);
        toastPanel.setLayout(null);

        onLinePanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
        gamePanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
        ipConnectPanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
    }

    /**
     * 初始化标题栏：titlePanel的各个属性
     */

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

    /**
     * 设置退出方法
     */
    public void quit() {
        gamePanel.quit();
        System.exit(0);
    }

    /**
     * 获取ToastPanel
     * @return toastpanel
     */
    public JPanel getToastPanel() {
        return toastPanel;
    }

    /**
     * 获取OnlinePanel，并把当前的Panel设置为onlinePanel
     * @return onlinepanel
     */
    public OnLinePanel getOnLinePanel() {
        currentPanel = onLinePanel;
        return onLinePanel;
    }

    /**
     * 获取IpConnectPanel，并把当前的Panel设置为IpConnectPanel
     * @return ipconnectpanel
     */
    public IpConnectPanel getIpConnectPanel() {
        currentPanel = ipConnectPanel;
        return ipConnectPanel;
    }
    /**
     * 获取GamePanel，并把当前的Panel设置为GamePane
     * @return gamepanel
     */
    public GamePanel getGamePanel() {
        currentPanel = gamePanel;
        return gamePanel;
    }

    /**
     * 只获取gamepanel
     * @return gamePanel
     */
    public GamePanel getGamePanelOnly() {
        return gamePanel;
    }

    /**
     * 获取现在的界面
     * @return 当前界面
     */
    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    /**
     * 获取MainFrame，主要用于构造Panel
     * @return mainFrame
     */
    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * 点击
     * @param e
     * 鼠标事件
     */
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

    /**
     * 最小或关闭
     * @param e
     * 鼠标事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == minimize) {
            this.setExtendedState(ICONIFIED);
        } else if (e.getSource() == close) {
            quit();
        }
    }

    /**
     * 拖动窗口
     * @param e
     * 鼠标事件
     */
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
