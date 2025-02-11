package vttp.batch5.PAF.Workshop_22.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Person {

    private String name;
    private String email;
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date confirmDate;
    private String comments;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Person toPerson(SqlRowSet rs) {
        Person person = new Person();
        person.setName(rs.getString("name"));
        person.setEmail(rs.getString("email"));
        person.setPhone(rs.getString("phone"));
        person.setConfirmDate(rs.getDate("confirmDate"));
        person.setComments(rs.getString("comments"));
        return person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
