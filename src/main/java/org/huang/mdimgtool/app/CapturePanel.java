package org.huang.mdimgtool.app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.huang.mdimgtool.util.Utils;

public class CapturePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Point startPoint = new Point(0, 0);
	private Point endPoint = new Point(0, 0);
	private JFrame appFrame;
	private JFrame captureFrame;
	
	CapturePanel(JFrame appFrame, JFrame captureFrame) {
		this.appFrame = appFrame;
		this.captureFrame = captureFrame;
		
		CaptureMouseListener listener = new CaptureMouseListener();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
        
        int px = Math.min(startPoint.x,endPoint.x);
        int py = Math.min(startPoint.y,endPoint.y);
        int pw = Math.abs(startPoint.x-endPoint.x);
        int ph = Math.abs(startPoint.y-endPoint.y);
        
        g.drawRect(px, py, pw, ph);
	}

	class CaptureMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			setStartPoint(e.getPoint());
			
			// Change cursor style
			e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		public void mouseDragged(MouseEvent e) {
			setEndPoint(e.getPoint());
			repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			setEndPoint(e.getPoint());
			
			repaint();
			// Change back to normal
			e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
			Rectangle captureRect = new Rectangle(startPoint, new Dimension(Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y)));
			try {
				Robot robot = new Robot();

				BufferedImage screenSelectedImage = robot.createScreenCapture(captureRect);
				ImageIO.write(screenSelectedImage, Utils.IMG_FORMAT, new File(Utils.CAPTURE_SCREEN_FILE_NAME + Utils.FILE_SPLIT + Utils.IMG_FORMAT));
				
				// Firstly minimize this tool APP
				if (appFrame != null) {
					appFrame.setState(Frame.NORMAL);
				}
				
				if(captureFrame != null) {
					captureFrame.dispose();
					captureFrame = null;
				}
				
				// Utils.READY_TO_CAPTURE  = true;
			} catch (AWTException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
}
