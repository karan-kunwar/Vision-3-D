package renderer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;	
import renderer.input.Mouse;
import javax.swing.JFrame;
import renderer.point.MyPoint;
import renderer.point.PointConverter;
import renderer.input.ClickType;
import renderer.shapes.MyPolygons;
import renderer.shapes.Tetrahedron;

public class Display extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private JFrame frame;
	private static String title = "3D Animation";
	public static final int WIDTH= 800;
	public static final int HEIGHT= 600;
	private static boolean running =false;
	private Tetrahedron tetra; 
	private Mouse mouse;
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH,HEIGHT);
		this.setPreferredSize(size);
		this.mouse = new Mouse();
		
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
		final double ns = 1000000000.0/30;
		double delta = 0;
		int frames=0;
		init();
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

	private void init() {
		int s = 100;
		MyPoint p1 = new MyPoint(s/2,-s/2,-s/2);
		MyPoint p2 = new MyPoint(s/2,s/2,-s/2);
		MyPoint p3 = new MyPoint(s/2,s/2,s/2);
		MyPoint p4 = new MyPoint(s/2,-s/2,s/2);
		MyPoint p5 = new MyPoint(-s/2,-s/2,-s/2);
		MyPoint p6 = new MyPoint(-s/2,s/2,-s/2);
		MyPoint p7 = new MyPoint(-s/2,s/2,s/2);
		MyPoint p8 = new MyPoint(-s/2,-s/2,s/2);
		
		this.tetra = new Tetrahedron(
				new MyPolygons(Color.BLUE,p5,p6,p7,p8),
				new MyPolygons(Color.GREEN,p1,p2,p6,p5 ),
				new MyPolygons(Color.YELLOW,p4,p3,p7,p8),
				new MyPolygons(Color.WHITE,p1,p5,p8,p4),
				new MyPolygons(Color.ORANGE,p2,p6,p7,p3),
				new MyPolygons(Color.RED,p1,p2,p3,p4)
				);
		
	}
	private void render() {

		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		tetra.render(g);
		g.dispose();
		bs.show();
		
		
	}
//	ClickType prevClick = ClickType.Unknown;
	int initialX=0,initialY=0;
	private void update() {
		this.tetra.rotate(true, 1, -1, 2);
//		if(initialX)
//		int x= this.mouse.getX();
//		int y= this.mouse.getY();
//		if(this.mouse.getButton() == ClickType.LeftClick) {
//			int xDiff = x -initialX;
//			int yDiff = y -initialY;
//			this.tetra.rotate(true, 0, -(xDiff), -(yDiff));
//		}
//		initialX= x;
//		initialY= y;
//		 this.tetra.rotate(true, 1,0, 0);
		
	}
}
