package ui.part;

import java.awt.Color;
import java.io.Serializable;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Point implements Serializable  // Serializable�����л��ӿڣ�����ʱ������������ĵ�Ķ���
{
	int x,y;  //������
	boolean f;  //�ж϶ϵ���ж�
	int co;  //��¼ÿ�������ɫ
	int cx;  //��¼���ʵĴ�ϸ
	int tool;  //��¼��ѡ��ĵĹ���
	
	public Point(int x,int y,boolean f,int co,int cx,int tool)
	{
		this.x = x;
		this.y = y;
		this.f = f;
		this.co = co;
		this.cx = cx;
		this.tool = tool;
	}
}