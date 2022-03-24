package vn.techmaster.restapicrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.restapicrud.model.Job;
import vn.techmaster.restapicrud.model.Location;
import vn.techmaster.restapicrud.request.JobRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class HomeController {
    private ConcurrentMap<String,Job> listJob;
    public HomeController(){
         listJob = new ConcurrentHashMap<>();
         listJob.put("1",new Job("1","IT","Develop SoftWare", Location.HANOI,1000,1100,"techmaster@gmail.com"));
         listJob.put("2",new Job("2","Desktop","Help Desk", Location.DANANG,1100,1300,"techmaster@gmail.com"));
         listJob.put("3",new Job("3","Networking","Net", Location.HANOI,1400,1500,"techmaster@gmail.com"));
         listJob.put("4",new Job("4","Devops","Devops", Location.HOCHIMINH,1600,1700,"techmaster@gmail.com"));
    }

    @GetMapping("/listJob")
    public List<Job> getJobs(){
        return  listJob.values().stream().toList();
    }
    @GetMapping("/getJob/{id}")
    public Job getJob(@PathVariable("id") String id){
        Job job = listJob.get(id);
        return  job;
    }
    @PostMapping("/addJob")
    public Job createNewJob(@RequestBody JobRequest jobRequest){
        String id  = UUID.randomUUID().toString();
        Job newJob = new Job(id,jobRequest.getTitle(),jobRequest.getDescription(),jobRequest.getLocation(),jobRequest.getMin_salary(),jobRequest.getMax_salary(),jobRequest.getEmail_to());
        listJob.put(id,newJob);
        return  newJob;
    }

    @PutMapping("/updateJob/{id}")
    public  Job updateJob(@PathVariable("id") String id ,@RequestBody JobRequest jobRequest){
        Job updateJob = new Job(id,jobRequest.getTitle(),jobRequest.getDescription(),jobRequest.getLocation(),jobRequest.getMin_salary(),jobRequest.getMax_salary(),jobRequest.getEmail_to());
        listJob.replace(id,updateJob);
        return  updateJob;
    }
    @DeleteMapping("/deleteJob/{id}")
    public Job deleteJob(@PathVariable("id") String id){
        Job removeJob = listJob.remove(id);
        return  removeJob;
    }

    @GetMapping("/job/sortbylocation")
    public List<Job> sortByLocation(){
          return listJob.values().stream()
                .sorted(Comparator.comparing(Job::getLocation))
                .collect(Collectors.toList());
    }

    @GetMapping("/job/sortbyprice")
    public List<Job> sortByPrice(){
        List<Job> result = listJob.values().stream()
                .sorted(Comparator.comparingInt(Job::getMin_salary).reversed()).collect(Collectors.toList());
        return  result;
    }
    @GetMapping("job/salary/{salary}")
    public List<Job> findJobBySalary(@PathVariable("salary") int salary){
        return  listJob.values().stream()
                .filter(job -> (job.getMin_salary() <= salary) && job.getMax_salary() >= salary)
                .collect(Collectors.toList());
    }
    @GetMapping("job/keyword/{keyword}")
    public List<Job> findJobByKeyword(@PathVariable("keyword") String keyword){
        List<Job> jobList = listJob.values().stream().toList();
        List<Job> result = new ArrayList<>();
        for (Job job : jobList) {
               if(job.findJobByKeyword(keyword) == true){
                     result.add(job);
               }
        }
        return result;
    }
    @GetMapping("job/query")
    public List<Job> queryJob(@RequestParam String location,@RequestParam String keyword){
        List<Job> jobList = listJob.values().stream().toList();
        List<Job> result = new ArrayList<>();
        for (Job job : jobList) {
            if(job.getLocation().toString().equals(location) && job.findJobByKeyword(keyword) == true){
                result.add(job);
            }
        }
        return result;
    }











}
