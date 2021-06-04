package DrawAndGuess;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;
import java.util.*;

import javax.swing.*;

public class PaintPanel extends JPanel implements ActionListener    //画图板的类
{
	//画板上的变量
	List<Point> list = new ArrayList<Point>();  //声明一个List，存点
    Point p1,p2;
    int x1,y1,x2,y2;
    int flag = 1;//判断当前所用的工具,默认为铅笔
    
    Font f1 = new Font("宋体",Font.PLAIN,14);
    Font f2 = new Font("宋体",Font.PLAIN,12);
    private JButton jb1,jb4;  //清空画板,和撤销上一次画的图案的按钮
    private JPanel jp;   //画板下面的工具栏的面版
    private JButton jb2,jb3;  //橡皮和画笔的选择按钮
    private JButton jb5,jb6,jb7,jb8;  //设置四个画笔粗细的按钮
    private JLabel jl1,jl2,jl3,jl4;  //设置提示操作的标签
    private JButton cjb1,cjb2,cjb3,cjb4,cjb5,cjb6,cjb7;  //选择颜色的按钮
    
    private Color c;
    int co = 1;
    private int n;
    static boolean clear = false;
    static boolean cancel = false;
    int id;   //记录用户ID，以便画出来
    
    DatagramSocket ds;
    DrawAndGuess tc;
  
    
	public PaintPanel(DrawAndGuess tc)
	{
		this.tc = tc;
		
		//下面是工具栏的面版jp
    	jb1 = new JButton("清空");
    	jb1.setFont(f2);
    	ClearListener cl1 = new ClearListener();
    	jb1.addActionListener(cl1);
    	jb1.setBounds(497,7, 60, 29);
    	CancelListener cl2 = new CancelListener();
    	jb4 = new JButton("撤消");
    	jb4.setFont(f2);
    	jb4.addActionListener(cl2);
    	jb4.setBounds(434, 7, 61, 29);
    	
    	WriterListener wl = new WriterListener();
    	jb5 = new JButton(new ImageIcon("G:\\java\\13.jpg"));  //最粗的线
    	jb5.addActionListener(wl);
    	jb6 = new JButton(new ImageIcon("G:\\java\\14.jpg"));  //第二粗的线
    	jb6.addActionListener(wl);
    	jb7 = new JButton(new ImageIcon("G:\\java\\15.jpg"));  //第三粗的线
    	jb7.addActionListener(wl);
    	jb8 = new JButton(new ImageIcon("G:\\java\\16.jpg"));  //最细的线
    	jb8.addActionListener(wl);
    	jb5.setBounds(325, 47, 52, 29);
    	jb6.setBounds(380, 47, 52, 29);
    	jb7.setBounds(434, 47, 61, 29);
    	jb8.setBounds(497,47, 60, 29);
    	
    	jl1 = new JLabel("工具:");
    	jl1.setFont(f1);
		jl1.setForeground(Color.BLUE);
    	jl1.setBounds(270, 7, 52, 29);
    	jl2 = new JLabel("画笔粗细:");
    	jl2.setFont(f1);
		jl2.setForeground(Color.BLUE);
    	jl2.setBounds(258, 47, 77, 29);
    	jl3 = new JLabel("画笔颜色:");
    	jl3.setFont(f1);
		jl3.setForeground(Color.BLUE);
    	jl3.setBounds(7, 7, 77, 29);
		
    	jb2 = new JButton(new ImageIcon("G:\\java\\12.jpg"));  //铅笔按钮
    	jb3 = new JButton(new ImageIcon("G:\\java\\11.jpg"));  //橡皮按钮
    	ChangeListener cl3 = new ChangeListener();
    	jb2.addActionListener(cl3);
    	jb3.addActionListener(cl3);
    	jb2.setBounds(380, 7, 52, 29);
    	jb3.setBounds(325, 7, 52, 29);
    	
    	ColorListener cler = new ColorListener();  //定义一个颜色监听器
    	cjb1 = new JButton();
    	cjb1.setBackground(Color.BLACK);
    	cjb1.addActionListener(cler);
    	cjb1.setBounds(70, 7, 52, 29);
    	cjb2 = new JButton();
    	cjb2.setBackground(Color.BLUE);
    	cjb2.addActionListener(cler);
    	cjb2.setBounds(130, 7, 52, 29);
    	cjb3 = new JButton();
    	cjb3.setBackground(Color.PINK);
    	cjb3.addActionListener(cler);
    	cjb3.setBounds(190, 7, 52, 29);
    	cjb4 = new JButton();
    	cjb4.setBackground(Color.YELLOW);
    	cjb4.addActionListener(cler);
    	cjb4.setBounds(7, 47, 52, 29);
    	cjb5 = new JButton();
    	cjb5.setBackground(Color.GREEN);
    	cjb5.addActionListener(cler);
    	cjb5.setBounds(70, 47, 52, 29);
    	cjb6 = new JButton();
    	cjb6.setBackground(Color.ORANGE);
    	cjb6.addActionListener(cler);
    	cjb6.setBounds(130, 47, 52, 29);
    	cjb7 = new JButton();
    	cjb7.setBackground(Color.RED);
    	cjb7.addActionListener(cler);
    	cjb7.setBounds(190, 47, 52, 29);
    
    	
    	jp = new JPanel();
    	jp.setBounds(0,418 , 590,83);
    	jp.setBackground(Color.LIGHT_GRAY);
    	jp.setLayout(null);
    	jp.add(jb1);
    	jp.add(jb2);
    	jp.add(jb3);
    	jp.add(jb4);
    	jp.add(jb5);
    	jp.add(jb6);
    	jp.add(jb7);
    	jp.add(jb8);
    	jp.add(jl1);
    	jp.add(jl2);
    	jp.add(jl3);
    	jp.add(cjb1);
    	jp.add(cjb2);
    	jp.add(cjb3);
    	jp.add(cjb4);
    	jp.add(cjb5);
    	jp.add(cjb6);
    	jp.add(cjb7);
    	
    	 //下面是画板和工具栏面版的总面版pp
    	setLayout(null);
    	setBackground(Color.WHITE);
    	setBounds(77, 70, 560, 599);
    	add(jp);
    	PaintListener l = new PaintListener();  //画版的监听器
    	addMouseListener(l);  //将监听器添加到画板中
	    addMouseMotionListener(l);
	}



private class PaintListener implements MouseListener,MouseMotionListener //写一个监听器可以画出曲线
{
	
