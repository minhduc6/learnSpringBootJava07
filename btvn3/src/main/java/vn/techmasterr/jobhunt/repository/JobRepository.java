package vn.techmasterr.jobhunt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.model.Job;
import vn.techmasterr.jobhunt.service.EmployerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class JobRepository {
       public Set<Job> getAllJobByEmployer(Employer employer){
           return  employer.getListJob();
       }
       public Job addJob(Employer employer ,Job jobRequest){
           String id  = UUID.randomUUID().toString();
           Job job = new Job(id,jobRequest.getTitle(),jobRequest.getAddress(),jobRequest.getDescription());
           if(employer.getListJob() != null){
               employer.getListJob().add(job);
           }else {
               Set<Job> listJob = new HashSet<>();
               listJob.add(job);
               employer.setListJob(listJob);
           }
           return  job;
       }
       public  Job getByID(Employer employer,String id){
           List<Job> listJob = employer.getListJob().stream().toList();
           for (int i = 0 ; i < listJob.size();i++){
               if(listJob.get(i).getId().equals(id)){
                   return listJob.get(i);
               }
           }
           return  null;
       }

       public Job updateJob(Employer employer,Job jobRequest ,String id){
           Job job = getByID(employer,id);
           job.setTitle(jobRequest.getTitle());
           job.setAddress(jobRequest.getAddress());
           job.setDescription(jobRequest.getDescription());
           return job;
       }
    public Job delete(Employer employer ,String id){
        Job job = getByID(employer,id);
        employer.getListJob().remove(job);
        return job;
    }
}
