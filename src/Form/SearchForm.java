package Form;

import Model.GradesBean;
import Presenter.GradesBeanPresenter;
import Util.ShowUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchForm extends JFrame implements ActionListener {
    JTextField jTextField;
    JTable jTable;
    int level;
    List<GradesBean> gradesBeanList;
    public SearchForm(int level){
        this.level=level;
        setTitle("成绩查询界面");
        Container container=getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(getSearchBar());
        container.add(initTable());
        gradesBeanList= GradesBeanPresenter.getGrads();
        pack();
        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);
        if(level!=0){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    public JPanel getSearchBar(){
        JPanel jPanel=new JPanel(new GridLayout(1,3,10,10));
        jTextField=new JTextField(10);
        JPanel jPanel_input=new JPanel(new FlowLayout());
        jPanel_input.add(new JLabel("学生姓名:"));
        jPanel_input.add(jTextField);
        jPanel.add(jPanel_input);
        JButton jButton_search=new JButton("搜索");
        JButton jButton_all=new JButton("查看全部");
        jPanel.add(jButton_search);
        jPanel.add(jButton_all);
        jButton_all.addActionListener(this);
        jButton_search.addActionListener(this);
        return jPanel;
    }
    public JScrollPane initTable(){
        jTable = new JTable();
        jTable.setEnabled(false);
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addColumn("学生姓名");
        tableModel.addColumn("成绩");
        jTable.setRowHeight(50);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setViewportView(jTable);
        return jScrollPane;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "搜索":
                updateTableByName();
                break;
            case "查看全部":
                updateTable();
                break;
        }
    }

    public void updateTableByName(){
        if(jTextField.getText().equals("")){
            ShowUtils.errorMessage("请输入完整！");
            return;
        }
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        removeAllDataInTable();
        boolean flag=false;
        for(GradesBean o:gradesBeanList){
            if (o.getUserAccount().equals(jTextField.getText())){
                Object []data={o.getUserAccount(),o.getGrade()};
                tableModel.addRow(data);
                flag=true;
            }
        }
        if(!flag){
            ShowUtils.warningMessage("未找到结果");
        }
    }
    public void updateTable(){
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        removeAllDataInTable();
        for(GradesBean o:gradesBeanList){
            Object []data={o.getUserAccount(),o.getGrade()};
            tableModel.addRow(data);
        }
        if(gradesBeanList.size()==0){
            ShowUtils.warningMessage("未找到数据！");
        }
    }
    public void removeAllDataInTable(){
        ((DefaultTableModel) jTable.getModel()).getDataVector().clear();//清除表格数据
        ((DefaultTableModel) jTable.getModel()).fireTableDataChanged();//通知模型更新

        jTable.updateUI();//刷新表格 
    }
}
