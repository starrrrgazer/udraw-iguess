package DrawAndGuess;
import ui.mergeFace.MainPanel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class DrawAndGuess extends JFrame
{
int set = 0;
	//���ı���
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
	private TextArea ta1;  //���������
	private JButton b1;   //��յİ�ť
	private AboutDialog dialog;
	
	private JTextField udpTf;
	private JButton b2;
	
	public MainPanel mp = new MainPanel(this);
	Font f1 = new Font("����",Font.PLAIN,12);
	
    public DrawAndGuess()  //���췽��
	{
		
	}
	
	public void launchFrame()  //���������ֵķ���
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
		//�����p
		p = new JPanel();
		p.setLayout(new BorderLayout());
		
		popup = new JPopupMenu();
		Changeaction = new MyAction("�޸�");
		popup.add(Changeaction);
		p.setComponentPopupMenu(popup);
		
		
	
	    //������������
	    pr = new JPanel();  //��������
	    pr.setLayout(null);
	    l5 = new JLabel("��Ϣ��¼");  //��������Ϸ��ı�ǩ
	    ta1 = new TextArea();  //����ĶԻ���
	    t4 = new JTextField();  //������������Կ�
	    t4.addActionListener(new TFListener());
	    l5.setBounds(0,7, 100, 20);
	    ta1.setBounds(0, 30, 250, 400);
	    t4.setBounds(0, 430, 140, 25);
	    udpTf = new JTextField();
	    udpTf.setText("6666");
	    udpTf.setBounds(0,450, 140, 25);
		udpTf.setColumns(10);
		b2 = new JButton("����");
		b2.setFont(f1);
		b2.addActionListener(new CListener());
		b2.setBounds(30, 480, 60, 30);
		pr.add(udpTf);
		pr.add(b2);
	    pr.add(l5);
	    pr.add(ta1);
	    pr.add(t4);
		
	    
	    //����Ļ�������
	    pd = new JPanel();
	    pd.setLayout(null);
	
	    pr.setBounds(650, 0, 142, 520);
	    pd.setBounds(0, 470, 800, 1);
	    mp.setBounds(0, 0, 650, 599);
	    p.add(mp);
	    p.add(pr);
	    p.add(pd);
	    
	    
	    //�������棬�����ÿ��
	    getContentPane().add(p);
	    setSize(800, 600);
	    setLocation(280, 70);
	    addWindowListener(new WidowClosingListener());
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    
	}
	

	
	private class TFListener implements ActionListener  //����һ��������������Enter��ʱ�����д�ְ�ͻ�����
	{
		public void actionPerformed(ActionEvent e) 
		{
			String str = t4.getText().trim();  //trim()��ȥ�����ߵĿո�
			t4.setText("");  //���д�ְ�

		}
		
	}
	
	private class WidowClosingListener extends WindowAdapter  //�رմ���ʱ�ļ������������ӶϿ�
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
	    		super(owner , "�жϿ�", true);
	    		if(CheckAnswer()){
	    			add(new JLabel(
	    					"<html><h1><i> ��ȷ��</i></h1><hr> ��ϲ�����Ŀ����һ��</html>"), BorderLayout.CENTER);
	    			Score++;
	    		}
	    		else{
	    			add(new JLabel(
	    					"<html><h1><i> ����</i></h1><hr> ��̫���ˡ���������</html>"), BorderLayout.CENTER);
	    		}
	    		JPanel panel = new JPanel();
	    		JButton ok = new JButton("ȷ��");
	    		
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
		    		super(owner , "�жϿ�", true);
		    		Change = new JTextField("" , 60);
		    		Delete = new JTextField("" , 60);
		    		
		    		JPanel panel = new JPanel();
		    		JPanel p2 = new JPanel();
		    		JButton ok = new JButton("ȷ���޸�");
		    		JButton deleteButton = new JButton("ȷ��ɾ��");
		    		
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
