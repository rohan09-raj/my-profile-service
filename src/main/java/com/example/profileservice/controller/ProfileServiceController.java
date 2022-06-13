package com.example.profileservice.controller;

import com.example.profileservice.model.Profile;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class ProfileServiceController {
    @Autowired
    private Environment env;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        try {
            final String status = "status: UP";
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            final String status = "status: DOWN";
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(@RequestHeader("authorization") String token) {
        try {
            if (token.isEmpty()) {
                HashMap<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Unauthorized Access, Hash Not Found");
                return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
            }

            String[] field = token.split(" ");
            String hash = field[1];
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(!encoder.matches(env.getProperty("app.CHAINCODE"), hash)) {
                HashMap<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Invalid Hash");
                return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
            }

            Profile profile = new Profile(
                    env.getProperty("app.FIRST_NAME"),
                    env.getProperty("app.LAST_NAME"),
                    env.getProperty("app.EMAIL"),
                    env.getProperty("app.PHONE_NUMBER"),
                    Integer.parseInt(Objects.requireNonNull(env.getProperty("app.YOE"))),
                    env.getProperty("app.COMPANY_NAME"),
                    env.getProperty("app.POSITION"),
                    env.getProperty("app.GITHUB_USERNAME"),
                    env.getProperty("app.LINKEDIN_ID"),
                    env.getProperty("app.TWITTER_USERNAME"),
                    env.getProperty("app.INSTAGRAM_USERNAME"),
                    env.getProperty("app.PORTFOLIO_WEBSITE")
            );
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Error while getting profile data");
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/verification")
    public ResponseEntity<HashMap<String, String>> verification(@RequestBody String salt) {
        if(salt.isEmpty()) {
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("hash", "Salt not found");
            return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
        }
        try {
            final String chainCode = env.getProperty("app.CHAINCODE");
            JSONObject jsonObject = new JSONObject(salt);
            HashMap<String, String> hashMap = new HashMap<>();
            if (chainCode != null) {
                hashMap.put("hash", BCrypt.hashpw(chainCode, jsonObject.getString("salt")));
            }
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Error while encryption");
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
