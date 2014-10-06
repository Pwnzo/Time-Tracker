package com.matthewteolis.timetracker.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.matthewteolis.timetracker.drivers.ClockDriver;

public class BtnStopListener implements ActionListener
{
	private ClockDriver clockDriver;
	
	public BtnStopListener(ClockDriver clockDriver)
	{
		this.clockDriver = clockDriver;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(this.clockDriver.isRunning())
		{
			this.clockDriver.stop();
		}
	}	
}