package vn.techmaster.job_hunt.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.job_hunt.FileUploadUtil;
import vn.techmaster.job_hunt.model.Employer;
import vn.techmaster.job_hunt.request.EmployerRequest;
import vn.techmaster.job_hunt.respository.EmployerRepo;


@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
  @Autowired
  private EmployerRepo employerRepo;


  @GetMapping
  public String listAllEmployers(Model model) {
    model.addAttribute("employers", employerRepo.getAll());
    return "employers";
  }

  @GetMapping(value = "/{id}")
  public String showEmployerDetailByID(Model model, @PathVariable String id) {
    model.addAttribute("employer", employerRepo.findById(id));
    return "employer";
  }

  @GetMapping(value = "/add")
  public String addEmployerForm(Model model) {
    model.addAttribute("employer", new EmployerRequest("", "", "", ""));
    return "employer_add";
  }

  @PostMapping(value = "/add", consumes = { "multipart/form-data" })
  public String addEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest,
                            @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

    // Thêm vào cơ sở dữ liệu
    if(employerRequest.id().equals(""))
    {
      Employer emp = employerRepo.add(Employer.builder()
              .name(employerRequest.name())
              .website(employerRequest.website())
              .email(employerRequest.email())
              .logo_path(fileName)
              .build());
      String uploadDir = "employer-photos/" + emp.getId();
      FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    } else if(employerRequest.id() != ""){
      Employer emp =  employerRepo.findById(employerRequest.id());
      emp.setName(employerRequest.name());
      emp.setWebsite(employerRequest.website());
      emp.setEmail(employerRequest.email());
      emp.setLogo_path(fileName);
      String uploadDir = "employer-photos/" + emp.getId();
      FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
    return "redirect:/employer";
  }

  @GetMapping("/edit/{id}")
  public String editCar(@PathVariable(name = "id") String id , Model model)
  {
    Employer employer = employerRepo.findById(id);
    model.addAttribute("employer", employer);
    model.addAttribute("pageTitle", "Edit Employer ID : " + id);
    return "employer_add";
  }

@GetMapping(value ="/delete/{id}")
public String deleteEmployerByID(@PathVariable String id) {
  Employer emp = employerRepo.deleteById(id);
  return "redirect:/employer";
}

}
