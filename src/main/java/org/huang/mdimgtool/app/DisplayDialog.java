package org.huang.mdimgtool.app;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.huang.mdimgtool.service.ClipboardService;
import org.huang.mdimgtool.service.GitService;
import org.huang.mdimgtool.util.Utils;

public class DisplayDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisplayDialog (BufferedImage selectedImgBuffer) {
		
		final BufferedImage finalSelectedImgBuffer = selectedImgBuffer;
		
		Container container = getContentPane();
		container.setLayout(null);
		
		JLabel imgLabel = new JLabel(new ImageIcon(finalSelectedImgBuffer));
		container.add(imgLabel);
		imgLabel.setBounds(100, 100, finalSelectedImgBuffer.getWidth(), finalSelectedImgBuffer.getHeight());
		
		final JTextField txtFileName = new JTextField(20);
		container.add(txtFileName);
		txtFileName.setBounds(10, 10, 200, 30);
		
		JButton btnSave = new JButton("Save");
		container.add(btnSave);
		btnSave.setBounds(250,10,100,30);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = txtFileName.getText();
				
				GitService.gitPush(finalSelectedImgBuffer, fileName);
				
				ClipboardService.setClipboardString(Utils.GIT_REMOTE_LOCATION + "/blob/master/" + fileName + Utils.FILE_SPLIT + Utils.IMG_FORMAT);
				
				dispose();
			}
		});
	}
}
