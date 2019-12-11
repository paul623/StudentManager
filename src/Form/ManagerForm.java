package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerForm extends JFrame implements ActionListener {
    public ManagerForm(){
        setTitle("后台管理界面");
        Container container=getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(addButtons());


        pack();
        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public JPanel addButtons(){
        String a[]={"添加学生","录入成绩","修改成绩","删除学生信息","查询学生信息","退出系统"};
        JPanel jPanel=new JPanel();
        for(String i:a){
            JButton jButton=new JButton(i);
            jButton.addActionListener(this);
            jPanel.add(jButton);
        }
        return jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "录入成绩":
                new InputGradeForm();
                break;
            case "修改成绩":
                new UpdateGradeForm();
                break;
            case "删除学生信息":
                new DeleteForm();
                break;
            case "查询学生信息":
                new SearchForm(0);
                break;
            case "退出系统":
                System.exit(0);
                break;
            case "添加学生":
                new AddGradeFrame();
                break;
        }
    }
}