	public void mouseDragged(MouseEvent e) //鼠标拖动
	{
		if(id==0)
		{
			switch(flag)
			{
			case 1:
				Point po1 = new Point(e.getPoint().x,e.getPoint().y,true,co,n,1);
				list.add(po1);
				PointNewMsg msg = new PointNewMsg(id,po1,clear,cancel);

				repaint();
				break;
			case 2:
				Point po2 = new Point(e.getPoint().x,e.getPoint().y,true,co,n,2);
				list.add(po2);
				PointNewMsg msg1 = new PointNewMsg(id,po2,clear,cancel);

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
			switch(flag)
			{
			case 1:
				Point po3 = new Point(e.getPoint().x,e.getPoint().y,true,co,n,1);
				list.add(po3);
				PointNewMsg msg = new PointNewMsg(id,po3,clear,cancel);
//System.out.println(""+tc);

				break;
			case 2:
				Point po4 = new Point(e.getPoint().x,e.getPoint().y,true,co,n,2);
				list.add(po4);
				PointNewMsg msg1 = new PointNewMsg(id,po4,clear,cancel);

				break;
			}
		}
	}
	public void mouseReleased(MouseEvent e) //鼠标抬起时
	{
		if(id==0)
		{
			switch(flag)
			{
			case 1:
				Point po5 = new Point(e.getPoint().x,e.getPoint().y,false,co,n,1);
				list.add(po5);
				PointNewMsg msg = new PointNewMsg(id,po5,clear,cancel);
				repaint();
				break;
			case 2:
				Point po6 = new Point(e.getPoint().x,e.getPoint().y,false,co,n,2);
				list.add(po6);
				PointNewMsg msg1 = new PointNewMsg(id,po6,clear,cancel);
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
					
		if((JButton)e.getSource()==jb1)
		{	
			//list.clear();
			clear = true;
			PointNewMsg msg = new PointNewMsg(id,new Point(1,1,false,co,n,2),clear,cancel);
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
			if((JButton)e.getSource()==jb4)
			{
				cancel = true;
				PointNewMsg msg = new PointNewMsg(id,new Point(1,1,false,co,n,2),clear,cancel);
				
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
			System.out.println("");
		}

	}
	
}

private class ColorListener implements ActionListener  //画笔颜色的监听器
{
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==cjb1)
			co = 1;
		if(e.getSource()==cjb2)
			co = 2;
		if(e.getSource()==cjb3)
			co = 3;
		if(e.getSource()==cjb4)
			co = 4;
		if(e.getSource()==cjb5)
			co = 5;
		if(e.getSource()==cjb6)
			co = 6;
		if(e.getSource()==cjb7)
			co = 7;
		
	}
	
}

public class WriterListener implements ActionListener  //画笔粗细的监听器
{

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jb5)
			n = 11;
		if(e.getSource()==jb6)
			n = 7;
		if(e.getSource()==jb7)
			n = 3;
		if(e.getSource()==jb8)
			n = 1;
		
	}
	
}

public void actionPerformed(ActionEvent e)       //画笔和橡皮之间切换的监听器
{
	if(e.getSource()==jb2)   //铅笔
		flag = 1;
	else  //橡皮
		flag = 2;
}

public class ChangeListener implements ActionListener    //画笔和橡皮之间切换的监听器
{
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jb2)
			flag = 1;
		if(e.getSource()==jb3)
			flag = 2;
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