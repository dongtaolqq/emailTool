package org.wx.ui.event.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.wx.facade.MailSender;
import org.wx.model.Model;
import org.wx.service.DataService;
import org.wx.ui.exception.NotChooseContentColumnException;
import org.wx.ui.util.UIUtils;

@Component
public class EventImplButtonSend extends MouseAdapter {
	
	private Logger logger = LoggerFactory.getLogger(EventImplButtonSend.class);
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	@Qualifier("list_to")
	private List				  list_to;
	
	@Autowired
    private ProgressMonitorDialog progressMonitorDialog;
	
	@Autowired
	private MailSender runnableWithProgress;

	@Override
	public void mouseUp(MouseEvent arg0) {
		Button button = (Button) arg0.getSource();
		Shell parent = button.getShell();
		setDataServiceColumnIndexs();
		try {
			dataService.buildReciverModels();
			Map<String, Set<Model>> reciverModel = dataService.getReciverWithModels();
			if(MapUtils.isEmpty(reciverModel)){
				UIUtils.errorPromot(parent, "没有接收者, 请查看数据是否正常，或联系作者.");
				return;
			}
			boolean confirm = MessageDialog.openConfirm(parent, "确认信息", dataService.howManyReciver()+"个接收者。");
			if(confirm){
				runnableWithProgress.setContentModel(reciverModel);
				runnableWithProgress.setTitles(dataService.getChoosedTitles());
				progressMonitorDialog.run(true, true, runnableWithProgress);
			}
		} catch (NullPointerException e){
			logger.error("未导入数据文件。");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NotChooseContentColumnException e) {
			UIUtils.errorPromot(parent, "需要选择发送内容列。");
		}
	}
	
	private void setDataServiceColumnIndexs(){
		String titles[] = dataService.getTitles();
		java.util.List<String> titleList = Arrays.asList(titles);
		String items[] = list_to.getItems();
		java.util.List<Integer> contentColumn = new LinkedList<Integer>();
		for(String item : items){
			contentColumn.add(titleList.indexOf(item));
		}
		dataService.setContentColumn(contentColumn);
	}
}
