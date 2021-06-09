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
	PaintToolPanel paintToolPanel;
	boolean clear;
	Point p1,p2;

    DatagramSocket ds;
    MainFrame mainFrame;

	private PaintPanel() {
		this.mainFrame = MainFrame.getMainFrame();
		this.paintToolPanel=PaintToolPanel.getPaintToolPanel();
		this.clear=this.paintToolPanel.clear;
		//下面是画板和工具栏面版的总面版pp
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, WIDTH, 450);
		PaintListener paintListener=new PaintListener();
		addMouseListener(paintListener);  //将监听器添加到画板中
		addMouseMotionListener(paintListener);

	}
	private static PaintPanel paintPanel=new PaintPanel();

	public static PaintPanel getPaintPanel() {
		return paintPanel;
	}

	private class PaintListener implements MouseListener,MouseMotionListener //写一个监听器可以画出曲线
	{

		public void mouseDragged(MouseEvent e) //鼠标拖动
		{
			if(paintToolPanel.id==0)
			{
				switch(paintToolPanel.chooseTool)
				{
					case 1:
						Point po1 = new Point(e.getPoint().x,e.getPoint().y,true, paintToolPanel.chooseColor,paintToolPanel.n,1);
						PointList.getPointList().getList().add(po1);
//				PointNewMsg msg = new PointNewMsg(id,po1,clear,cancel);
						repaint();
						break;
					case 2:
						Point po2 = new Point(e.getPoint().x,e.getPoint().y,true, paintToolPanel.chooseColor,paintToolPanel.n,2);
						PointList.getPointList().getList().add(po2);
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
			if(paintToolPanel.id==0)
			{
				switch(paintToolPanel.chooseTool)
				{
					case 1:
						Point po3 = new Point(e.getPoint().x,e.getPoint().y,true, paintToolPanel.chooseColor,paintToolPanel.n,1);
						PointList.getPointList().getList().add(po3);
//				PointNewMsg msg = new PointNewMsg(id,po3,clear,cancel);

						break;
					case 2:
						Point po4 = new Point(e.getPoint().x,e.getPoint().y,true, paintToolPanel.chooseColor,paintToolPanel.n,2);
						PointList.getPointList().getList().add(po4);
//				PointNewMsg msg1 = new PointNewMsg(id,po4,clear,cancel);

						break;
				}
			}
		}
		public void mouseReleased(MouseEvent e) //鼠标抬起时
		{
			if(paintToolPanel.id==0)
			{
				switch(paintToolPanel.chooseTool)
				{
					case 1:
						Point po5 = new Point(e.getPoint().x,e.getPoint().y,false, paintToolPanel.chooseColor,paintToolPanel.n,1);
						PointList.getPointList().getList().add(po5);
//				PointNewMsg msg = new PointNewMsg(id,po5,clear,cancel);
						repaint();
						break;
					case 2:
						Point po6 = new Point(e.getPoint().x,e.getPoint().y,false, paintToolPanel.chooseColor,paintToolPanel.n,2);
						PointList.getPointList().getList().add(po6);
//				PointNewMsg msg1 = new PointNewMsg(id,po6,clear,cancel);
						repaint();
						break;
				}
			}

		}

	}

	public void paint(Graphics g)  //画笔
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		if(!clear)
		{
			for(int i=0;i<PointList.getPointList().getList().size()-1;i++)  //把数组中所有的元素所代表的点都画出来
			{

				if(PointList.getPointList().getList().get(i)==null)
					continue;
				//得到数组中连续的两个元素，并包装成两个点
				p1=(Point)PointList.getPointList().getList().get(i);  //得到第i位的元素
				p2=(Point)PointList.getPointList().getList().get(i+1);
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
						g2.clearRect(paintToolPanel.p1.x, paintToolPanel.p1.y, 20, 20);
						break;
				}
			}
		}
		else
		{
			PointList.getPointList().getList().clear();
			clear = false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}