package vn.techmasterr.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.model.Job;
import vn.techmasterr.jobhunt.repository.JobRepository;
import vn.techmasterr.jobhunt.service.EmployerService;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerService employerService;
    @GetMapping
    public String homePageJob(Model model){
         model.addAttribute("employers",employerService.getListEmployer().values());
         return "job_page";
    }
    @GetMapping("employer")
    public String getAllJob(@RequestParam String employerID,Model model){
       Employer employer =  employerService.getById(employerID);
       model.addAttribute("employerID",employerID);
       model.addAttribute("jobs",jobRepository.getAllJobByEmployer(employer));
       model.addAttribute("employers",employerService.getListEmployer().values());
       return "listjob";
    }
    @GetMapping("/detail/{employerID}/{idJob}")
    public String detailJob(@PathVariable("employerID") String employerID,@PathVariable("idJob") String idJob,Model model){
        Employer employer =  employerService.getById(employerID);
        Job job = jobRepository.getByID(employer,idJob);
        model.addAttribute("emailEmployer",employer.getEmail());
        model.addAttribute("job",job);
        return "detailJob";
    }
    @GetMapping("/add/{employerID}")
    public String addJob(@PathVariable("employerID") String employerID,Model model){
        Job job = new Job();
        model.addAttribute("employerID",employerID);
        model.addAttribute("job", job);
        model.addAttribute("pageTitle", "Create New Job");
        return "job-form";
    }
    @GetMapping("/edit/{employerID}/{idJob}")
    public String editCar(@PathVariable("employerID") String employerID,@PathVariable("idJob") String idJob,Model model)
    {
        Employer employer =  employerService.getById(employerID);
        Job job = jobRepository.getByID(employer,idJob);
        model.addAttribute("job", job);
        model.addAttribute("employerID",employerID);
        model.addAttribute("pageTitle", "Edit Job ID : " + idJob);
        return "job-form";
    }
    @PostMapping("/save/{employerID}")
    public String saveJob(@PathVariable("employerID") String employerID,Job job){
         String idJob = job.getId();
         if(idJob == ""){
             Employer employer =  employerService.getById(employerID);
             jobRepository.addJob(employer,job);
         }else {
             Employer employer =  employerService.getById(employerID);
             jobRepository.updateJob(employer,job,idJob);
         }
         return  "redirect:/job/employer?employerID=" +employerID;
    }
    @GetMapping("/delete/{employerID}/{idJob}")
    public String delete(@PathVariable("employerID") String employerID,@PathVariable("idJob") String idJob,Model model)
    {
        Employer employer =  employerService.getById(employerID);
        jobRepository.delete(employer,idJob);
        return "redirect:/job/employer?employerID=" +employerID;
    }


}
