package vn.techmasterr.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.service.EmployerService;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerService employerService;


    @GetMapping("/list")
    public String listEmployerPage(Model model){
        ConcurrentMap<String,Employer> listEmployerHashMap = employerService.getListEmployer();
        List<Employer> listEmployer = listEmployerHashMap.values().stream().toList();
        model.addAttribute("listEmployer",listEmployer);
        return "listemployer";
    }
    @GetMapping("/add")
    public String addBook(Model model)
    {
        Employer employer = new Employer();
        model.addAttribute("employer", employer);
        model.addAttribute("pageTitle", "Create New Employer");
        return "employer-form";
    }

    @PostMapping("/save")
    public String saveBook(Employer employer)
    {
        String id = employer.getId();
        if (id == "") {
            employerService.addEmployer(employer);
        }
        else if(id != "")
        {
            employerService.updateEmployer(employer,id);
        }
        return "redirect:/employer/list";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable(name = "id") String id , Model model)
    {
        Employer employer = employerService.getById(id);
        model.addAttribute("employer", employer);
        model.addAttribute("pageTitle", "Edit Employer ID : " + id);
        return "employer-form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id , Model model)
    {
        employerService.deleteEmployer(id);
        return "redirect:/employer/list";
    }
}
