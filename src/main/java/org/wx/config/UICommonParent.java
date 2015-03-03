package org.wx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.wx.config.props.UITextProperties;
import org.wx.ui.MainWindow;

@Configuration
public class UICommonParent {

    @Autowired
    protected MainWindow       parent;

    @Autowired
    protected UITextProperties uiProps;

    public MainWindow getParent() {
        return parent;
    }

    public void setParent(MainWindow parent) {
        this.parent = parent;
    }

    public UITextProperties getUiProps() {
        return uiProps;
    }

    public void setUiProps(UITextProperties uiProps) {
        this.uiProps = uiProps;
    }

}
