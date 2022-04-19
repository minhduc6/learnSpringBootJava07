package vn.techmaster.job_hunt.respository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Job;

@Repository
public class JobRepo {
  private ConcurrentHashMap<String, Job> jobs = new ConcurrentHashMap<>();

  public Collection<Job> getAll(){
    return jobs.values();
  }

  public Job addJob(Job job) {
    String id = UUID.randomUUID().toString();
    job.setId(id);
    jobs.put(id, job);
    return job;
  }

  public Job findById(String id) {
    return jobs.get(id);
  }

  public Job deleteById(String id) {
    return jobs.remove(id);
  }

  public void update(Job job){
    jobs.put(job.getId(), job);
  }
}
