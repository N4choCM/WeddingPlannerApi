package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lId;

    private String sName;
    private String sLastName;
    private String sEmail;
    private String sUserName;
    private String sPassword;




    public User () {

    }

    public User(Long lId, String sName, String sLastName, String sEmail, String sUserName, String sPassword) {
        this.lId = lId;
        this.sName = sName;
        this.sLastName = sLastName;
        this.sEmail = sEmail;
        this.sUserName = sUserName;
        this.sPassword = sPassword;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "lId=" + lId +
                ", sName='" + sName + '\'' +
                ", sLastName='" + sLastName + '\'' +
                ", sEmail='" + sEmail + '\'' +
                ", sUserName='" + sUserName + '\'' +
                ", sPassword='" + sPassword + '\'' +
                '}';
    }
}
