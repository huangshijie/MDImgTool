package org.huang.mdimgtool.app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
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

public class DrawRect extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6279503059714657182L;
	int x, y, x2, y2;

    DrawRect() {
    	
        x = y = x2 = y2 = 0; // 
        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

    public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw=Math.abs(x-x2);
        int ph=Math.abs(y-y2);
        g.drawRect(px, py, pw, ph);
    }

    class MyMouseListener extends MouseAdapter {
    	
    	Point start = new Point();
		Point end = new Point();

        public void mousePressed(MouseEvent e) {
        	start = e.getPoint();
            setStartPoint(e.getX(), e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
        	end = e.getPoint();
			
            setEndPoint(e.getX(), e.getY());
            repaint();
            
            Rectangle captureRect = new Rectangle(start, new Dimension(Math.abs(end.x - start.x), Math.abs(end.y - start.y)));
			try {
				Robot robot = new Robot();
				String format = "jpg";
				String fileName = "screenShot." + format;

				BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
				ImageIO.write(screenFullImage, format, new File(fileName));

			} catch (AWTException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        drawPerfectRect(g, x, y, x2, y2);
    }

}