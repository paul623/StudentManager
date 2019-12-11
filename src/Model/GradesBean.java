package Model;

import java.util.Objects;

public class GradesBean {
   String userAccount;
   double grade;

    public GradesBean(String userAccount, double grade) {
        this.userAccount = userAccount;
        this.grade = grade;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradesBean that = (GradesBean) o;
        return Double.compare(that.grade, grade) == 0 &&
                Objects.equals(userAccount, that.userAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, grade);
    }

    @Override
    public String toString() {
        return "GradesBean{" +
                "userAccount='" + userAccount + '\'' +
                ", grade=" + grade +
                '}';
    }
}
