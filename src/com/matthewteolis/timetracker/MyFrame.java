package com.matthewteolis.timer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.matthewteolis.timer.objects.Clock;


@SuppressWarnings("serial")
public class MyFrame extends JFrame implements Runnable
{
	
	private JPanel contentPane;
	private final JTextField textField = new JTextField();
	private boolean running;
	private Clock clock;
	private long millis, timeStopped;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
					frame.createThread();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void init()
	{
		running = false;
		clock = new Clock();
		timeStopped = 0;
	}
	
	/**
	 * Create the frame.
	 */
	public MyFrame()
	{
		init();
		setTitle("Time Tracker");
		setResizable(false);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 213);
		setIconImage(new ImageIcon(MyFrame.class.getResource("images/timer.png")).getImage());
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/* Temporarily disabled text highlight, due to crashing when update() method is called */
		textField.removeMouseListener(textField.getMouseListeners()[2]);
		textField.removeMouseMotionListener(textField.getMouseMotionListeners()[1]);
//		textField.setSelectionColor(new Color(200, 50, 50));
//		textField.setSelectedTextColor(Color.WHITE);
		
		textField.setFont(new Font("Monospaced", Font.PLAIN, 86));
		textField.setEditable(false);
		textField.setText(clock.toString());
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(10, 11, 424, 127);
		contentPane.add(textField);
		
		JButton btnStart = new JButton("Start");
		btnStart.setToolTipText("Starts the timer");
		btnStart.setBackground(Color.LIGHT_GRAY);
		btnStart.setBounds(10, 149, 89, 23);
		btnStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(!running)
				{
					millis = System.currentTimeMillis() - timeStopped;
					setRunning(true);
				}
			}
		});
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(180, 149, 89, 23);
		btnStop.setToolTipText("Stops the timer from counting, does not clear time");
		btnStop.setBackground(Color.LIGHT_GRAY);
		btnStop.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(running)
				{
					timeStopped = System.currentTimeMillis() - millis;
					setRunning(false);
				}
			}
		});
		contentPane.add(btnStop);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(345, 149, 89, 23);
		btnReset.setToolTipText("Resets the timer to 00:00:00");
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				millis = System.currentTimeMillis();
				reset();
			}
		});
		contentPane.add(btnReset);
	}
	
	public void createThread()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			if(running && System.currentTimeMillis() >= millis + 1000)
			{
				clock.tickSecond();
				millis = System.currentTimeMillis();
			}
			update();
		}
	}
	
	private void update()
	{
		textField.setText(clock.toString());
	}
	
	private void setRunning(boolean value)
	{
		running = value;
	}
	
	private void reset()
	{
		clock.setHour(0);
		clock.setMinute(0);
		clock.setSecond(0);
	}
}
