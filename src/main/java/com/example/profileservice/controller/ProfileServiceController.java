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

import java.util.HashMap;

@RestController
public class ProfileServiceController {
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        final String status = "status: UP";
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping(value = "/verification")
    public ResponseEntity<HashMap<String, String>> verification(@RequestBody String salt) throws JSONException {
        final String chainCode = "y9U0NRTKDrLI9yBqqMER";
        JSONObject jsonObject = new JSONObject(salt);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", BCrypt.hashpw(chainCode, jsonObject.getString("salt")));
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

//    "<FIRST_NAME>",
//    "<LAST_NAME>",
//    "<YOUR_EMAIL>",
//    "<YOUR_PHONE_NUMBER>",
//    0,
//    "<COMPANY_NAME>",
//    "<POSITION>",
//    "<GITHUB_USERNAME>",
//    "<LINKED_ID>",
//    "<TWITTER_USERNAME",
//    "<INSTAGRAM_USERNAME",
//    "<YOUR_PORTFOLIO_WEBSITE>"

    @GetMapping("/profile")
    public ResponseEntity<Object> profile() {
        Profile profile = new Profile(
                "Rohan Raj",
                "Gupta",
                "rajrohan1914@gmail.com",
                "8707745915",
                0,
                "nil",
                "nil",
                "rohan09-raj",
                "rohan-raj-gupta-1a3465190",
                "RohanRajGupta6",
                "_.rohan09._",
                "nil"
        );
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
