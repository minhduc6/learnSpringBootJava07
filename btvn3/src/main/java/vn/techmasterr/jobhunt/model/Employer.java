package vn.techmasterr.jobhunt.model;


public class Employer {
    private  String id;
    private  String name;
    private  String website;
    private  String email;
    private  String address;

    public Employer(String id, String name, String website, String email, String address) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.email = email;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
