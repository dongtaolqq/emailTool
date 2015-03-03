package org.wx.ui.event.impl;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MoveItemProcess implements IMoveItemProcess, Listener {
	
    private List				  list_from;
	    
    private List				  list_to;
	
    
	public List getList_from() {
		return list_from;
	}

	public void setList_from(List list_from) {
		this.list_from = list_from;
	}

	public List getList_to() {
		return list_to;
	}

	public void setList_to(List list_to) {
		this.list_to = list_to;
	}

	@Override
	public void move() {
		String items[] = list_from.getSelection();
		doMove(items);
	}
	
	@Override
	public void moveAll() {
		String items[] = list_from.getItems();
		doMove(items);
	}
	
	private void doMove(String... items){
		for(String item : items){
			list_from.remove(item);
			list_to.add(item);
		}
	}

	@Override
	public void handleEvent(Event arg0) {
		doMove(list_from.getSelection());
	}
}
