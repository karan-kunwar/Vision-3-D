import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private JFrame frame;
	private static String title = "3D Animation";
	private static final int WIDTH= 800;
	private static final int HEIGHT= 600;
	private static boolean running =false;
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH,HEIGHT);
		this.setPreferredSize(size);
	}
	
	public synchronized void start() {
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
		// TODO Auto-generated method stub
		
	}

}
