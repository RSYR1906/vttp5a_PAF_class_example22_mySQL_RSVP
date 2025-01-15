package vttp.batch5.PAF.Workshop_22.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.PAF.Workshop_22.model.Person;
import vttp.batch5.PAF.Workshop_22.repo.RsvpRepo;

@Service
public class RsvpService {

    @Autowired
    private RsvpRepo rsvpRepo;

    public List<Person> getAllRSVP() {
        return rsvpRepo.getAllRSVP();
    }

    public Optional<Person> getRSVPByid(String id) {
        return rsvpRepo.getRSVPById(id);
    }

    public boolean addOrUpdateRsvp(String id, String email, String phone, Date confirmDate, String comments) {
        if (rsvpRepo.rsvpExists(email)) {
            // Update existing RSVP
            return rsvpRepo.updateRsvp(id, phone, confirmDate, comments, email);
        } else {
            // Insert new RSVP
            return rsvpRepo.insertNewRsvp(id, email, phone, confirmDate, comments);
        }
    }

    public boolean updateRsvp(String id, String phone, Date confirmDate, String comments, String email) {
        return rsvpRepo.updateRsvp(id, phone, confirmDate, comments, email);
    }

    public Integer getTotalRsvp() {
        return rsvpRepo.getTotalRsvp();
    }

}