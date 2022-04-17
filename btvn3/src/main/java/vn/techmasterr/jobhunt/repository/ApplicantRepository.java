package vn.techmasterr.jobhunt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.techmasterr.jobhunt.model.Applicant;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.model.Job;
import vn.techmasterr.jobhunt.request.ApplicantRequest;
import vn.techmasterr.jobhunt.service.EmployerService;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class ApplicantRepository {
    private ConcurrentMap<String, Applicant> listApplicant ;
    @Autowired
    private EmployerService employerService;


    public ApplicantRepository(){

    }


    public List<Job> getAllJob(){
        List<Job> listJob = new ArrayList<>();
        List<Employer> listEmployer = employerService.getListEmployer().values().stream().toList();

        for (int i = 0; i < listEmployer.size(); i++) {
             List<Job> jobs =  listEmployer.get(i).getListJob().stream().toList();
            for (int j = 0; j < jobs.size(); j++) {
                listJob.add(jobs.get(j));
            }
        }
        return  listJob;
    }

    public List<Applicant> getAllApplicatnt(){
        return listApplicant.values().stream().toList();
    }

    public Applicant getApplicantById(String id){
        List<Applicant> applicantList =  getAllApplicatnt();
        for (int i = 0; i < applicantList.size(); i++) {
            if(applicantList.get(i).getId().equals(id)){
                return applicantList.get(i);
            }
        }
        return null;
    }

}
