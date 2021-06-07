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

public class PaintPanel extends JPanel implements ActionListener    //��ͼ�����
{

	private ImageManager imageManager = ImageManager.getDefaultImageManager();

	//�����ϵı���
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;


	List<Point> list = new ArrayList<Point>();  //����һ��List�����
    Point p1,p2;
    int x1,y1,x2,y2;
    int chooseTool = 1;//�жϵ�ǰ���õĹ���,Ĭ��ΪǦ��

    private JButton jButtonClear, jButtonCancel;  //��ջ���,�ͳ�����һ�λ���ͼ���İ�ť
    private JPanel jPanelTools;   //��������Ĺ����������
    private JButton jButtonEraser,jButtonBrush;  //��Ƥ�ͻ��ʵ�ѡ��ť
    private JButton jButtonBrush1,jButtonBrush2,jButtonBrush3,jButtonBrush4;  //�����ĸ����ʴ�ϸ�İ�ť
    private JLabel jLabelTool,jLabelBrush, jLabelColor;  //������ʾ�����ı�ǩ
	//ѡ����ɫ�İ�ť
    private JButton jButtonColor1, jButtonColor2, jButtonColor3, jButtonColor4, jButtonColor5, jButtonColor6, jButtonColor7;

	//��������
    private Font paintLabelFont = FontManager.getDefaultFontManager().getPaintLabelFont();

    private Color color;
    int chooseColor = 1;//Ĭ�Ϻ�ɫ
    private int n;
    static boolean clear = false;
    static boolean cancel = false;
    public int id;   //��¼�û�ID���Ա㻭����

    DatagramSocket ds;
    MainFrame mainFrame;

	public PaintPanel(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;

		//�����ǹ����������jp
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
    	jButtonBrush1 = new JButton("4");  //��ֵ���
    	jButtonBrush1.addActionListener(writerListener);
    	jButtonBrush2 = new JButton("3");  //�ڶ��ֵ���
    	jButtonBrush2.addActionListener(writerListener);
    	jButtonBrush3 = new JButton("2");  //�����ֵ���
    	jButtonBrush3.addActionListener(writerListener);
    	jButtonBrush4 = new JButton("1");  //��ϸ����
    	jButtonBrush4.addActionListener(writerListener);
    	jButtonBrush1.setBounds(325, 47, 52, 29);
    	jButtonBrush2.setBounds(380, 47, 52, 29);
    	jButtonBrush3.setBounds(434, 47, 61, 29);
    	jButtonBrush4.setBounds(497,47, 60, 29);

    	jLabelTool = new JLabel("����:");
    	jLabelTool.setFont(paintLabelFont);
		jLabelTool.setForeground(Color.BLUE);
    	jLabelTool.setBounds(270, 7, 52, 29);
    	jLabelBrush = new JLabel("���ʴ�ϸ:");
    	jLabelBrush.setFont(paintLabelFont);
		jLabelBrush.setForeground(Color.BLUE);
    	jLabelBrush.setBounds(258, 47, 77, 29);
    	jLabelColor = new JLabel("������ɫ:");
    	jLabelColor.setFont(paintLabelFont);
		jLabelColor.setForeground(Color.BLUE);
    	jLabelColor.setBounds(7, 7, 77, 29);

		ImageIcon brushIcon = new ImageIcon(imageManager.getBrushImage());
		ImageIcon eraserIcon = new ImageIcon(imageManager.getEraserImage());
    	jButtonEraser = new JButton(brushIcon);  //Ǧ�ʰ�ť
    	jButtonBrush = new JButton(eraserIcon);  //��Ƥ��ť
		jButtonEraser.setContentAreaFilled(false);
		jButtonBrush.setContentAreaFilled(false);
    	ChangeListener changeListener = new ChangeListener();
    	jButtonEraser.addActionListener(changeListener);
    	jButtonBrush.addActionListener(changeListener);
    	jButtonEraser.setBounds(380, 7, 52, 29);
    	jButtonBrush.setBounds(325, 7, 52, 29);

    	ColorListener colorListener = new ColorListener();  //����һ����ɫ������
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

    	 //�����ǻ���͹��������������pp
    	setLayout(null);
    	setBackground(Color.WHITE);
    	setBounds(0, 0, WIDTH, 500);
    	add(jPanelTools);
    	PaintListener paintListener = new PaintListener();  //����ļ�����
    	addMouseListener(paintListener);  //����������ӵ�������
	    addMouseMotionListener(paintListener);
	}



private class PaintListener implements MouseListener,MouseMotionListener //дһ�����������Ի�������
{

	public void mouseDragged(MouseEvent e) //����϶�
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
	public void mousePressed(MouseEvent e) //��갴��
	{
	    //��ÿ���µõ�����������µĵ�Ķ�����ӵ�list������
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
	public void mouseReleased(MouseEvent e) //���̧��ʱ
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

private class ClearListener implements ActionListener  //�����ļ�����
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

private class CancelListener implements ActionListener  //�����ļ�����
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

private class ColorListener implements ActionListener  //������ɫ�ļ�����
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

public class WriterListener implements ActionListener  //���ʴ�ϸ�ļ�����
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

public void actionPerformed(ActionEvent e)       //���ʺ���Ƥ֮���л��ļ�����
{
	if(e.getSource()==jButtonEraser)   //Ǧ��
		chooseTool = 1;
	else  //��Ƥ
		chooseTool = 2;
}

public class ChangeListener implements ActionListener    //���ʺ���Ƥ֮���л��ļ�����
{
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jButtonEraser)
			chooseTool = 1;
		if(e.getSource()==jButtonBrush)
			chooseTool = 2;
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