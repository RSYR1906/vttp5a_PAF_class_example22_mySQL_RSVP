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

    public Optional<Person> getRSVPByName(String name) {
        return rsvpRepo.getRSVPByName(name);
    }

    public boolean addOrUpdateRsvp(String name, String email, String phone, Date confirmDate, String comments) {
        email = email.trim(); // Ensure email consistency

        if (rsvpRepo.rsvpExists(email)) {
            return rsvpRepo.updateRsvp(name, email, phone, confirmDate, comments);
        } else {
            return rsvpRepo.insertNewRsvp(name, email, phone, confirmDate, comments);
        }
    }

    public boolean updateRsvp(String name, String email, String phone, Date confirmDate, String comments) {
        return rsvpRepo.updateRsvp(name, email, phone, confirmDate, comments);
    }

    public Integer getTotalRsvp() {
        return rsvpRepo.getTotalRsvp();
    }

}