package vn.techmaster.job_hunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import vn.techmaster.job_hunt.model.Employer;
import vn.techmaster.job_hunt.repository.EmployerRepo;
import vn.techmaster.job_hunt.request.EmployerRequest;
import vn.techmaster.job_hunt.service.StorageService;

import java.util.UUID;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
    @Autowired
    private EmployerRepo employerRepo;
    @Autowired
    private StorageService storageService;
    @GetMapping
    public String listAllEmployers(Model model) {
        model.addAttribute("employers", employerRepo.getAll());
        return "employers";
    }
    @GetMapping("/{id}")
    public String showEmployerDetailById(@PathVariable("id") String id, Model model){
        model.addAttribute("employer", employerRepo.findById(id));
        return "employer";
    }
    @GetMapping(value = "/add")
    public String addEmpoylerForm(Model model){
        model.addAttribute("employer",new EmployerRequest());
        return "employer_add";
    }
    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String upload(@ModelAttribute EmployerRequest employerRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employer_add";
        }
        String id  = UUID.randomUUID().toString();
        Employer newEmployer = new Employer(id,
                employerRequest.getName(),
                employerRequest.getLogo_path().getOriginalFilename(),
                employerRequest.getWebsite(),
                employerRequest.getEmail());
        storageService.uploadFile(employerRequest.getLogo_path());
        employerRepo.add(newEmployer);
        return "redirect:/employer";
    }
}
