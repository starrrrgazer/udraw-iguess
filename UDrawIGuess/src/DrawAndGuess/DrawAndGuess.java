package DrawAndGuess;
import ui.mergeFace.MainPanel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class DrawAndGuess extends JFrame
{
int set = 0;
	//面版的变量
	private  String TheCorrectAnswer = "";
	private String nowAnswer = "";
	public static DrawAndGuess client = launch.client;
	
	int i = 0;
	int iii = 11;
	int Score = 0;
	int OtherScore = 0;
	
	private JLabel l5;
	private JTextField t4 ,  Change ,Delete;
	private JPopupMenu popup;  
	private MyAction Changeaction;
	private JPanel p,pr,pd;
	private TextArea ta1;  //聊天的区域
	private JButton b1;   //清空的按钮
	private AboutDialog dialog;
	
	private JTextField udpTf;
	private JButton b2;
	
	public MainPanel mp = new MainPanel(this);
	Font f1 = new Font("宋体",Font.PLAIN,12);
	
    public DrawAndGuess()  //构造方法
	{
		
	}
	
	public void launchFrame()  //主界面版出现的方法
	{
		mp.tb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog  = new AboutDialog(client);
				dialog.setVisible(true);
			}
		});
		mp.tb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nowAnswer = mp.getproblem().getText().trim();
				System.out.println(nowAnswer);
			}
		});
		mp.tb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		//总面板p
		p = new JPanel();
		p.setLayout(new BorderLayout());
		
		popup = new JPopupMenu();
		Changeaction = new MyAction("修改");
		popup.add(Changeaction);
		p.setComponentPopupMenu(popup);
		
		
	
	    //右面的聊天面版
	    pr = new JPanel();  //聊天的面版
	    pr.setLayout(null);
	    l5 = new JLabel("消息记录");  //聊天面版上方的标签
	    ta1 = new TextArea();  //聊天的对话框
	    t4 = new JTextField();  //聊天的输入语言框
	    t4.addActionListener(new TFListener());
	    l5.setBounds(0,7, 100, 20);
	    ta1.setBounds(0, 30, 250, 400);
	    t4.setBounds(0, 430, 140, 25);
	    udpTf = new JTextField();
	    udpTf.setText("6666");
	    udpTf.setBounds(0,450, 140, 25);
		udpTf.setColumns(10);
		b2 = new JButton("链接");
		b2.setFont(f1);
		b2.addActionListener(new CListener());
		b2.setBounds(30, 480, 60, 30);
		pr.add(udpTf);
		pr.add(b2);
	    pr.add(l5);
	    pr.add(ta1);
	    pr.add(t4);
		
	    
	    //下面的画笔区域
	    pd = new JPanel();
	    pd.setLayout(null);
	
	    pr.setBounds(650, 0, 142, 520);
	    pd.setBounds(0, 470, 800, 1);
	    mp.setBounds(0, 0, 650, 599);
	    p.add(mp);
	    p.add(pr);
	    p.add(pd);
	    
	    
	    //添加主面版，并设置框架
	    getContentPane().add(p);
	    setSize(800, 600);
	    setLocation(280, 70);
	    addWindowListener(new WidowClosingListener());
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    
	}
	

	
	private class TFListener implements ActionListener  //定义一个监听器，当按Enter键时，清空写字板和缓存区
	{
		public void actionPerformed(ActionEvent e) 
		{
			String str = t4.getText().trim();  //trim()是去掉两边的空格
			t4.setText("");  //清空写字板

		}
		
	}
	
	private class WidowClosingListener extends WindowAdapter  //关闭窗口时的监听器，让连接断开
	{
		   public void windowClosing(WindowEvent e3)
	       {
	       }
	}
	
	private class CListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==b2)
			{
			}
		}
		
	}

   
	 public boolean CheckAnswer(){
		 	System.out.println(mp.getAnswer().getText());
		 	System.out.println(TheCorrectAnswer);
	    	if(mp.getAnswer().getText().equals(TheCorrectAnswer))
	    		return true;
	    	else{
	    		return false;
	    	}
	    		
	    }
	 
	    private class AboutDialog extends JDialog
	    {
	    	public AboutDialog(JFrame owner){
	    		super(owner , "判断框", true);
	    		if(CheckAnswer()){
	    			add(new JLabel(
	    					"<html><h1><i> 正确！</i></h1><hr> 恭喜答对题目。加一分</html>"), BorderLayout.CENTER);
	    			Score++;
	    		}
	    		else{
	    			add(new JLabel(
	    					"<html><h1><i> 错误！</i></h1><hr> 你太废了。。。。。</html>"), BorderLayout.CENTER);
	    		}
	    		JPanel panel = new JPanel();
	    		JButton ok = new JButton("确定");
	    		
	    		ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							mp.getPlayerScore().setText(String.valueOf(Score));
							setVisible(false);
					}
				});
	    		
	    		panel.add(ok);
	    		add(panel , BorderLayout.SOUTH);
	    		setSize(300,300);
	    	}
	    }
	
	
	    private class ChangeDialog extends JDialog {
	    		public ChangeDialog(JFrame owner){
		    		super(owner , "判断框", true);
		    		Change = new JTextField("" , 60);
		    		Delete = new JTextField("" , 60);
		    		
		    		JPanel panel = new JPanel();
		    		JPanel p2 = new JPanel();
		    		JButton ok = new JButton("确定修改");
		    		JButton deleteButton = new JButton("确定删除");
		    		
		    		deleteButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

						}
					});
		    		
		    		ok.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

						}
					});
		    		p2.setLayout(new GridLayout(1,2));
		    		p2.add(Change);
		    		p2.add(Delete);
		    		add(p2,BorderLayout.NORTH);
		    		panel.add(ok); panel.add(deleteButton);
		    		add(panel , BorderLayout.SOUTH);
		    		setSize(200 , 100);
	    		}
	    }

	    private class MyAction extends AbstractAction{
	    	public MyAction(String name){
	    		super(name);
	    	}
	    	public void actionPerformed(ActionEvent  e){
	    		 ChangeDialog dialog = new ChangeDialog(client);
	    		 dialog.setVisible(true);
	    	}
	    }


}
