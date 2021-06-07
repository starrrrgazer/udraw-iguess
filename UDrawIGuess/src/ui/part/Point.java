package ui.part;

import java.awt.Color;
import java.io.Serializable;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Point implements Serializable  // Serializable是序列化接口，画的时候鼠标所经过的点的对象
{
	int x,y;  //两个点
	boolean f;  //判断断电的判断
	int co;  //记录每个点的颜色
	int cx;  //记录画笔的粗细
	int tool;  //记录所选择的的工具
	
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