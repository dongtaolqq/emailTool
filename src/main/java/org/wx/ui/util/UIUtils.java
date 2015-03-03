package org.wx.ui.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class UIUtils {
	public static void errorPromot(Shell shell, String msg){
		MessageBox box = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
        box.setMessage(msg);
        box.setText("\u6ce8\u610f!");
        box.open();
	}
}
