package org.wx.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wx.model.Model;
import org.wx.ui.exception.NotChooseContentColumnException;

@Component
public class DataService {

    private Logger                   logger            = LoggerFactory.getLogger(DataService.class);

    private Map<String, Set<Model>> reciverWithModels  = new TreeMap<String, Set<Model>>();

    private List<String>             titles            = new LinkedList<String>();
    
    private List<String>			 choosedTitles 	   = new LinkedList<String>();

    private List<Integer>            contentColumn     = new LinkedList<Integer>();

    private String                   filePath;

    private int                      reciverIndex;
    
    private Sheet sheet = null;
    private Workbook workBook;
    private int rows = 0;
    private int columns = 0;

    public void buildReciverModels() throws NullPointerException, NotChooseContentColumnException{
    	if (CollectionUtils.isEmpty(contentColumn)) throw new NotChooseContentColumnException(); 
    	if (rows < 1) throw new NullPointerException();
    	buildContentTitles();
    	reciverWithModels.clear();
    	
    	for(int row = 1; row < rows; row++){
    		List<String> contents = new LinkedList<String>();
    		for(Integer column : contentColumn){
    			String cell_content = sheet.getCell(column, row).getContents();
    			contents.add(cell_content);
    		}
    		String reciver = sheet.getCell(reciverIndex, row).getContents();
    		Set<Model> models = reciverWithModels.get(reciver);
    		if(models == null){
				models = new HashSet<Model>();
				reciverWithModels.put(reciver, models);
			}
    		models.add(new Model(contents));
    	}
    }
    
    public void buildContentTitles(){
    	String titles[] = getTitles();
    	choosedTitles.clear();
    	generateList(choosedTitles, titles);
    }
    
    private void generateList(List<String> container, String[] resources){
    	
    	for(Integer c_column : contentColumn){
    		container.add(resources[c_column]);
    	}
    }
    
    public void setContentColumn(Collection<Integer> contentColumn) {
        this.contentColumn.clear();
        this.contentColumn.addAll(contentColumn);
    }

    public void setReciverColumn(int index) {
        reciverIndex = index;
    }

    public String[] getTitles() {
    	String _titles[] = new String[titles.size()];
    	return titles.toArray(_titles);
    }

    public void readTitles() throws IOException {
    	
    	for(int i = 0; i < columns; i++){
    		titles.add(sheet.getCell(i, 0).getContents());
    	}
    	
    }
    /**
     * 获取指定邮件接收者对应的集合
     * @param key	员工编号
     * @return
     */
    public Set<Model> chooseOneReciver(Integer key) {
        return reciverWithModels.get(key);
    }
    
    public Map<String, Set<Model>> getReciverWithModels() {
		return reciverWithModels;
	}

	/**
     * 有多少个邮件接收者
     * @return
     */
    public int howManyReciver() {
        return reciverWithModels.size();
    }
    
    public void loadResource() throws BiffException, IOException {
        workBook = Workbook.getWorkbook(new FileInputStream(filePath));
        sheet = workBook.getSheet(0);
        rows = sheet.getRows();
        columns = sheet.getColumns();
    }

    public void unloadResource() {
    	if(workBook != null)
    		workBook.close();
    }

    public void setFilePath(String filePath) {
        clear();
        this.filePath = filePath;
    }
    private void clear(){
    	logger.info("Clear data before this import!!");
    	reciverWithModels.clear();
    	titles.clear();
    }
    
    public String getFilePath() {
        return filePath;
    }

	public List<String> getChoosedTitles() {
		return choosedTitles;
	}
    
}
