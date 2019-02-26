package org.huang.mdimgtool.app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class App1 extends JFrame {

	private static final long serialVersionUID = 1L;

	public void screenShotMethod(JFrame appFrame) {

		// Firstly minimize this tool APP
		if (appFrame != null) {
			appFrame.setState(Frame.ICONIFIED);
		}

		// Secondly, show select area
		final JFrame ssFrame = new JFrame();
		ssFrame.setUndecorated(true);
		ssFrame.setOpacity(0.55f);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenSize.getHeight();
		ssFrame.setSize(screenSize);
		ssFrame.setVisible(true);

		MouseInputAdapter mia = new MouseInputAdapter() {

			Point start = new Point();
			Point end = new Point();

			public void mouseDragged(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				// Get start point
				start = e.getPoint();
				
				// Change cursor style
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				end = e.getPoint();
				
				// Change back to normal
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				
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
				repaint();
			}

		};

		ssFrame.addMouseListener(mia);
		ssFrame.addMouseMotionListener(mia);

		// Thirdly, after area was selected, show a pop-up and provide input to
		// fill in filename

		// Fourthly, use JGit API to upload this file to Git.

		// At last, restore this APP
	}
	
	public App1(String title) {
		this.setTitle(title);
		this.setVisible(true);
		this.setSize(300, 100);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});

		JPanel panel = new JPanel();
		this.add(panel);

		// Screen shot button
		JButton btnScreenCapture = new JButton("ScreenShot");
		btnScreenCapture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenShotMethod(App1.this);
			}
		});
		panel.add(btnScreenCapture);

		// Settings button
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("asdasda");
			}
		});
		panel.add(btnSettings);
	}
	
	// Initialize App frame
	public void initApp() {
		
	}
	

	class CapturePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		
	}
	
	class CaptureMouseListener extends MouseInputAdapter {
		
	}
	
	public static void main(String[] args) {
		new App1("sadas");
	}
}
