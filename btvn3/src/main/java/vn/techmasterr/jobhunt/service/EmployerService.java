package vn.techmasterr.jobhunt.service;


import vn.techmasterr.jobhunt.model.Employer;


import java.util.concurrent.ConcurrentMap;

public interface EmployerService {
    public ConcurrentMap<String,Employer> getListEmployer();
    public Employer getById(String id);
    public  Employer addEmployer(Employer employer);
    public  Employer updateEmployer(Employer employer, String id);
    public Employer deleteEmployer(String id);
}
