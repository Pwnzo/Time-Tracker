package com.matthewteolis.timetracker.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.matthewteolis.timetracker.drivers.ClockDriver;

public class BtnResetListener implements ActionListener
{	
	private ClockDriver clockDriver;
	
	public BtnResetListener(ClockDriver clockDriver)
	{
		this.clockDriver = clockDriver;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.clockDriver.reset();
	}
}
