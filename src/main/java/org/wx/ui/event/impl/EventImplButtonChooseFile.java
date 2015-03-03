package org.wx.ui.event.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.wx.service.DataService;

import static org.wx.ui.util.UIUtils.errorPromot;

@Component
public class EventImplButtonChooseFile extends MouseAdapter {
	
	private Logger                logger = LoggerFactory.getLogger(EventImplButtonChooseFile.class);
	
	@Autowired
	FileDialog 					  fileDialog;
	
	@Autowired
    private Combo                 combo_titles;
    
	@Autowired
	@Qualifier("list_from")
    private List				  list_from;
	
	@Resource(name = "text_chooseFile")
    private Text				  text_chooseFile;
	
	@Autowired
	private DataService dataService;
	
	private Shell shell;
	
	@Override
	public void mouseUp(MouseEvent arg0) {
		Button button = (Button) arg0.getSource();
		this.shell = button.getShell();
		String filepath = fileDialog.open();
        if (StringUtils.isNotBlank(filepath)) {
        	text_chooseFile.setText(filepath);
            try {
                initDataService(filepath);
                String []titles = dataService.getTitles();
                initUIItems(titles);
            } catch (FileNotFoundException e1) {
                logger.error(e1.getMessage());
                e1.printStackTrace();
                errorPromot(shell, "找不到文件: " + filepath);
            } catch (IOException e1) {
                logger.error(e1.getMessage());
                e1.printStackTrace();
                errorPromot(shell, "读取内容错误");
            } catch (BiffException e) {
				e.printStackTrace();
			}
        }
	}
	private void initUIItems(String... items){
		combo_titles.setItems(items);
        combo_titles.select(0);
        list_from.setItems(items);
	}
	
	private void initDataService(String filepath) throws IOException, FileNotFoundException, BiffException{
		dataService.setFilePath(filepath);
		dataService.unloadResource();
		dataService.loadResource();
        dataService.readTitles();
	}
	
}
