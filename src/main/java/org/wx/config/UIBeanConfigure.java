package org.wx.config;

import java.util.LinkedList;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wx.ui.MainWindow;
import org.wx.ui.event.impl.EventImplButtonChooseFile;
import org.wx.ui.event.impl.EventImplButtonMoveItem;
import org.wx.ui.event.impl.EventImplButtonSend;
import org.wx.ui.event.impl.EventImplButtonUpDown;
import org.wx.ui.event.impl.EventImplComboTitlesSelection;
import org.wx.ui.event.impl.MoveItemProcess;

@Configuration
public class UIBeanConfigure extends UICommonParent {

    @Bean
    public MainWindow mainWindow() {
        MainWindow main = new MainWindow();
        return main;
    }

    @Bean(name = "label_chooseFile")
    public Label labelChooseFile() {
        Label label = new Label(parent, SWT.NONE);
        label.setText(uiProps.getUploadFileLabel());
        label.setBounds(13, 10, 61, 17);
        return label;
    }

    @Bean(name = "text_chooseFile")
    public Text textChooseFile() {
        Text text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
        text.setBounds(80, 10, 136, 23);
        return text;
    }

    @Bean(name = "button_chooseFile")
    public Button buttonChooseFile(EventImplButtonChooseFile eventImplButtonChooseFile) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getUploadFileButton());
        button.setBounds(218, 10, 80, 27);
        button.addMouseListener(eventImplButtonChooseFile);
        return button;
    }

    @Bean(name = "buttons_moveItems")
    public java.util.List<Button> buttonMoveItems(@Qualifier("button_rightMove") Button button0,
                                                  @Qualifier("button_rightMoveAll") Button button1,
                                                  @Qualifier("button_leftMoveAll") Button button2,
                                                  @Qualifier("button_leftMove") Button button3) {
        java.util.List<Button> list = new LinkedList<Button>();
        list.add(button0);
        list.add(button1);
        list.add(button2);
        list.add(button3);
        return list;
    }

    @Bean(name = "button_rightMove")
    public Button buttonRightMove(MoveItemProcess process,
                                  EventImplButtonMoveItem eventImplButtonMoveItem,
                                  @Qualifier("list_from") List list0,
                                  @Qualifier("list_to") List list1) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getButtonRightMove());
        process.setList_from(list0);
        process.setList_to(list1);
        button.setData(process);
        button.addMouseListener(eventImplButtonMoveItem);
        button.setBounds(223, 136, 50, 30);
        return button;
    }

    @Bean(name = "button_rightMoveAll")
    public Button buttonRightMoveAll(MoveItemProcess process,
                                     EventImplButtonMoveItem eventImplButtonMoveItem,
                                     @Qualifier("list_from") List list0,
                                     @Qualifier("list_to") List list1) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getButtonRightMoveAll());
        process.setList_from(list0);
        process.setList_to(list1);
        button.setData(process);
        button.addMouseListener(eventImplButtonMoveItem);
        button.setBounds(223, 182, 50, 30);
        return button;
    }

    @Bean(name = "button_leftMoveAll")
    public Button buttonLeftMoveAll(MoveItemProcess process,
                                    EventImplButtonMoveItem eventImplButtonMoveItem,
                                    @Qualifier("list_from") List list0,
                                    @Qualifier("list_to") List list1) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getButtonLeftMoveAll());
        process.setList_from(list1);
        process.setList_to(list0);
        button.setData(process);
        button.addMouseListener(eventImplButtonMoveItem);
        button.setBounds(223, 228, 50, 30);
        return button;
    }

    @Bean(name = "button_leftMove")
    public Button buttonLeftMove(MoveItemProcess process,
                                 EventImplButtonMoveItem eventImplButtonMoveItem,
                                 @Qualifier("list_from") List list0,
                                 @Qualifier("list_to") List list1) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getButtonLeftMove());
        process.setList_from(list1);
        process.setList_to(list0);
        button.setData(process);
        button.addMouseListener(eventImplButtonMoveItem);
        button.setBounds(223, 274, 50, 30);
        return button;
    }

    @Bean(name = "button_send")
    public Button buttonSend(EventImplButtonSend eventImplButtonSend) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getSendEmailButton());
        button.addMouseListener(eventImplButtonSend);
        button.setBounds(320, 10, 80, 80);
        return button;
    }

    @Bean(name = "combo_titles")
    public Combo comboTitles(EventImplComboTitlesSelection eventImplComboTitlesSelection) {
        Combo combo = new Combo(parent, SWT.READ_ONLY);
        combo.setBounds(100, 50, 200, 20);
        combo.addSelectionListener(eventImplComboTitlesSelection);
        return combo;
    }

    @Bean(name = "label_reciever")
    public Label labelReciever() {
        Label label = new Label(parent, SWT.NONE);
        label.setText(uiProps.getLabelChooseReciverColumn());
        label.setBounds(13, 50, 80, 23);
        return label;
    }

    @Bean(name = "label_contents")
    public Label labelContents() {
        Label label = new Label(parent, SWT.NONE);
        label.setText(uiProps.getLabelChooseContentColumn());
        label.setBounds(13, 93, 115, 23);
        return label;
    }

    @Bean(name = "list_from")
    public List list_from(MoveItemProcess moveItemProcess) {
        List list = new List(parent, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
        list.setBounds(13, 120, 200, 200);
        return list;
    }

    @Bean(name = "list_to")
    public List list_to(MoveItemProcess moveItemProcess) {
        final List list = new List(parent, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);

        final int y = 200;
        final int y_cordinate = 120;

        DragSource ds = new DragSource(list, DND.DROP_MOVE);
        ds.setTransfer(new Transfer[] { TextTransfer.getInstance() });
        ds.addDragListener(new DragSourceAdapter() {
            @Override
            public void dragSetData(DragSourceEvent event) {
                Integer sel = list.getSelectionIndex();
                event.data = String.valueOf(sel);
            }
        });

        DropTarget dt = new DropTarget(list, DND.DROP_MOVE);
        dt.setTransfer(new Transfer[] { TextTransfer.getInstance() });
        dt.addDropListener(new DropTargetAdapter() {
            int itemHeight = list.getItemHeight();

            @Override
            public void dropAccept(DropTargetEvent event) {
            }

            @Override
            public void dragOver(DropTargetEvent event) {
            }

            @Override
            public void drop(DropTargetEvent event) {
                int drapIndex = Integer.valueOf(event.data.toString());
                System.out.println(event);
                if (drapIndex < 0)
                    return;
                int dropY = event.y;
                int diff = dropY - list.getShell().getLocation().y - y_cordinate;
                System.out.println(list.getShell().getLocation());
                int dropIndex = diff / itemHeight;
                if (dropIndex > list.getItemCount())
                    dropIndex = list.getItemCount() - 1;
                String dragItem = list.getItem(drapIndex);
                String dropItem = list.getItem(dropIndex);
                list.setItem(drapIndex, dropItem);
                list.setItem(dropIndex, dragItem);
            }
        });

        list.setBounds(283, y_cordinate, 200, y);
        return list;
    }

    @Bean(name = "button_up")
    public Button upButton(EventImplButtonUpDown eventImplButtonUpDown) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getButtonMoveUp());
        button.setData(-1);
        button.addMouseListener(eventImplButtonUpDown);
        button.setBounds(493, 230, 60, 40);
        return button;
    }

    @Bean(name = "button_down")
    public Button downButton(EventImplButtonUpDown eventImplButtonUpDown) {
        Button button = new Button(parent, SWT.NONE);
        button.setText(uiProps.getButtonMoveDown());
        button.setData(1);
        button.addMouseListener(eventImplButtonUpDown);
        button.setBounds(493, 280, 60, 40);
        return button;
    }

    @Bean
    public FileDialog fileDialogChooseFile() {
        FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.NONE);
        fileDialog.setText(uiProps.getFileDialogText());
        fileDialog.setFilterExtensions(uiProps.getFileDialogFilterExtensions().split(","));
        fileDialog.setFilterNames(uiProps.getFileDialogFilterNames().split(","));
        return fileDialog;
    }

    @Bean
    public ProgressMonitorDialog progeMonitorDialog() {
        ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(parent);
        return progressMonitorDialog;
    }
}
