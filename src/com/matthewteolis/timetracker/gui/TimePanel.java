package com.matthewteolis.timetracker.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.matthewteolis.timetracker.drivers.ClockDriver;
import com.matthewteolis.timetracker.lib.Constants;
import com.matthewteolis.timetracker.listeners.BtnResetListener;
import com.matthewteolis.timetracker.listeners.BtnStartListener;
import com.matthewteolis.timetracker.listeners.BtnStopListener;

public class TimePanel extends JPanel implements Constants, Runnable
{	
	private JTextField txtTime;
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnReset;
	private ClockDriver clockDriver;
	private Thread threadUpdate;
	
	public TimePanel()
	{
		this.clockDriver = new ClockDriver();
		this.threadUpdate = new Thread(this, "Thread-TimePanelUpdate");
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		this.addTextField();
		this.addStartButton();
		this.addStopButton();
		this.addResetButton();
		this.threadUpdate.start();
	}
	
	private void addTextField()
	{
		this.txtTime = new JTextField();
		this.txtTime.removeMouseListener(this.txtTime.getMouseListeners()[2]);
		this.txtTime.removeMouseMotionListener(this.txtTime.getMouseMotionListeners()[1]);
		this.txtTime.setFont(new Font("Monospaced", Font.PLAIN, 86));
		this.txtTime.setEditable(false);
		this.txtTime.setBackground(Color.LIGHT_GRAY);
		this.txtTime.setBounds(10, 10, 424, 127);
		this.add(this.txtTime);
	}
	
	private void addStartButton()
	{
		this.btnStart = new JButton("Start");
		this.btnStart.setToolTipText(Constants.TOOLTIP_START);
		this.btnStart.setBackground(Color.LIGHT_GRAY);
		this.btnStart.setBounds(10, 149, 89, 23);
		this.btnStart.addActionListener(new BtnStartListener(this.clockDriver));
		this.add(this.btnStart);
	}
	
	private void addStopButton()
	{
		this.btnStop = new JButton("Stop");
		this.btnStop.setToolTipText(Constants.TOOLTIP_STOP);
		this.btnStop.setBackground(Color.LIGHT_GRAY);
		this.btnStop.setBounds(180, 149, 89, 23);
		this.btnStop.addActionListener(new BtnStopListener(this.clockDriver));
		this.add(this.btnStop);
	}
	
	private void addResetButton()
	{
		this.btnReset = new JButton("Reset");
		this.btnReset.setToolTipText(Constants.TOOLTIP_RESET);
		this.btnReset.setBackground(Color.LIGHT_GRAY);
		this.btnReset.setBounds(345, 149, 89, 23);
		this.btnReset.addActionListener(new BtnResetListener(this.clockDriver));
		this.add(this.btnReset);
	}
	
	public JTextField getTimeField()
	{
		return this.txtTime;
	}
	
	public JButton getBtnStart()
	{
		return this.btnStart;
	}
	
	public JButton getBtnStop()
	{
		return this.btnStop;
	}
	
	public JButton getBtnReset()
	{
		return this.btnReset;
	}
	
	@Override
	public void run()
	{
		while(this.clockDriver.isAlive())
		{
			if(this.clockDriver.isRunning() && System.currentTimeMillis() >= this.clockDriver.getMillis() + 1000)
			{
				this.clockDriver.getClock().tickSecond();
				this.clockDriver.setMillis(System.currentTimeMillis());
			}
			this.update();
		}
	}
	
	private void update()
	{
		this.txtTime.setText(this.clockDriver.getClock().toString());
	}
}
