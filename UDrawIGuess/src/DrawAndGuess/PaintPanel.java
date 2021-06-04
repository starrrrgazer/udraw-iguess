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

public class PaintPanel extends JPanel implements ActionListener    //��ͼ�����
{
	//�����ϵı���
	List<Point> list = new ArrayList<Point>();  //����һ��List�����
    Point p1,p2;
    int x1,y1,x2,y2;
    int flag = 1;//�жϵ�ǰ���õĹ���,Ĭ��ΪǦ��
    
    Font f1 = new Font("����",Font.PLAIN,14);
    Font f2 = new Font("����",Font.PLAIN,12);
    private JButton jb1,jb4;  //��ջ���,�ͳ�����һ�λ���ͼ���İ�ť
    private JPanel jp;   //��������Ĺ����������
    private JButton jb2,jb3;  //��Ƥ�ͻ��ʵ�ѡ��ť
    private JButton jb5,jb6,jb7,jb8;  //�����ĸ����ʴ�ϸ�İ�ť
    private JLabel jl1,jl2,jl3,jl4;  //������ʾ�����ı�ǩ
    private JButton cjb1,cjb2,cjb3,cjb4,cjb5,cjb6,cjb7;  //ѡ����ɫ�İ�ť
    
    private Color c;
    int co = 1;
    private int n;
    static boolean clear = false;
    static boolean cancel = false;
    int id;   //��¼�û�ID���Ա㻭����
    
    DatagramSocket ds;
    DrawAndGuess tc;
  
    
	public PaintPanel(DrawAndGuess tc)
	{
		this.tc = tc;
		
		//�����ǹ����������jp
    	jb1 = new JButton("���");
    	jb1.setFont(f2);
    	ClearListener cl1 = new ClearListener();
    	jb1.addActionListener(cl1);
    	jb1.setBounds(497,7, 60, 29);
    	CancelListener cl2 = new CancelListener();
    	jb4 = new JButton("����");
    	jb4.setFont(f2);
    	jb4.addActionListener(cl2);
    	jb4.setBounds(434, 7, 61, 29);
    	
    	WriterListener wl = new WriterListener();
    	jb5 = new JButton(new ImageIcon("G:\\java\\13.jpg"));  //��ֵ���
    	jb5.addActionListener(wl);
    	jb6 = new JButton(new ImageIcon("G:\\java\\14.jpg"));  //�ڶ��ֵ���
    	jb6.addActionListener(wl);
    	jb7 = new JButton(new ImageIcon("G:\\java\\15.jpg"));  //�����ֵ���
    	jb7.addActionListener(wl);
    	jb8 = new JButton(new ImageIcon("G:\\java\\16.jpg"));  //��ϸ����
    	jb8.addActionListener(wl);
    	jb5.setBounds(325, 47, 52, 29);
    	jb6.setBounds(380, 47, 52, 29);
    	jb7.setBounds(434, 47, 61, 29);
    	jb8.setBounds(497,47, 60, 29);
    	
    	jl1 = new JLabel("����:");
    	jl1.setFont(f1);
		jl1.setForeground(Color.BLUE);
    	jl1.setBounds(270, 7, 52, 29);
    	jl2 = new JLabel("���ʴ�ϸ:");
    	jl2.setFont(f1);
		jl2.setForeground(Color.BLUE);
    	jl2.setBounds(258, 47, 77, 29);
    	jl3 = new JLabel("������ɫ:");
    	jl3.setFont(f1);
		jl3.setForeground(Color.BLUE);
    	jl3.setBounds(7, 7, 77, 29);
		
    	jb2 = new JButton(new ImageIcon("G:\\java\\12.jpg"));  //Ǧ�ʰ�ť
    	jb3 = new JButton(new ImageIcon("G:\\java\\11.jpg"));  //��Ƥ��ť
    	ChangeListener cl3 = new ChangeListener();
    	jb2.addActionListener(cl3);
    	jb3.addActionListener(cl3);
    	jb2.setBounds(380, 7, 52, 29);
    	jb3.setBounds(325, 7, 52, 29);
    	
    	ColorListener cler = new ColorListener();  //����һ����ɫ������
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
    	
    	 //�����ǻ���͹��������������pp
    	setLayout(null);
    	setBackground(Color.WHITE);
    	setBounds(77, 70, 560, 599);
    	add(jp);
    	PaintListener l = new PaintListener();  //����ļ�����
    	addMouseListener(l);  //����������ӵ�������
	    addMouseMotionListener(l);
	}



private class PaintListener implements MouseListener,MouseMotionListener //дһ�����������Ի�������
{
	
	public void mouseDragged(MouseEvent e) //����϶�
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
	public void mousePressed(MouseEvent e) //��갴��
	{
	    //��ÿ���µõ�����������µĵ�Ķ�����ӵ�list������
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
	public void mouseReleased(MouseEvent e) //���̧��ʱ
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

private class ClearListener implements ActionListener  //�����ļ�����
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

private class CancelListener implements ActionListener  //�����ļ�����
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

private class ColorListener implements ActionListener  //������ɫ�ļ�����
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

public class WriterListener implements ActionListener  //���ʴ�ϸ�ļ�����
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

public void actionPerformed(ActionEvent e)       //���ʺ���Ƥ֮���л��ļ�����
{
	if(e.getSource()==jb2)   //Ǧ��
		flag = 1;
	else  //��Ƥ
		flag = 2;
}

public class ChangeListener implements ActionListener    //���ʺ���Ƥ֮���л��ļ�����
{
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jb2)
			flag = 1;
		if(e.getSource()==jb3)
			flag = 2;
	}
	
}

public void paint(Graphics g)  //����
 {
	 super.paint(g);
	 Graphics2D g2 = (Graphics2D)g;
	 if(!clear)
	 {
		 for(int i=0;i<list.size()-1;i++)  //�����������е�Ԫ��������ĵ㶼������
	     {
	    		
	    		if(list.get(i)==null)
	    			continue;
	        	//�õ�����������������Ԫ�أ�����װ��������
	    		p1=(Point)list.get(i);  //�õ���iλ��Ԫ��
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
		    			g2.drawLine(p1.x,p1.y, p2.x, p2.y);  //����		
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