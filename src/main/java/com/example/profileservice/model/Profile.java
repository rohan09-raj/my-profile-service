package com.example.profileservice.model;

public class Profile {
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String phone;
    private final int yoe;
    private final String company;
    private final String designation;
    private final String github_id;
    private final String linkedin_id;
    private final String twitter_id;
    private final String instagram_id;
    private final String website;

    public Profile(String first_name, String last_name, String email, String phone, int yoe, String company, String designation, String github_id, String linkedin_id, String twitter_id, String instagram_id, String website) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.yoe = yoe;
        this.company = company;
        this.designation = designation;
        this.github_id = github_id;
        this.linkedin_id = linkedin_id;
        this.twitter_id = twitter_id;
        this.instagram_id = instagram_id;
        this.website = website;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getYoe() {
        return yoe;
    }

    public String getCompany() {
        return company;
    }

    public String getDesignation() {
        return designation;
    }

    public String getGithub_id() {
        return github_id;
    }

    public String getLinkedin_id() {
        return linkedin_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    public String getInstagram_id() {
        return instagram_id;
    }

    public String getWebsite() {
        return website;
    }
}
