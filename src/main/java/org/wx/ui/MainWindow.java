package org.wx.ui;

import java.awt.Toolkit;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.wx.ui.event.impl.MoveItemProcess;

public class MainWindow extends Shell implements ApplicationContextAware {

    private Logger             logger = LoggerFactory.getLogger(MainWindow.class);

    private ApplicationContext contenxt;

    /**
     * 初始化UI组件
     */
    private void init() {
        logger.info("开始初始化UI组件.");
        try {
            this.setImage(new Image(this.getDisplay(), new ClassPathResource("email.png")
                .getInputStream()));
        } catch (IOException e) {
            logger.error("设置应用图标失败!", e);
        }
        this.setText("邮件发送工具");

        List from = contenxt.getBean("list_from", List.class);
        List to = contenxt.getBean("list_to", List.class);
        MoveItemProcess m1 = contenxt.getBean(MoveItemProcess.class);
        MoveItemProcess m2 = contenxt.getBean(MoveItemProcess.class);

        m1.setList_from(from);
        m1.setList_to(to);
        from.addListener(SWT.MouseDoubleClick, m1);

        m2.setList_from(to);
        m2.setList_to(from);
        to.addListener(SWT.MouseDoubleClick, m2);
    }

    /**
     * 启动显示整个UI窗口界面
     */
    public void start() {
        try {
            init();
            centerShell();
            this.open();
            this.layout();
            while (!this.isDisposed()) {
                if (!this.getDisplay().readAndDispatch()) {
                    this.getDisplay().sleep();
                }
            }
        } catch (Exception e) {
            logger.error("初始化应用窗口出错!", e);
            e.printStackTrace();
        }
    }

    private void centerShell() {
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        int shellHeight = this.getBounds().height;
        int shellWidth = this.getBounds().width;

        if (shellHeight > screenHeight)
            shellHeight = screenHeight;
        if (shellWidth > screenWidth)
            shellWidth = screenWidth;

        setLocation((screenWidth - shellWidth) / 2, (screenHeight - shellHeight) / 2);

    }

    @Override
    protected void checkSubclass() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.contenxt = applicationContext;
    }
}
