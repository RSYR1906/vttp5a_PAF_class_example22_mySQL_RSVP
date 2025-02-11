package vttp.batch5.PAF.Workshop_22.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vttp.batch5.PAF.Workshop_22.model.Person;
import vttp.batch5.PAF.Workshop_22.service.RsvpService;

@RestController
@RequestMapping("/api")
public class RsvpRestController {

    @Autowired
    private RsvpService rsvpService;

    private static final Logger log = LoggerFactory.getLogger(RsvpRestController.class);

    @GetMapping("/rsvps")
    @ResponseBody
    public ResponseEntity<List<Person>> getRsvpList() {
        List<Person> rsvpList = rsvpService.getAllRSVP();
        return ResponseEntity.ok().body(rsvpList);
    }

    @GetMapping("/rsvp")
    public ResponseEntity<?> getRsvpByName(@RequestParam(required = true) String name) {

        Optional<Person> rsvp = rsvpService.getRSVPByName(name);

        if (rsvp.isPresent()) {
            return ResponseEntity.ok().body(rsvp);
        }

        return ResponseEntity.status(404).body("Error. No such rsvp.");
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<?> getTotalRsvp() {

        Integer totalRsvp = rsvpService.getTotalRsvp();

        if (totalRsvp > 0) {
            return ResponseEntity.status(201).body(totalRsvp);
        }

        return ResponseEntity.status(404).body("Error.Rsvp is empty");

    }

    @PostMapping("/rsvp")
    public ResponseEntity<?> addNewRsvp(@RequestBody Person person) {

        log.info("Received POST request to add or update RSVP for email: {}", person.getEmail());
        log.info("Parameters - name: {}, phone: {}, confirmDate: {}, comments: {}",
                person.getName(), person.getEmail(), person.getPhone(), person.getConfirmDate(), person.getComments());

        boolean isAdded = rsvpService.addOrUpdateRsvp(
                person.getName(),
                person.getEmail(),
                person.getPhone(),
                person.getConfirmDate(),
                person.getComments());

        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("RSVP added or updated.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing RSVP.");
    }

    @PutMapping("/rsvp/{email}")
    public ResponseEntity<?> updateRsvp(@PathVariable String email,
            @RequestBody Person person) {

        log.info("Received PUT request to update RSVP for email: {}", email);
        log.info("Parameters - name: {}, phone: {}, confirmDate: {}, comments: {}",
                person.getName(), person.getEmail(), person.getPhone(), person.getConfirmDate(), person.getComments());

        boolean isUpdated = rsvpService.updateRsvp(
                person.getName(),
                email,
                person.getPhone(),
                person.getConfirmDate(),
                person.getComments());

        if (isUpdated) {
            log.info("RSVP updated successfully for email: {}", email);
            return ResponseEntity.status(HttpStatus.CREATED).body("RSVP updated");
        } else {
            log.warn("No RSVP found for email: {}", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error. No RSVP found with email: " + email);
        }
    }

}
