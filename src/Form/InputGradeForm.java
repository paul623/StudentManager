package Form;

import Model.GradesBean;
import Presenter.GradesBeanPresenter;
import Util.ShowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class InputGradeForm extends JFrame implements ActionListener {
    JTextField jTextField;
    JLabel jLabel;
    int loc=0;
    List<GradesBean> gradesBeans;
    public InputGradeForm(){
        setTitle("成绩录入");
        Container container=getContentPane();
        container.setLayout(new FlowLayout());
        gradesBeans= GradesBeanPresenter.getNullGrades();
        if(gradesBeans==null){
            ShowUtils.warningMessage("成绩均已录入");
            dispose();
        }
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
    public JPanel getInputPanel(){
        JPanel jPanel=new JPanel(new GridLayout(2,1,10,10));
        JPanel jPanel_dataview=new JPanel(new GridLayout(2,2));
        jPanel_dataview.add(new JLabel("姓名"));
        jPanel_dataview.add(new JLabel("成绩"));
        jLabel=new JLabel(gradesBeans.get(0).getUserAccount());
        jTextField=new JTextField(10);
        jPanel_dataview.add(jLabel);
        jPanel_dataview.add(jTextField);
        JPanel jPanel_button=new JPanel(new GridLayout(2,1,10,10));
        String []ss={"提交","结束录入"};
        for(String i:ss){
            JButton jButton=new JButton(i);
            jButton.addActionListener(this);
            jPanel_button.add(jButton);
        }
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
            case "结束录入":
                dispose();
                break;
        }
    }

    public void commit(){
        if(jTextField.getText().equals("")){
            ShowUtils.errorMessage("成绩不能为空！");
            return;
        }
        Double d=Double.parseDouble(jTextField.getText());
        gradesBeans.get(loc).setGrade(d);
        GradesBeanPresenter.update(gradesBeans.get(loc));
        if(loc+1<gradesBeans.size()){
            loc++;
            jTextField.setText("");
            jLabel.setText(gradesBeans.get(loc).getUserAccount());
        }else {
            ShowUtils.message("已经全部录入完毕！");
            dispose();
        }
    }
}
