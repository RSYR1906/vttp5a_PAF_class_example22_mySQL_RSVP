package vttp.batch5.PAF.Workshop_22.service;

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

    public Optional<Person> getRSVPByName(String name) {
        return rsvpRepo.getRSVPByName(name);
    }

    public boolean addOrUpdateRsvp(String name, String email, String phone, String confirmDate, String comments) {
        if (rsvpRepo.rsvpExists(email)) {
            // Update existing RSVP
            return rsvpRepo.updateRsvp(name, phone, confirmDate, comments, email);
        } else {
            // Insert new RSVP
            return rsvpRepo.insertNewRsvp(name, email, phone, confirmDate, comments);
        }
    }

    public boolean updateRsvp(String name, String phone, String confirmDate, String comments, String email) {
        return rsvpRepo.updateRsvp(name, phone, confirmDate, comments, email);
    }

    public Integer getTotalRsvp() {
        return rsvpRepo.getTotalRsvp();
    }

}