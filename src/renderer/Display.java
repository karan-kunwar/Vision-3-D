package renderer;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;	
import renderer.input.Mouse;

import javax.swing.JButton;
import javax.swing.JFrame;
import renderer.entity.entityManager;

public class Display extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private JFrame frame;
	private static String title = "3D Animation";
	public static final int WIDTH= 800;
	public static final int HEIGHT= 600;
	private static boolean running =false;
	private static double step=0.7;
	private entityManager entityManager;
	private Mouse mouse;
	private static String shape;
	private static boolean Mode=true;
	private JButton ModeButton;
	private JButton SpeedIncButton;
	private JButton SpeedDecButton;
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH,HEIGHT);
		this.setPreferredSize(size);
		this.mouse = new Mouse();
		this.entityManager = new entityManager();
		
		
		this.ModeButton = new JButton("Mode Switch");
		
		ModeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Mode=!Mode;
				}catch (Exception err) {
					err.printStackTrace();
				}
			}
		});
		
		
		ModeButton.setBounds(110, 34, 146, 25);
		this.frame.getContentPane().add(ModeButton);
		
		this.SpeedIncButton = new JButton("Speed Increement");
		
		SpeedIncButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{	
					if(!Mode) {
						entityManager.speedx+=step;
						entityManager.speedy+=step;
						entityManager.speedz+=step;
					}
				}catch (Exception err) {
					err.printStackTrace();
				}
			}
		});
		
		
		SpeedIncButton.setBounds(270, 34, 200, 25);
		this.frame.getContentPane().add(SpeedIncButton);
		
		this.SpeedDecButton = new JButton("Speed Decreement");
		
		SpeedDecButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(entityManager.speedx>1)
						entityManager.speedx-=step;
					if(entityManager.speedy>1)
						entityManager.speedy-=step;
					if(entityManager.speedz>1)
						entityManager.speedz-=step;
				}catch (Exception err) {
					err.printStackTrace();
				}
			}
		});
		
		
		SpeedDecButton.setBounds(480, 34, 200, 25);
		this.frame.getContentPane().add(SpeedDecButton);
		
		this.addMouseListener(this.mouse);
		this.addMouseMotionListener(this.mouse);
		this.addMouseWheelListener(this.mouse);
	}
	
	public synchronized void start(){
		running = true; 
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}
	
	public synchronized void stop() {
		running= false;
		try {
			this.thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace(); 
		}
	}
	
	public static void main(String[] args) {
		
		shape=args[1].toString();
		Mode=Boolean.parseBoolean(args[2]);
		
		Display display = new Display();
		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);
		
		
		display.start();
	}
	
	@Override
	public void run() {
		long lasttime= System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/90;
		double delta = 0;
		int frames=0;
		this.entityManager.init(shape);
		while(running) {
			long now = System.nanoTime();
			delta += (now-lasttime)/ns;
			lasttime = now;
			
			while(delta>=1) {
				update();
				delta--;
				render();
				frames++;
			}

			if(System.currentTimeMillis()-timer>=1000) {
				timer+=1000;
				this.frame.setTitle(title+" - "+ frames+ "fps");
				frames=0;
			}
		}
		stop();
	}

	private void render() {

		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(64,64,64));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		this.entityManager.render(g);
		g.dispose();
		bs.show();
		
	}

	private void update() {
		if(Mode==true)
		this.entityManager.update(this.mouse);
		else
		{
			this.entityManager.update();
		}
			
	}
}
