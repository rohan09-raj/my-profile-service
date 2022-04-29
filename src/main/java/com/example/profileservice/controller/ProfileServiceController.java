package com.example.profileservice.controller;

import com.example.profileservice.model.Profile;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileServiceController {
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        final String status = "status: UP";
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping(value = "/verification")
    public ResponseEntity<String> verification(@RequestBody String salt) throws JSONException {
        final String chainCode = "rxkE5W6PSndb52c8SueS";
        JSONObject jsonObject = new JSONObject(salt);
        return new ResponseEntity<>(BCrypt.hashpw(chainCode, jsonObject.getString("salt")), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile() {
        Profile profile = new Profile(
                "Rohan Raj",
                "Gupta",
                "rajrohan1914@gmail.com",
                "8707745915",
                0,
                "AndWeMet",
                "SDE Intern",
                "rohan09-raj",
                "rohanrajgupta12",
                "RohanRajGupta6",
                "_.rohan09._",
                "https://lift-simulation-by-rohan.netlify.app/src/index.html"
        );
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
