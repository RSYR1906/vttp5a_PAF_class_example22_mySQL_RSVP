package vttp.batch5.PAF.Workshop_22.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.PAF.Workshop_22.model.Person;

@Repository
public class RsvpRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Person> getAllRSVP() {

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ALL_RSVP);
        List<Person> RSVPList = new ArrayList<>();

        while (rs.next()) {
            Person p = new Person();
            p.setName(rs.getString("name"));
            p.setEmail(rs.getString("email"));
            p.setPhone(rs.getString("phone"));
            p.setComments(rs.getString("comments"));
            p.setConfirmDate(rs.getDate("confirmDate"));
            RSVPList.add(p);
        }
        return RSVPList;
    }

    public Optional<Person> getRSVPByName(String name) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_RSVP_BY_NAME, String.format("%%%s%%", name));

        if (rs.next()) {
            Person person = Person.toPerson(rs);
            return Optional.of(person);
        }
        return Optional.empty();

    }

    public boolean rsvpExists(String email) {
        Integer count = template.queryForObject(Queries.SQL_CHECK_RSVP_BY_EMAIL, Integer.class, email);
        return count != null && count > 0;
    }

    public boolean insertNewRsvp(String name, String email, String phone, Date confirmDate, String comments) {
        email = email.trim(); // Ensure email consistency
        int added = template.update(Queries.SQL_INSERT_NEW_RSVP, name, email, phone, confirmDate, comments);
        return added > 0;
    }

    public boolean updateRsvp(String name, String email, String phone, Date confirmDate, String comments) {
        int rowsUpdated = template.update(Queries.SQL_UPDATE_RSVP, name, email, phone, confirmDate, comments, email);
        return rowsUpdated > 0;
    }

    public Integer getTotalRsvp() {
        return template.queryForObject(Queries.SQL_COUNT_RSVP, Integer.class);
    }

}
