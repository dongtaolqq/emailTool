package org.wx.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ui.text")
public class UITextProperties {

    /** 上传文件触发按钮名 */
    private String uploadFileButton;
    /** 显示选择的文件名路径标签名 */
    private String uploadFileLabel;
    /** 发送邮件按钮名 */
    private String sendEmailButton;
    private String buttonRightMoveAll;
    private String buttonRightMove;
    private String buttonLeftMoveAll;
    private String buttonLeftMove;
    private String labelChooseReciverColumn;
    private String labelChooseContentColumn;
    private String buttonMoveUp;
    private String buttonMoveDown;
    private String fileDialogFilterNames;
    private String fileDialogFilterExtensions;
    private String fileDialogText;

    public String getUploadFileButton() {
        return uploadFileButton;
    }

    public void setUploadFileButton(String uploadFileButton) {
        this.uploadFileButton = uploadFileButton;
    }

    public String getUploadFileLabel() {
        return uploadFileLabel;
    }

    public void setUploadFileLabel(String uploadFileLabel) {
        this.uploadFileLabel = uploadFileLabel;
    }

    public String getSendEmailButton() {
        return sendEmailButton;
    }

    public void setSendEmailButton(String sendEmailButton) {
        this.sendEmailButton = sendEmailButton;
    }

    public String getButtonRightMoveAll() {
        return buttonRightMoveAll;
    }

    public void setButtonRightMoveAll(String buttonRightMoveAll) {
        this.buttonRightMoveAll = buttonRightMoveAll;
    }

    public String getButtonRightMove() {
        return buttonRightMove;
    }

    public void setButtonRightMove(String buttonRightMove) {
        this.buttonRightMove = buttonRightMove;
    }

    public String getButtonLeftMoveAll() {
        return buttonLeftMoveAll;
    }

    public void setButtonLeftMoveAll(String buttonLeftMoveAll) {
        this.buttonLeftMoveAll = buttonLeftMoveAll;
    }

    public String getButtonLeftMove() {
        return buttonLeftMove;
    }

    public void setButtonLeftMove(String buttonLeftMove) {
        this.buttonLeftMove = buttonLeftMove;
    }

    public String getLabelChooseReciverColumn() {
        return labelChooseReciverColumn;
    }

    public void setLabelChooseReciverColumn(String labelChooseReciverColumn) {
        this.labelChooseReciverColumn = labelChooseReciverColumn;
    }

    public String getLabelChooseContentColumn() {
        return labelChooseContentColumn;
    }

    public void setLabelChooseContentColumn(String labelChooseContentColumn) {
        this.labelChooseContentColumn = labelChooseContentColumn;
    }

    public String getButtonMoveUp() {
        return buttonMoveUp;
    }

    public void setButtonMoveUp(String buttonMoveUp) {
        this.buttonMoveUp = buttonMoveUp;
    }

    public String getButtonMoveDown() {
        return buttonMoveDown;
    }

    public void setButtonMoveDown(String buttonMoveDown) {
        this.buttonMoveDown = buttonMoveDown;
    }

    public String getFileDialogFilterNames() {
        return fileDialogFilterNames;
    }

    public void setFileDialogFilterNames(String fileDialogFilterNames) {
        this.fileDialogFilterNames = fileDialogFilterNames;
    }

    public String getFileDialogFilterExtensions() {
        return fileDialogFilterExtensions;
    }

    public void setFileDialogFilterExtensions(String fileDialogFilterExtensions) {
        this.fileDialogFilterExtensions = fileDialogFilterExtensions;
    }

    public String getFileDialogText() {
        return fileDialogText;
    }

    public void setFileDialogText(String fileDialogText) {
        this.fileDialogText = fileDialogText;
    }

}
