package org.huang.mdimgtool.app;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.huang.mdimgtool.util.Utils;

public class AppMain{
	
	private static void createAndShowGUI() {
		// Create and set up the window
		JFrame frame = new JFrame("MDImgTool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel appPanel = new JPanel();
		frame.add(appPanel);
		
		ScreenCaptureListener captureListener = new ScreenCaptureListener(frame);
		// Screen shot button
		JButton btnScreenCapture = new JButton("ScreenShot");
		btnScreenCapture.addActionListener(captureListener);
		appPanel.add(btnScreenCapture);

		// Settings button
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("asdasda");
			}
		});
		appPanel.add(btnSettings);
		
		frame.setSize(300, 100);
		frame.setVisible(true);
		
	}
	
	static class ScreenCaptureListener implements ActionListener {
		
		private JFrame appFrame;
		
		ScreenCaptureListener(JFrame appFrame) {
			this.appFrame = appFrame;
		}

		public void actionPerformed(ActionEvent e) {
			
			// minimize this tool APP
			if (appFrame != null) {
				appFrame.setState(Frame.ICONIFIED);
			}

			// crate capture operation area
			JFrame captureFrame = new JFrame();
			captureFrame.setUndecorated(true);
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			captureFrame.setSize(screenSize);
					
			ContentPanel contentPane = new ContentPanel();
			captureFrame.setContentPane(contentPane);
			contentPane.setVisible(true);
			
			// add this full screen to background
			CapturePanel capturePane = new CapturePanel(appFrame, captureFrame);
			capturePane.setOpaque(false);
			captureFrame.setGlassPane(capturePane);
			capturePane.setVisible(true);
			
			captureFrame.setVisible(true);
		}
	}
	
	static class ContentPanel extends JPanel{
		private static final long serialVersionUID = 3767466677847605307L;
		
		public void paintComponent(Graphics g) {
			// capture full screen
			try {

				if(Utils.screenSelectedImage == null) {
					Robot robot = new Robot();
					Utils.screenSelectedImage = robot.createScreenCapture(new Rectangle(0, 0, (int)this.getWidth(), (int)this.getHeight()));
				}
				
				ImageIcon icon=new ImageIcon(Utils.screenSelectedImage);
				Image img=icon.getImage();
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
					
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
