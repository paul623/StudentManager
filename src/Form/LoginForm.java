package Form;
import Model.Account;
import Presenter.AccountPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginForm extends JFrame implements ActionListener {
    JButton button_login,button_register;
    JTextField jt_account;
    JPasswordField jt_password;


    public LoginForm(){
        setTitle("用户登录");
        setBounds(200,300,300,200);
        Container container=getContentPane();
        container.setLayout(new GridLayout(3,1));
        JPanel jPanel1=new JPanel(new FlowLayout());
        jPanel1.add(new JLabel("用户名"));
        jt_account=new JTextField(10);
        jPanel1.add(jt_account);
        container.add(jPanel1);
        JPanel jPanel2=new JPanel(new FlowLayout());
        jPanel2.add(new JLabel("密码    "));
        jt_password=new JPasswordField(10);
        jPanel2.add(jt_password);
        container.add(jPanel2);

        JPanel jPanel3=new JPanel(new FlowLayout());
        button_login=new JButton("登录");
        button_login.addActionListener(this);
        button_register=new JButton("注册");
        button_register.addActionListener(this);
        jPanel3.add(button_login);
        jPanel3.add(button_register);
        container.add(jPanel3);


        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {

        new LoginForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "登录":
                if(AccountPresenter.login(jt_account.getText(),jt_password.getText())){
                    dispose();
                    if(AccountPresenter.isSystemManager(jt_account.getText())){
                        new ManagerForm();
                    }else {
                        new SearchForm(1);
                    }
                }
                break;
            case "注册":
                AccountPresenter.register(jt_account.getText(),jt_password.getText());
                break;
        }
    }
}
