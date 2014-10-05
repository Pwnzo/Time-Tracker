package com.matthewteolis.timetracker;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrameNew extends JFrame
{	
	public MyFrameNew()
	{
		this.setTitle("Time Tracker");
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 213);
		this.setIconImage(new ImageIcon(MyFrameNew.class.getResource("images/timer.png")).getImage());
		add();
		this.setVisible(true);
	}
	
	private void add()
	{
		
	}
}
