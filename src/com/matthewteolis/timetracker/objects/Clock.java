package com.matthewteolis.timer.objects;

public class Clock
{	
	private int hour;
	private int minute;
	private int second;
	
	public Clock()
	{
		this(0, 0, 0);
	}
	
	public Clock(int hour, int minute, int second)
	{
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
	}

	public int getHour()
	{
		return hour;
	}

	public void setHour(int hour)
	{
		if(hour < 0 || hour > 99)
		{
			this.hour = 0;
		}
		else
		{
			this.hour = hour;
		}
	}

	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		if(minute < 0 || minute > 59)
		{
			this.minute = 0;
		}
		else
		{
			this.minute = minute;
		}
	}

	public int getSecond()
	{
		return second;
	}

	public void setSecond(int second)
	{
		if(second < 0 || second > 59)
		{
			this.second = 0;
		}
		else
		{
			this.second = second;
		}
	}
	
	public void tickSecond()
	{
		++this.second;
		if(this.second >= 60)
		{
			this.second = 0;
			this.tickMinute();
		}
	}
	
	public void tickMinute()
	{
		++this.minute;
		if(this.minute >= 60)
		{
			this.minute = 0;
			this.tickHour();
		}
	}
	
	public void tickHour()
	{
		++this.hour;
		if(this.hour >= 100)
		{
			this.hour = 0;
		}
	}
	
	@Override
	public String toString()
	{
		return String.format("%02d:%02d:%02d", this.hour, this.minute, this.second);
	}
}
