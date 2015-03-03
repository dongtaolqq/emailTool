package org.wx.ui.event.impl;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EventImplButtonUpDown extends MouseAdapter {
	
	@Autowired
	@Qualifier("list_to")
	private List list_to;

	@Override
	public void mouseUp(MouseEvent event) {
		Button button = (Button) event.getSource();
		int moveStep = (Integer) button.getData();
		
		int totalCount = list_to.getItemCount();
		if(totalCount == 0) return;
		
		int selectIndex = list_to.getSelectionIndex();
		if(selectIndex == -1) return;
		
		int handleIndex = selectIndex + moveStep;
		if(handleIndex < 0 || handleIndex > totalCount - 1) return;
		
		String selectItem = list_to.getItem(selectIndex);
		
		String selectItemNext = list_to.getItem(handleIndex);
		list_to.setItem(selectIndex, selectItemNext);
		list_to.setItem(handleIndex, selectItem);
		list_to.setSelection(handleIndex);
		
	}
}
