package vn.techmasterr.jobhunt.model;


import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

public class Employer {
    private  String id;
    private  String name;
    private String logo;
    private  String website;
    private  String email;
    private Set<Job> listJob;

    public Employer(String id, String name, String logo, String website, String email, Set<Job> listJob) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.website = website;
        this.email = email;
        this.listJob = listJob;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Employer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Job> getListJob() {
        return listJob;
    }

    public void setListJob(Set<Job> listJob) {
        this.listJob = listJob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
