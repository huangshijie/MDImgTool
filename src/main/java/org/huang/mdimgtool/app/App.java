package org.huang.mdimgtool.app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class App extends JFrame {

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
		ssFrame.setSize(screenSize);
		ssFrame.setVisible(true);

		MouseInputAdapter mia = new MouseInputAdapter() {

			Point start = new Point();
			Point end = new Point();

			public void mouseDragged(MouseEvent e) {
				Point process = e.getPoint();
				Rectangle captureRect = new Rectangle(start, new Dimension(Math.abs(process.x - start.x), Math.abs(process.y - start.y)));
				
				JPanel dragPanel = new JPanel();
				dragPanel.setSize(new Dimension(Math.abs(process.x - start.x), Math.abs(process.y - start.y)));
				
				ssFrame.add(dragPanel);
			}

			public void mousePressed(MouseEvent e) {
				start = e.getPoint();
			}

			public void mouseReleased(MouseEvent e) {
				end = e.getPoint();

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

		};

		ssFrame.addMouseListener(mia);
		ssFrame.addMouseMotionListener(mia);

		//
		// public void mouseMoved(MouseEvent e) {
		// start = e.getPoint();
		// }
		// });

		// Thirdly, after area was selected, show a pop-up and provide input to
		// fill in filename

		// Fourthly, use JGit API to upload this file to Git.

		// At last, restore this APP
		// if (appFrame != null) {
		// appFrame.setState(Frame.NORMAL);
		// }
	}

	public App(String title) {
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
		JButton shotB = new JButton("ScreenShot");
		shotB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenShotMethod(App.this);
			}
		});
		panel.add(shotB);

		// Settings button
		JButton settingsB = new JButton("Settings");
		settingsB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("asdasda");
			}
		});
		panel.add(settingsB);

	}

	public static void main(String[] args) {
		new App("sadas");
	}
}
