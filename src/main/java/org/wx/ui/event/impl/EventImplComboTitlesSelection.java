package org.wx.ui.event.impl;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wx.service.DataService;

@Component
public class EventImplComboTitlesSelection extends SelectionAdapter {
	
	private Logger                logger = LoggerFactory.getLogger(EventImplComboTitlesSelection.class);
	
	@Autowired
	DataService dataService;

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		Combo combo = (Combo) arg0.getSource();
		int titleIndex = combo.getSelectionIndex();
        logger.info("选中第{}个Title.", titleIndex);
        dataService.setReciverColumn(titleIndex);
	}
	
}
