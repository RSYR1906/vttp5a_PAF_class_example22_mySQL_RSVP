package vttp.batch5.PAF.Workshop_22.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.batch5.PAF.Workshop_22.model.Person;
import vttp.batch5.PAF.Workshop_22.service.RsvpService;

@Controller
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
    @ResponseBody
    public ResponseEntity<?> getRsvpByID(@RequestParam(required = true) String id) {

        Optional<Person> rsvp = rsvpService.getRSVPByid(id);

        if (rsvp.isPresent()) {
            return ResponseEntity.ok().body(rsvp);
        }

        return ResponseEntity.status(404).body("Error. No such rsvp.");
    }

    @GetMapping("/rsvps/count")
    @ResponseBody
    public ResponseEntity<?> getTotalRsvp() {

        Integer totalRsvp = rsvpService.getTotalRsvp();

        if (totalRsvp > 0) {
            return ResponseEntity.status(201).body(totalRsvp);
        }

        return ResponseEntity.status(404).body("Error.Rsvp is empty");

    }

    @PostMapping("/rsvp")
    @ResponseBody
    public ResponseEntity<?> addNewRsvp(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam Date confirmDate,
            @RequestParam String comments) {

        log.info("Received POST request to add or update RSVP for email: {}", email);
        log.info("Parameters - name: {}, phone: {}, confirmDate: {}, comments: {}", name, phone, confirmDate, comments);

        boolean isAdded = rsvpService.addOrUpdateRsvp(name, email, phone, confirmDate, comments);
        if (isAdded) {
            return ResponseEntity.status(201).body("Rsvp added or updated.");
        }

        return ResponseEntity.status(500).body("Error processing RSVP.");
    }

    @PutMapping("/rsvp/{email}")
    @ResponseBody
    public ResponseEntity<?> updateRsvp(@PathVariable String email,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam Date confirmDate,
            @RequestParam String comments) {

        log.info("Received PUT request to update RSVP for email: {}", email);
        log.info("Parameters - name: {}, phone: {}, confirmDate: {}, comments: {}", name, phone, confirmDate, comments);

        boolean isUpdated = rsvpService.updateRsvp(name, phone, confirmDate, comments, email);

        if (isUpdated) {
            log.info("RSVP updated successfully for email: {}", email);
            return ResponseEntity.status(201).body("Rsvp updated");
        } else {
            log.warn("No RSVP found for email: {}", email);
            return ResponseEntity.status(404).body("Error. No RSVP found with email: " + email);
        }
    }

}
