package com.matthewteolis.timetracker.gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame
{	
	public MyFrame()
	{
		super("Time Tracker");
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 213);
		this.setIconImage(new ImageIcon(MyFrame.class.getResource("../images/timer.png")).getImage());
		this.init();
		this.setVisible(true);
	}
	
	private void init()
	{
		TimePanel timePanel = new TimePanel();
		this.add(timePanel);
	}
}
