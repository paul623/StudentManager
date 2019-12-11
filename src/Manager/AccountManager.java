package Manager;

import Model.Account;
import Util.DBHelper;
import Util.DateUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    public static Account getAccountByName(String name){
        Account account=null;
        DBHelper dbHelper=new DBHelper("select * from account where UserAccount="+"'"+name+"'");
        try {
            ResultSet resultSet=dbHelper.parseSQL();
            while (resultSet.next()){
                account=new Account();
                account.setID(resultSet.getInt(1));
                account.setUserName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setLevel(resultSet.getInt(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return account;
    }
    public static List<Account> getAccounts(){
        List<Account> list=new ArrayList<>();
        DBHelper dbHelper=new DBHelper("select * from account");
        try {
            ResultSet resultSet=dbHelper.parseSQL();
            while (resultSet.next()){
                Account account=new Account();
                account.setID(resultSet.getInt(1));
                account.setUserName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setLevel(resultSet.getInt(5));
                list.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return list;
    }
    public static int addAccount(String account,String password){
        DBHelper dbHelper=new DBHelper("insert into account(UserAccount,UserPassword,Date,level) values(?,?,?,?)");
        PreparedStatement p=dbHelper.getPreparedStatement();
        int i=0;
        try {
            p.setString(1,account);
            p.setString(2,password);
            p.setString(3,DateUtils.getCurTime());
            p.setInt(4,1);
            i=dbHelper.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return i;
    }
}
