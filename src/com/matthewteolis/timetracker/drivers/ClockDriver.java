package com.matthewteolis.timetracker.drivers;

import com.matthewteolis.timetracker.lib.Clock;

public class ClockDriver implements Runnable
{	
	private Clock clock;
	private Thread threadClock;
	
	private boolean alive;
	private boolean running;
	
	private long millis, timeStopped;
	
	public ClockDriver()
	{
		this.running = false;
		this.alive = true;
		this.millis = 0;
		this.timeStopped = 0;
		this.clock = new Clock();
//		this.threadClock = new Thread(this, "Thread-Clock");
//		this.threadClock.start();		
	}
	
	public void start()
	{
		this.millis = System.currentTimeMillis() - this.timeStopped;
		this.running = true;
	}
	
	public void stop()
	{
		timeStopped = System.currentTimeMillis() - this.millis;
		this.running = false;
	}
	
	public boolean isAlive()
	{
		return this.alive;
	}
	
	public void destroy()
	{
		this.alive = false;
	}
	
	public void reset()
	{
		this.stop();
		this.millis = System.currentTimeMillis();
		this.clock.setHour(0);
		this.clock.setMinute(0);
		this.clock.setSecond(0);
	}
	
	public boolean isRunning()
	{
		return this.running;
	}
	
	public void setMillis(long millis)
	{
		this.millis = millis;
	}
	
	public long getMillis()
	{
		return this.millis;
	}
	
	public Clock getClock()
	{
		return this.clock;
	}
	
	@Override
	public void run()
	{
		this.alive = true;
		while(this.alive)
		{
			if(this.running && System.currentTimeMillis() >= this.millis + 1000)
			{
				this.millis += 1000;
				this.clock.tickSecond();
			}
		}
	}
}