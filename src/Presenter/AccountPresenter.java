package Presenter;

import Model.Account;
import Manager.AccountManager;
import Util.ShowUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.rmi.CORBA.Util;
import java.util.List;

public class AccountPresenter {
    public static boolean login(String userName,String password){
        if(userName.equals("")||password.equals("")){
            ShowUtils.warningMessage("用户名或密码不能为空");
            return false;
        }
        List<Account> accountList= AccountManager.getAccounts();
        for(Account account:accountList){
            if(account.getUserName().equals(userName)){
                if(account.getPassword().equals(password)){
                    ShowUtils.message("登录成功");
                    return true;
                }else {
                    ShowUtils.errorMessage("用户名或密码错误");
                    return false;
                }
            }
        }
        ShowUtils.errorMessage("用户名不存在");
        return false;
    }
    public static boolean isSystemManager(String userName){
        Account account=AccountManager.getAccountByName(userName);
        if(account==null){
            ShowUtils.errorMessage("系统错误！");
            return false;
        }
        System.out.println("登录名:"+account.getUserName()+"权限："+account.getLevel());
        if(account.getLevel()==0){
            return true;
        }else {
            return false;
        }
    }

    public static boolean register(String userName,String password){
        if(userName.equals("")||password.equals("")){
            ShowUtils.warningMessage("用户名或密码不能为空");
            return false;
        }
        List<Account> accountList= AccountManager.getAccounts();
        for(Account i:accountList){
            if(i.getUserName().equals(userName)){
                ShowUtils.errorMessage("已存在该用户名，请重试！");
                return false;
            }
        }
        System.out.println(userName);
        int i=AccountManager.addAccount(userName,password);
        if(i==0){
            ShowUtils.errorMessage("注册失败，未知错误");
            return false;
        }else {
            ShowUtils.message("注册成功");
            return true;
        }
    }

}
