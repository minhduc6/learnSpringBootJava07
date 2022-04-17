package vn.techmasterr.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmasterr.jobhunt.model.Applicant;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.repository.ApplicantRepository;
import vn.techmasterr.jobhunt.request.ApplicantRequest;
import vn.techmasterr.jobhunt.service.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/add/{emailEmployer}")
    public String addApplicant(@PathVariable("emailEmployer") String emailEmployer,Applicant applicant, Model model){
        model.addAttribute("applicant",applicant);
        model.addAttribute("emailEmployer",emailEmployer);
        return "applicant_form";
    }

    @PostMapping("/add/{emailEmployer}")
    public String sendApplicant(@PathVariable("emailEmployer") String emailEmployer,Applicant applicant) throws MessagingException, IOException {
        emailService.sendmail(emailEmployer,applicant);
        return  "success";
    }
}
