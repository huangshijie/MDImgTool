package org.huang.mdimgtool.service;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ClipboardService {
	
	public static void setClipboardString (String txt) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		Transferable trans = new StringSelection(txt);
		clipboard.setContents(trans, null);
	}
	
}
