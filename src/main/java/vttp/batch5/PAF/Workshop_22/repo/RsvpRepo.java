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
            p.setId(rs.getString("rsvp_id"));
            p.setEmail(rs.getString("email"));
            p.setPhone(rs.getString("phone"));
            p.setComments(rs.getString("comments"));
            p.setConfirmDate(rs.getDate("confirm_date"));
            RSVPList.add(p);
        }
        return RSVPList;
    }

    public Optional<Person> getRSVPById(String id) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_RSVP_BY_ID, String.format("%%%s%%", id));

        if (rs.next()) {
            Person person = Person.toPerson(rs);
            return Optional.of(person);
        }
        return Optional.empty();

    }

    public boolean rsvpExists(String email) {
        Integer count = template.queryForObject(Queries.SQL_COUNT_RSVP_BY_EMAIL, Integer.class, email);
        return count != null && count > 0;
    }

    public boolean insertNewRsvp(String id, String email, String phone, Date confirmDate, String comments) {
        // if (!isValidDate(confirmDate)) {
        // throw new IllegalArgumentException("Invalid date format. Expected format:
        // YYYY-MM-DD");
        // }
        int added = template.update(Queries.SQL_INSERT_NEW_RSVP, id, email, phone, confirmDate, comments);
        return added > 0;
    }

    // private boolean isValidDate(String date) {
    // try {
    // LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    // return true;
    // } catch (DateTimeParseException e) {
    // return false;
    // }
    // }

    public boolean updateRsvp(String id, String phone, Date confirmDate, String comments, String email) {
        email = email.trim(); // Trim spaces
        int rowsUpdated = template.update(Queries.SQL_UPDATE_RSVP, id, phone, confirmDate, comments, email);
        return rowsUpdated > 0;
    }

    public Integer getTotalRsvp() {
        return template.queryForObject(Queries.SQL_COUNT_RSVP, Integer.class);
    }

}
