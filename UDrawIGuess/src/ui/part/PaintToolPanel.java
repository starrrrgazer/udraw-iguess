package ui.part;

import Manager.FontManager;
import Manager.ImageManager;
import ui.mergeFace.MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.net.DatagramSocket;
import java.util.List;
import java.util.*;

import javax.swing.*;


public class PaintToolPanel extends JPanel implements ActionListener {
    private ImageManager imageManager = ImageManager.getDefaultImageManager();
    int chooseTool = 1;//判断当前所用的工具,默认为铅笔
    int x1,y1,x2,y2;
    Point p1,p2;

    private JButton jButtonClear, jButtonCancel;  //清空画板,和撤销上一次画的图案的按钮
    private JButton jButtonEraser,jButtonBrush;  //橡皮和画笔的选择按钮
    private JButton jButtonBrush1,jButtonBrush2,jButtonBrush3,jButtonBrush4;  //设置四个画笔粗细的按钮
    private JLabel jLabelTool,jLabelBrush, jLabelColor;  //设置提示操作的标签
    //选择颜色的按钮
    private JButton jButtonColor1, jButtonColor2, jButtonColor3, jButtonColor4, jButtonColor5, jButtonColor6, jButtonColor7;

    //设置字体
    private Font paintLabelFont = FontManager.getDefaultFontManager().getPaintLabelFont();

    private Color color;
    int chooseColor = 1;//默认黑色
     int n;
    boolean clear = false;
    boolean cancel = false;
    public int id;   //记录用户ID，以便画出来

    DatagramSocket ds;
    MainFrame mainFrame;

    private PaintToolPanel(){
        this.mainFrame=MainFrame.getMainFrame();

        //下面是工具栏的面版jp
        ImageIcon clearIcon = new ImageIcon(imageManager.getClearImage());
        jButtonClear = new JButton(clearIcon);
        jButtonClear.setFont(paintLabelFont);
        ClearListener clearListener = new ClearListener();
        jButtonClear.addActionListener(clearListener);
        jButtonClear.setBounds(497,7, 60, 29);
        jButtonClear.setContentAreaFilled(false);

        CancelListener cancelListener = new CancelListener();
        ImageIcon cancelIcon = new ImageIcon(imageManager.getCancelImage());
        jButtonCancel = new JButton(cancelIcon);
        jButtonCancel.setFont(paintLabelFont);
        jButtonCancel.addActionListener(cancelListener);
        jButtonCancel.setBounds(434, 7, 61, 29);
        jButtonCancel.setContentAreaFilled(false);

        WriterListener writerListener = new WriterListener();
        jButtonBrush1 = new JButton("4");  //最粗的线
        jButtonBrush1.addActionListener(writerListener);
        jButtonBrush2 = new JButton("3");  //第二粗的线
        jButtonBrush2.addActionListener(writerListener);
        jButtonBrush3 = new JButton("2");  //第三粗的线
        jButtonBrush3.addActionListener(writerListener);
        jButtonBrush4 = new JButton("1");  //最细的线
        jButtonBrush4.addActionListener(writerListener);
        jButtonBrush1.setBounds(325, 47, 52, 29);
        jButtonBrush2.setBounds(380, 47, 52, 29);
        jButtonBrush3.setBounds(434, 47, 61, 29);
        jButtonBrush4.setBounds(497,47, 60, 29);

        jLabelTool = new JLabel("工具:");
        jLabelTool.setFont(paintLabelFont);
        jLabelTool.setForeground(Color.BLUE);
        jLabelTool.setBounds(270, 7, 52, 29);
        jLabelBrush = new JLabel("画笔粗细:");
        jLabelBrush.setFont(paintLabelFont);
        jLabelBrush.setForeground(Color.BLUE);
        jLabelBrush.setBounds(258, 47, 77, 29);
        jLabelColor = new JLabel("画笔颜色:");
        jLabelColor.setFont(paintLabelFont);
        jLabelColor.setForeground(Color.BLUE);
        jLabelColor.setBounds(7, 7, 77, 29);

        ImageIcon brushIcon = new ImageIcon(imageManager.getBrushImage());
        ImageIcon eraserIcon = new ImageIcon(imageManager.getEraserImage());
        jButtonEraser = new JButton(brushIcon);  //铅笔按钮
        jButtonBrush = new JButton(eraserIcon);  //橡皮按钮
        jButtonEraser.setContentAreaFilled(false);
        jButtonBrush.setContentAreaFilled(false);
        ChangeListener changeListener = new ChangeListener();
        jButtonEraser.addActionListener(changeListener);
        jButtonBrush.addActionListener(changeListener);
        jButtonEraser.setBounds(380, 7, 52, 29);
        jButtonBrush.setBounds(325, 7, 52, 29);

        ColorListener colorListener = new ColorListener();  //定义一个颜色监听器
        jButtonColor1 = new JButton();
        jButtonColor1.setBackground(Color.BLACK);
        jButtonColor1.addActionListener(colorListener);
        jButtonColor1.setBounds(70, 7, 52, 29);
        jButtonColor2 = new JButton();
        jButtonColor2.setBackground(Color.BLUE);
        jButtonColor2.addActionListener(colorListener);
        jButtonColor2.setBounds(130, 7, 52, 29);
        jButtonColor3 = new JButton();
        jButtonColor3.setBackground(Color.PINK);
        jButtonColor3.addActionListener(colorListener);
        jButtonColor3.setBounds(190, 7, 52, 29);
        jButtonColor4 = new JButton();
        jButtonColor4.setBackground(Color.YELLOW);
        jButtonColor4.addActionListener(colorListener);
        jButtonColor4.setBounds(7, 47, 52, 29);
        jButtonColor5 = new JButton();
        jButtonColor5.setBackground(Color.GREEN);
        jButtonColor5.addActionListener(colorListener);
        jButtonColor5.setBounds(70, 47, 52, 29);
        jButtonColor6 = new JButton();
        jButtonColor6.setBackground(Color.ORANGE);
        jButtonColor6.addActionListener(colorListener);
        jButtonColor6.setBounds(130, 47, 52, 29);
        jButtonColor7 = new JButton();
        jButtonColor7.setBackground(Color.RED);
        jButtonColor7.addActionListener(colorListener);
        jButtonColor7.setBounds(190, 47, 52, 29);


        setBounds(0,451 , 600,83);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        add(jButtonClear);
        add(jButtonEraser);
        add(jButtonBrush);
        add(jButtonCancel);
        add(jButtonBrush1);
        add(jButtonBrush2);
        add(jButtonBrush3);
        add(jButtonBrush4);
        add(jLabelTool);
        add(jLabelBrush);
        add(jLabelColor);
        add(jButtonColor1);
        add(jButtonColor2);
        add(jButtonColor3);
        add(jButtonColor4);
        add(jButtonColor5);
        add(jButtonColor6);
        add(jButtonColor7);
    }

