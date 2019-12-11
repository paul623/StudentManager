package Form;

import Presenter.GradesBeanPresenter;
import Util.ShowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteForm extends JFrame implements ActionListener {
    JTextField jTextField;
    public DeleteForm(){
        setTitle("删除成绩");
        Container container=getContentPane();
        container.setLayout(new FlowLayout());
        container.add(getDeletePanel());
        setSize(300,150);
        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);
    }
    public JPanel getDeletePanel(){
        JPanel jPanel=new JPanel(new GridLayout(2,1));
        JPanel jPanel_view=new JPanel(new FlowLayout());
        jPanel_view.add(new JLabel("姓名"));
        jTextField=new JTextField(10);
        jPanel_view.add(jTextField);
        jPanel.add(jPanel_view);
        JButton jButton=new JButton("删除");
        jButton.addActionListener(this);
        jPanel.add(jButton);
        return jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("删除")){
            deleteData();
        }
    }
    private void deleteData(){
        String userName=jTextField.getText();
        if(userName.equals("")){
            ShowUtils.errorMessage("姓名不能为空！");
            return;
        }
        GradesBeanPresenter.deleteGradesByName(userName);
        jTextField.setText("");
    }
}
