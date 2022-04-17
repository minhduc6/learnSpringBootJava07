package vn.techmasterr.jobhunt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.model.Job;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class EmployerServiceImp implements EmployerService{
    private  ConcurrentMap<String,Employer> listEmployer ;
    public  EmployerServiceImp(){
    }
    @Override
    public ConcurrentMap<String,Employer> getListEmployer() {
        return listEmployer;
    }

    @Override
    public Employer getById(String id) {
        return listEmployer.get(id);
    }

    @Override
    public Employer addEmployer(Employer employer) {
        String id  = UUID.randomUUID().toString();
        employer.setId(id);
        listEmployer.put(id,employer);
        return employer;
    }

    @Override
    public Employer updateEmployer(Employer employerRequest, String id) {
        Employer employerAfter = listEmployer.get(id);
        employerAfter.setName(employerRequest.getName());
        employerAfter.setWebsite(employerRequest.getWebsite());
        employerAfter.setEmail(employerRequest.getEmail());
        employerAfter.setLogo(employerRequest.getLogo());
        return employerAfter;
    }

    @Override
    public Employer deleteEmployer(String id) {
        return listEmployer.remove(id);
    }


    @PostConstruct
    public void addSomeData(){
        Set<Job> listVietel = new HashSet<>();
        listVietel.add(new Job("1","Web Development Viettel","HN","Phat trien Web"));
        listVietel.add(new Job("2","Mobi Development Viettel","HCM","Phat trien Mobile"));
        listVietel.add(new Job("3","Devops Viettel","DN","Phat trien Devops"));

        Set<Job> listFPT = new HashSet<>();
        listFPT.add(new Job("1","Web Development Fpt","HN","Phat trien Web"));
        listFPT.add(new Job("2","Mobi Development Fpt","HCM","Phat trien Mobile"));
        listFPT.add(new Job("3","Devops Fpt","DN","Phat trien Devops"));

        Set<Job> listTopcv = new HashSet<>();
        listTopcv.add(new Job("1","Web Development Topcv","HN","Phat trien Web"));
        listTopcv.add(new Job("2","Mobi Development Topcv","HCM","Phat trien Mobile"));
        listTopcv.add(new Job("3","Devops Topcv","DN","Phat trien Devops"));

        Set<Job> listCMC = new HashSet<>();
        listCMC.add(new Job("1","Web Development CMC","HN","Phat trien Web"));
        listCMC.add(new Job("2","Mobi Development CMC","HCM","Phat trien Mobile"));
        listCMC.add(new Job("3","Devops CMC","DN","Phat trien Devops"));

        listEmployer = new ConcurrentHashMap<>();
        listEmployer.put("1",new Employer("1","Viettel","1.png","https://vietteltelecom.vn/","viettel@gmail.com",listVietel));
        listEmployer.put("2",new Employer("2","FPT","2.png","https://FPT.com.vn/","fpt@gmail.com",listFPT));
        listEmployer.put("3",new Employer("3","TOPCV","3.png","https://topcv.com.vn/","topcv@gmail.com",listTopcv));
        listEmployer.put("4",new Employer("4","CMC","4.png","https://CMC.com.vn/","cmc@gmail.com",listCMC));
    }
}
