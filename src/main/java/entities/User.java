package entities;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lId;

    @Column (name = "Names")
    private String sName;
    @Column (name = "Last Names")
    private String sLastName;
    @Column (name = "Emails")
    private String sEmail;
    @Column (name = "User Names")
    private String sUserName;
    @Column (name = "Passwords")
    private String sPassword;

    public User() {
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
                '}';
    }
}
