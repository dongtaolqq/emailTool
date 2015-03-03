package org.wx.ui.event.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.springframework.stereotype.Component;

@Component
public class EventImplButtonMoveItem extends MouseAdapter {
	
	@Override
	public void mouseUp(MouseEvent arg0) {
		Button button = (Button) arg0.getSource();
		String text = button.getText();
		IMoveItemProcess buttonMoveItemProcess = (IMoveItemProcess) button.getData();
		if(StringUtils.equals(text, "<<") || StringUtils.equals(text, ">>"))
			buttonMoveItemProcess.moveAll();
		else
			buttonMoveItemProcess.move();
	}
	
}
