package renderer.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private int mouseX = -1;
	private int mouseY = -1;
	private int mouseB = -1;
	private int scroll = 0;
	public boolean isDraging = false;
	
	public int getX() {
		return this.mouseX;
	}
	public int getY() {
		return this.mouseY;
	}
	public ClickType getButton() {
		switch (this.mouseB) {
		case 1:
			return ClickType.LeftClick;
		case 2:
			return ClickType.Scroll;
		case 3:
			return ClickType.RightClick;
		default:
			return ClickType.Unknown; 
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}
 
	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseB = e.getButton();
		this.isDraging = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseB =-1;
		this.isDraging = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isScrollingUp() {
		return this.scroll == -1;
	}
	public boolean isScrollingDown() {
		return this.scroll == 1;
	}
	public void resetScroll() {
		this.scroll = 0; 
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		this.scroll = e.getWheelRotation();
	}

}
