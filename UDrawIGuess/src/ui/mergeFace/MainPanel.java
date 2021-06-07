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

public class MainPanel extends JPanel  //中间主面板的类
{
	
	int i = 0;
    Font f1 = new Font("宋体",Font.PLAIN,14);
    Font f2 = new Font("宋体",Font.PLAIN,12);
    private JLabel l4;
    private JTextField t3;
    private JLabel l1,l2,l3,l5;  //左面计分板中标签
    private JTextField t1,t2;  //左面计分板的文本框
    private JTextArea t4;  //答案的文本框
    public JButton tb,tb1,tb3;
    DrawAndGuess tc;
    private JPopupMenu popup;
    
    public PaintPanel pp = new PaintPanel(tc);
    
    public MainPanel(DrawAndGuess tc)
    {
    	this.tc = tc;
    	launch();
    }
    
    public void launch()  //显示出主面板的方法
    {
    	
    	pp = new PaintPanel(tc);
	  //下面是上面题目的标签
    	l4 = new JLabel("题目：");
	    l4.setBounds(140, 23, 40, 40);
	    t3 = new JTextField();  //题目框
	    t3.setBounds(180,29, 210,30);
	    t3.setText("");
	    tb = new JButton("跳过");
	    tb.setFont(f1);
	    tb.setBounds(430, 27, 65, 34);
	    tb3 = new JButton("题目确定");
	    tb3.setFont(f1);
	    tb3.setBounds(520, 27, 100, 34);
	  
	    t1 = new JTextField(7);
		t2 = new JTextField(7);
		l1 = new JLabel("记分板");
		l2 = new JLabel("玩家1");
		l3 = new JLabel("玩家2");
		l1.setBounds(10, 70, 40, 70);
		l2.setBounds(10, 110, 50, 70);
		t1.setBounds(10, 160, 50, 30);
		l3.setBounds(10, 180, 50, 70);
		t2.setBounds(10, 240, 50, 30);
		l5 = new JLabel("您的答案：");
		l5.setFont(f1);
		l5.setForeground(Color.BLUE);
		l5.setBounds(3, 300, 90, 50);
		tb1 = new JButton("确定");
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