    private static PaintToolPanel paintToolPanel=new PaintToolPanel();

    public static PaintToolPanel getPaintToolPanel() {
        return paintToolPanel;
    }

    private class ClearListener implements ActionListener  //清屏的监听器
    {
        public void actionPerformed(ActionEvent e)
        {

            if((JButton)e.getSource()== jButtonClear)
            {
                //list.clear();
                clear = true;
//			PointNewMsg msg = new PointNewMsg(id,new Point(1,1,false,co,n,2),clear,cancel);
                repaint();
            }
        }

    }

    private class CancelListener implements ActionListener  //撤销的监听器
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if((JButton)e.getSource()== jButtonCancel)
                {
                    cancel = true;
//				PointNewMsg msg = new PointNewMsg(id,new Point(1,1,false,co,n,2),clear,cancel);

                    if(PointList.getPointList().getList().size()>0)
                    {
                        int i = PointList.getPointList().getList().size()-1;
                        if(i>1){
                            while(PointList.getPointList().getList().get(PointList.getPointList().getList().size()-2).f)
                            {
                                System.out.println(i);
                                PointList.getPointList().getList().remove(i);
                                i--;
                            }
                        }
                        else
                        {
                            PointList.getPointList().getList().remove(1);
                            PointList.getPointList().getList().remove(0);
                        }
                        repaint();
                    }
                    cancel = false;
                }
            }catch(Exception ee)
            {
                System.out.println("cancel error");
            }

        }

    }


    private class ColorListener implements ActionListener  //画笔颜色的监听器
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==jButtonColor1)
                chooseColor = 1;
            if(e.getSource()== jButtonColor2)
                chooseColor = 2;
            if(e.getSource()== jButtonColor3)
                chooseColor = 3;
            if(e.getSource()== jButtonColor4)
                chooseColor = 4;
            if(e.getSource()== jButtonColor5)
                chooseColor = 5;
            if(e.getSource()== jButtonColor6)
                chooseColor = 6;
            if(e.getSource()== jButtonColor7)
                chooseColor = 7;

        }

    }

    public class WriterListener implements ActionListener  //画笔粗细的监听器
    {

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()== jButtonBrush1)
                n = 11;
            if(e.getSource()==jButtonBrush2)
                n = 7;
            if(e.getSource()==jButtonBrush3)
                n = 3;
            if(e.getSource()==jButtonBrush4)
                n = 1;

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButtonEraser)   //铅笔
            chooseTool = 1;
        else  //橡皮
            chooseTool = 2;
    }

    public class ChangeListener implements ActionListener    //画笔和橡皮之间切换的监听器
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==jButtonEraser)
                chooseTool = 1;
            if(e.getSource()==jButtonBrush)
                chooseTool = 2;
        }

    }

}
