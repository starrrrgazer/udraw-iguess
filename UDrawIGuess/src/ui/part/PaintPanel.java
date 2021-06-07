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

public class PaintPanel extends JPanel implements ActionListener    //画图板的类
{

	private ImageManager imageManager = ImageManager.getDefaultImageManager();

	//画板上的变量
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;


	List<Point> list = new ArrayList<Point>();  //声明一个List，存点
    Point p1,p2;
    int x1,y1,x2,y2;
    int chooseTool = 1;//判断当前所用的工具,默认为铅笔

    private JButton jButtonClear, jButtonCancel;  //清空画板,和撤销上一次画的图案的按钮
    private JPanel jPanelTools;   //画板下面的工具栏的面版
    private JButton jButtonEraser,jButtonBrush;  //橡皮和画笔的选择按钮
    private JButton jButtonBrush1,jButtonBrush2,jButtonBrush3,jButtonBrush4;  //设置四个画笔粗细的按钮
    private JLabel jLabelTool,jLabelBrush, jLabelColor;  //设置提示操作的标签
	//选择颜色的按钮
    private JButton jButtonColor1, jButtonColor2, jButtonColor3, jButtonColor4, jButtonColor5, jButtonColor6, jButtonColor7;

	//设置字体
    private Font paintLabelFont = FontManager.getDefaultFontManager().getPaintLabelFont();

    private Color color;
    int chooseColor = 1;//默认黑色
    private int n;
    static boolean clear = false;
    static boolean cancel = false;
    public int id;   //记录用户ID，以便画出来

    DatagramSocket ds;
    MainFrame mainFrame;

	public PaintPanel(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;

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


    	jPanelTools = new JPanel();
    	jPanelTools.setBounds(0,418 , WIDTH,83);
    	jPanelTools.setBackground(Color.LIGHT_GRAY);
    	jPanelTools.setLayout(null);
    	jPanelTools.add(jButtonClear);
    	jPanelTools.add(jButtonEraser);
    	jPanelTools.add(jButtonBrush);
    	jPanelTools.add(jButtonCancel);
    	jPanelTools.add(jButtonBrush1);
    	jPanelTools.add(jButtonBrush2);
    	jPanelTools.add(jButtonBrush3);
    	jPanelTools.add(jButtonBrush4);
    	jPanelTools.add(jLabelTool);
    	jPanelTools.add(jLabelBrush);
    	jPanelTools.add(jLabelColor);
    	jPanelTools.add(jButtonColor1);
    	jPanelTools.add(jButtonColor2);
    	jPanelTools.add(jButtonColor3);
    	jPanelTools.add(jButtonColor4);
    	jPanelTools.add(jButtonColor5);
    	jPanelTools.add(jButtonColor6);
    	jPanelTools.add(jButtonColor7);

    	 //下面是画板和工具栏面版的总面版pp
    	setLayout(null);
    	setBackground(Color.WHITE);
    	setBounds(0, 0, WIDTH, 500);
    	add(jPanelTools);
    	PaintListener paintListener = new PaintListener();  //画版的监听器
    	addMouseListener(paintListener);  //将监听器添加到画板中
	    addMouseMotionListener(paintListener);
	}



private class PaintListener implements MouseListener,MouseMotionListener //写一个监听器可以画出曲线
{

	public void mouseDragged(MouseEvent e) //鼠标拖动
	{
		if(id==0)
		{
			switch(chooseTool)
			{
			case 1:
				Point po1 = new Point(e.getPoint().x,e.getPoint().y,true, chooseColor,n,1);
				list.add(po1);
//				PointNewMsg msg = new PointNewMsg(id,po1,clear,cancel);
				repaint();
				break;
			case 2:
				Point po2 = new Point(e.getPoint().x,e.getPoint().y,true, chooseColor,n,2);
				list.add(po2);
//				PointNewMsg msg1 = new PointNewMsg(id,po2,clear,cancel);

				repaint();
				break;
			}
		}
	}
	public void mouseMoved(MouseEvent arg0)
	{
	}
	public void mouseClicked(MouseEvent arg0)
	{
	}
	public void mouseEntered(MouseEvent arg0)
	{
	}
	public void mouseExited(MouseEvent arg0)
	{
	}
	public void mousePressed(MouseEvent e) //鼠标按下
	{
	    //将每次新得到的鼠标所按下的点的对象添加到list数组中
		if(id==0)
		{
			switch(chooseTool)
			{
			case 1:
				Point po3 = new Point(e.getPoint().x,e.getPoint().y,true, chooseColor,n,1);
				list.add(po3);
//				PointNewMsg msg = new PointNewMsg(id,po3,clear,cancel);

				break;
			case 2:
				Point po4 = new Point(e.getPoint().x,e.getPoint().y,true, chooseColor,n,2);
				list.add(po4);
//				PointNewMsg msg1 = new PointNewMsg(id,po4,clear,cancel);

				break;
			}
		}
	}
	public void mouseReleased(MouseEvent e) //鼠标抬起时
	{
		if(id==0)
		{
			switch(chooseTool)
			{
			case 1:
				Point po5 = new Point(e.getPoint().x,e.getPoint().y,false, chooseColor,n,1);
				list.add(po5);
//				PointNewMsg msg = new PointNewMsg(id,po5,clear,cancel);
				repaint();
				break;
			case 2:
				Point po6 = new Point(e.getPoint().x,e.getPoint().y,false, chooseColor,n,2);
				list.add(po6);
//				PointNewMsg msg1 = new PointNewMsg(id,po6,clear,cancel);
				repaint();
				break;
			}
		}

	}

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

				if(list.size()>0)
				{
				  int i = list.size()-1;
				  while(list.get(list.size()-2).f)
				  {
					  list.remove(list.get(i));
					  i--;
				  }
				  if(i>=0)
				  {
					  list.remove(list.get(i));
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

public void actionPerformed(ActionEvent e)       //画笔和橡皮之间切换的监听器
{
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

public void paint(Graphics g)  //画笔
 {
	 super.paint(g);
	 Graphics2D g2 = (Graphics2D)g;
	 if(!clear)
	 {
		 for(int i=0;i<list.size()-1;i++)  //把数组中所有的元素所代表的点都画出来
	     {

	    		if(list.get(i)==null)
	    			continue;
	        	//得到数组中连续的两个元素，并包装成两个点
	    		p1=(Point)list.get(i);  //得到第i位的元素
	    		p2=(Point)list.get(i+1);
	    		switch (p1.tool)
				{
				case 1:
					if(p1.f==true)
		    		{
						switch(p1.co)
						{
						case 1:
							g2.setColor(Color.BLACK);
							break;
						case 2:
							g2.setColor(Color.BLUE);
						    break;
						case 3:
							g2.setColor(Color.PINK);
						    break;
						case 4:
							g2.setColor(Color.YELLOW);
						    break;
						case 5:
							g2.setColor(Color.GREEN);
						    break;
						case 6:
							g2.setColor(Color.ORANGE);
						    break;
						case 7:
							g2.setColor(Color.RED);
						    break;
						}

		    			g2.setStroke(new BasicStroke(p1.cx, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		    			g2.drawLine(p1.x,p1.y, p2.x, p2.y);  //画线
				    }
					break;
				case 2:
					setBackground(Color.WHITE);
					g2.clearRect(p1.x, p1.y, 20, 20);
					break;
				}
	     }
	 }
	 else
	 {
		 list.clear();
		 clear = false;
	 }

  }

}