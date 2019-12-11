package Presenter;

import Manager.GradesBeanManager;
import Model.GradesBean;
import Util.ShowUtils;

import java.util.List;

public class GradesBeanPresenter {
    public static List<GradesBean> getGrads(){
        List<GradesBean> list=GradesBeanManager.getGradesBeanList();
        if(list.size()==0){
            ShowUtils.warningMessage("无数据！");
        }
        return list;
    }
    public static List<GradesBean> getNullGrades(){
        List<GradesBean> list=GradesBeanManager.needToUpdateDataList();
        return list;
    }
    public static void update(GradesBean gradesBean){
        int i=GradesBeanManager.updateGrades(gradesBean);
        if(i==0){
            ShowUtils.errorMessage("提交失败！");
        }else {
            ShowUtils.message("录入成功！");
        }
    }
    public static GradesBean getGradesBeanByName(String name){
        GradesBean gradesBean=GradesBeanManager.getByName(name);
        if(gradesBean==null){
            ShowUtils.errorMessage("未找到指定姓名的相关数据！");
        }
        return gradesBean;
    }
    public static void deleteGradesByName(String name){
        int i=GradesBeanManager.deleteGrades(name);
        if(i==0){
            ShowUtils.errorMessage("未找到学生");
        }else {
            ShowUtils.message("删除成功");
        }
    }
    public static void addGrades(String name){
        int i=GradesBeanManager.addGrades(name);
        if(i==0){
            ShowUtils.errorMessage("添加失败");
        }else {
            ShowUtils.message("添加成功");
        }
    }
}
