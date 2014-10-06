package com.matthewteolis.timetracker.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.matthewteolis.timetracker.drivers.ClockDriver;

public class BtnStartListener implements ActionListener
{
	private ClockDriver clockDriver;
	
	public BtnStartListener(ClockDriver clockDriver)
	{
		this.clockDriver = clockDriver;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(!this.clockDriver.isRunning())
		{
			this.clockDriver.start();
		}
	}	
}