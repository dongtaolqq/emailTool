package org.wx.service;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wx.TestCase;

public class DataServiceTest extends TestCase {

    @Autowired
    private DataService dataService;

    @Before
    public void setUp() throws BiffException, IOException {
        String path = getClass().getResource("/testData.xls").getPath();
        dataService.setFilePath(path);
        dataService.loadResource();
    }

    @Test
    public void testReadTitles() throws IOException {
        dataService.readTitles();
        String titles[] = dataService.getTitles();
        Assert.assertNotNull(titles);
        Assert.assertEquals(15, titles.length);
    }
    
    @After
    public void destroy(){
    	dataService.unloadResource();
    }
}
