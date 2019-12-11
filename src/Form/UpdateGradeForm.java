package Form;


import Model.GradesBean;
import Presenter.GradesBeanPresenter;
import Util.ShowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateGradeForm extends JFrame implements ActionListener {
    JTextField jTextField_name,jTextField_grade;
    JLabel jLabel;
    GradesBean gradesBean;
    public UpdateGradeForm(){
        setTitle("更新成绩");
        Container container=getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.X_AXIS));
        container.add(getInputPanel());
        pack();
        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);

    }
    public  JPanel getInputPanel(){
        JPanel jPanel=new JPanel(new GridLayout(4,1,10,10));
        JPanel jPanel_search=new JPanel(new FlowLayout());
        jPanel_search.add(new JLabel("查询的姓名:"));
        jTextField_name=new JTextField(10);
        jPanel_search.add(jTextField_name);
        JButton jButton_search=new JButton("查找");
        jButton_search.addActionListener(this);
        jPanel_search.add(jButton_search);
        JPanel jPanel_dataview=new JPanel(new GridLayout(2,2));
        jPanel_dataview.add(new JLabel("姓名"));
        jPanel_dataview.add(new JLabel("成绩"));
        jLabel=new JLabel("待检索");
        jTextField_grade=new JTextField(10);
        jPanel_dataview.add(jLabel);
        jPanel_dataview.add(jTextField_grade);
        JPanel jPanel_button=new JPanel(new GridLayout(2,1,10,10));
        String []ss={"提交"};
        for(String i:ss){
            JButton jButton=new JButton(i);
            jButton.addActionListener(this);
            jPanel_button.add(jButton);
        }
        jPanel.add(jPanel_search);
        jPanel.add(jPanel_dataview);
        jPanel.add(jPanel_button);
        return jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "提交":
                commit();
                break;
            case "查找":
                getSearchResult();
                break;
        }
    }
    private void getSearchResult(){
        String userName=jTextField_name.getText();
        if(userName.equals("")){
            ShowUtils.errorMessage("查询姓名不能为空");
        }
        gradesBean= GradesBeanPresenter.getGradesBeanByName(userName);
        if(gradesBean!=null){
            jLabel.setText(gradesBean.getUserAccount());
            jTextField_grade.setText(gradesBean.getGrade()+"");
        }
    }
    private void commit(){
        String userGrade=jTextField_grade.getText();
        if(userGrade.equals("")){
            ShowUtils.errorMessage("成绩不能为空");
            return;
        }
        gradesBean.setGrade(Double.parseDouble(userGrade));
        GradesBeanPresenter.update(gradesBean);
    }
}
