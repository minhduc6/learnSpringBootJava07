package vn.techmaster.job_hunt.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.techmaster.job_hunt.model.City;
import vn.techmaster.job_hunt.model.Job;
import vn.techmaster.job_hunt.respository.JobRepo;

@Controller
@RequestMapping("/job")
public class JobController {
  @Autowired
  private JobRepo jobRepo;
  @GetMapping
  public String listAllJobs(Model model) {
    model.addAttribute("jobs", jobRepo.getAll());
    return "jobs";
  }

  @GetMapping("/add")
  public String showAddJobForm(Model model, @RequestParam("emp_id") String emp_id) {
    Job job = new Job();
    job.setEmp_id(emp_id);
    model.addAttribute("job", job);
    model.addAttribute("cities", City.values());
    return "job_add";
  }

  @GetMapping("/edit/{id}")
  public String showEditJobForm(Model model, @PathVariable("id") String id) {
    Job job = jobRepo.findById(id);
    model.addAttribute("job", job);
    model.addAttribute("cities", City.values());
    return "job_add";
  }

  @PostMapping("/save")
  public String saveJob(Job job)
  {
    String id = job.getId();
    if (id.equals("")) {
      jobRepo.addJob(job);
    }
    else if(id != "")
    {
      jobRepo.update(job);
    }
    return "redirect:/job";
  }

  @GetMapping("/delete/{id}")
  public String deleteJob(@PathVariable("id") String id) {
    jobRepo.deleteById(id);
    return "redirect:/job";
  }
}
