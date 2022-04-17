package vn.techmaster.job_hunt.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Employer;

import javax.annotation.PostConstruct;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerRepo() {

    }


    public Employer add(Employer employer){
        employers.put(employer.getId(), employer);
        return employer;
    }

    public Collection<Employer> getAll(){
        return employers.values();
    }

    public Employer findById(String id){
        return employers.get(id);
    }


    @PostConstruct
    public void addSomeData() {
        this.add(new Employer("1","Viettel","1.png","https://vietteltelecom.vn/","viettel@gmail.com"));
        this.add(new Employer("2","FPT","2.png","https://fpt.com.vn/","fpt@gmail.com"));
        this.add(new Employer("3","CMC","3.png","https://cmc.com.vn/","cmc@gmail.com"));
        this.add(Employer.builder().id("4").name("SVMC")
                .logo_path("4.png")
                .website("https://cmccom.vn/")
                .email("cmc@gmail.com").build());
    }

}
