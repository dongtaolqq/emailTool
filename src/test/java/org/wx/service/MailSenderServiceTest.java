package org.wx.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wx.TestCase;
import org.wx.model.Model;

import com.google.common.collect.Lists;

public class MailSenderServiceTest extends TestCase {

    @Autowired
    private MailSenderService   mailSenderService;

    private Map<String, Object> map;
    private String              reciver;

    @Before
    public void initData() {
        reciver = "327722743";
        Set<Model> set = new HashSet<Model>();
        set.add(new Model(Lists.newArrayList("1", "2", "3")));
        set.add(new Model(Lists.newArrayList("4", "5", "6")));
        set.add(new Model(Lists.newArrayList("7", "8", "9")));
        map = new HashMap<String, Object>();
        map.put("title", new String[] { "A", "B", "C" });
        map.put("rows", set);
    }

    @Ignore
    @Test
    public void testSendWithTemplate() {
        mailSenderService.sendWithTemplate("Hello,world", reciver);
    }

    @Test
    public void testGenerateMailContent() {
        String content = mailSenderService.generateMailContent(map);
        Assert.assertNotNull(content);
        logger.info(content);
    }

    @After
    public void destroy() {
        map.clear();
    }
}
