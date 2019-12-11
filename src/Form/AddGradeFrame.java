package Form;

import Presenter.GradesBeanPresenter;
import Util.ShowUtils;
import com.sun.deploy.panel.JHighDPITable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGradeFrame extends JFrame implements ActionListener {
    JTextField jTextField;
    public AddGradeFrame(){
        setTitle("添加学生");
        Container container=getContentPane();
        container.setLayout(new FlowLayout());
        container.add(getAddPanel());
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
    public JPanel getAddPanel(){
        JPanel jPanel=new JPanel(new GridLayout(3,1));
        JPanel jPanel_name=new JPanel(new FlowLayout());
        jPanel_name.add(new JLabel("姓名"));
        jTextField=new JTextField(10);
        jPanel_name.add(jTextField);

        JButton jButton=new JButton("添加");
        JButton jButton_exit=new JButton("结束");
        jButton.addActionListener(this);
        jButton_exit.addActionListener(this);

        jPanel.add(jPanel_name);
        jPanel.add(jButton);
        jPanel.add(jButton_exit);
        return jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "添加":
                addName();
                break;
            case "结束":
                dispose();
                break;
        }
    }
    public void addName(){
        String name=jTextField.getText();
        if(name.equals("")){
            ShowUtils.errorMessage("姓名不能为空！");
            return;
        }
        GradesBeanPresenter.addGrades(name);
        jTextField.setText("");
    }

}
