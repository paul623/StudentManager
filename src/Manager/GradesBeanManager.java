package Manager;

import Model.Account;
import Model.GradesBean;
import Util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradesBeanManager {
    public static List<GradesBean> needToUpdateDataList(){
        List<GradesBean> list=new ArrayList<>();
        DBHelper dbHelper=new DBHelper("select * from grades where grade IS NULL");
        try {
            ResultSet resultSet=dbHelper.parseSQL();
            while (resultSet.next()){
                GradesBean gradesBean=new GradesBean(resultSet.getString(1),resultSet.getDouble(2));
                list.add(gradesBean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return list;
    }

    public static List<GradesBean> getGradesBeanList(){
        List<GradesBean> list=new ArrayList<>();
        DBHelper dbHelper=new DBHelper("select * from grades");
        try {
            ResultSet resultSet=dbHelper.parseSQL();
            while (resultSet.next()){
                GradesBean gradesBean=new GradesBean(resultSet.getString(1),resultSet.getDouble(2));
                list.add(gradesBean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return list;
    }
    public static int updateGrades(GradesBean gradesBean){

        DBHelper dbHelper=new DBHelper("update grades set grade=? where UserAccount=?");
        int i=0;
        PreparedStatement preparedStatement=dbHelper.getPreparedStatement();
        try {
            preparedStatement.setDouble(1,gradesBean.getGrade());
            preparedStatement.setString(2,gradesBean.getUserAccount());
            i=dbHelper.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return i;

    }

    public static GradesBean getByName(String name){
        GradesBean gradesBean=null;
        DBHelper dbHelper=new DBHelper("select * from grades where UserAccount="+"'"+name+"'");
        try {
            ResultSet resultSet=dbHelper.parseSQL();
            while (resultSet.next()){
                gradesBean=new GradesBean(resultSet.getString(1),resultSet.getDouble(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return gradesBean;
    }
    public static int deleteGrades(String name){

        DBHelper dbHelper=new DBHelper("delete from grades where UserAccount=?");
        int i=0;
        PreparedStatement preparedStatement=dbHelper.getPreparedStatement();
        try {
            preparedStatement.setString(1,name);
            i=dbHelper.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return i;

    }
    public static int addGrades(String name){
        DBHelper dbHelper=new DBHelper("insert into grades(UserAccount) Values(?)");
        int i=0;
        PreparedStatement preparedStatement=dbHelper.getPreparedStatement();
        try {
            preparedStatement.setString(1,name);
            i=dbHelper.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.closeConnection();
        }
        return i;

    }

}
