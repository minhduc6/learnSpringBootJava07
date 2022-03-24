package vn.techmaster.restapicrud.model;

public class Job {
    private  String id;
    private  String title;
    private  String description;
    private  Location location;
    private  int min_salary;
    private  int max_salary;
    private  String email_to;

    public Job() {
    }

    public Job(String id, String title, String description, Location location, int min_salary, int max_salary, String email_to) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
        this.email_to = email_to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(int min_salary) {
        this.min_salary = min_salary;
    }

    public int getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(int max_salary) {
        this.max_salary = max_salary;
    }

    public String getEmail_to() {
        return email_to;
    }

    public void setEmail_to(String email_to) {
        this.email_to = email_to;
    }

    public boolean findJobByKeyword(String keyword){
        if(title.toLowerCase().contains(keyword.toLowerCase())
                ||  description.toLowerCase().contains(keyword.toLowerCase())){
              return  true;
        }
        return  false;
    }
}
