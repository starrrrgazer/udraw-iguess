package ui.mergeFace;

import DrawAndGuess.DrawAndGuess;
import ui.part.PaintPanel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.List;
import java.util.*;

import javax.swing.*;

public class MainPanel extends JPanel  //�м���������
{
	
	int i = 0;
    Font f1 = new Font("����",Font.PLAIN,14);
    Font f2 = new Font("����",Font.PLAIN,12);
    private JLabel l4;
    private JTextField t3;
    private JLabel l1,l2,l3,l5;  //����Ʒְ��б�ǩ
    private JTextField t1,t2;  //����Ʒְ���ı���
    private JTextArea t4;  //�𰸵��ı���
    public JButton tb,tb1,tb3;
    DrawAndGuess tc;
    private JPopupMenu popup;
    
    public PaintPanel pp = new PaintPanel(tc);
    
    public MainPanel(DrawAndGuess tc)
    {
    	this.tc = tc;
    	launch();
    }
    
    public void launch()  //��ʾ�������ķ���
    {
    	
    	pp = new PaintPanel(tc);
	  //������������Ŀ�ı�ǩ
    	l4 = new JLabel("��Ŀ��");
	    l4.setBounds(140, 23, 40, 40);
	    t3 = new JTextField();  //��Ŀ��
	    t3.setBounds(180,29, 210,30);
	    t3.setText("");
	    tb = new JButton("����");
	    tb.setFont(f1);
	    tb.setBounds(430, 27, 65, 34);
	    tb3 = new JButton("��Ŀȷ��");
	    tb3.setFont(f1);
	    tb3.setBounds(520, 27, 100, 34);
	  
	    t1 = new JTextField(7);
		t2 = new JTextField(7);
		l1 = new JLabel("�Ƿְ�");
		l2 = new JLabel("���1");
		l3 = new JLabel("���2");
		l1.setBounds(10, 70, 40, 70);
		l2.setBounds(10, 110, 50, 70);
		t1.setBounds(10, 160, 50, 30);
		l3.setBounds(10, 180, 50, 70);
		t2.setBounds(10, 240, 50, 30);
		l5 = new JLabel("���Ĵ𰸣�");
		l5.setFont(f1);
		l5.setForeground(Color.BLUE);
		l5.setBounds(3, 300, 90, 50);
		tb1 = new JButton("ȷ��");
		tb1.setFont(f2);
		tb1.setBounds(3, 470, 60, 30);
		t4 = new JTextArea();
		t4.setBounds(3, 340, 70, 70);
		
		popup = new JPopupMenu();
		

		setLayout(null);
		
		add(pp);
		add(l4);
		add(t3);
		add(l1);
		add(l2);
		add(l3);
		add(t1);
		add(t2);
		add(l5);
		add(t4);
		add(tb);
		add(tb1);
		add(tb3);
    }
    
    public JTextField getproblem(){
    	return t3; 
    }
    
    public JTextArea getAnswer(){
    	return t4;
    }
    
    public JButton getTb(){
    	return tb;
    }
    
    public JTextField getPlayerScore(){
    	if(pp.id == 0){
    		return t1;
    	}
    	else{ 
    		 return t2;
    	}
    }
}   