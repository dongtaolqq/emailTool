package org.wx.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wx.model.Model;
import org.wx.service.MailSenderService;

@Component
public class MailSender implements IRunnableWithProgress {

    private Map<String, Set<Model>> contentModel;

    @Autowired
    private MailSenderService       mailSenderService;

    private List<String>            titles;

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException,
                                             InterruptedException {
        int totalSize = contentModel.size();
        int doing = 1;
        monitor.beginTask("发送邮件", totalSize);
        for (Entry<String, Set<Model>> entry : contentModel.entrySet()) {
            String reciver = entry.getKey();
            Set<Model> models = entry.getValue();
            monitor.subTask("正在向接收者：" + reciver + "发送邮件。 整体进度（" + doing++ + "/" + totalSize + "）");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", titles);
            map.put("rows", models);
            String content = mailSenderService.generateMailContent(map);
            mailSenderService.sendWithTemplate(content, reciver);
            monitor.worked(1);
        }
        monitor.done();
    }

    public void setContentModel(Map<String, Set<Model>> contentModel) {
        this.contentModel = contentModel;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
