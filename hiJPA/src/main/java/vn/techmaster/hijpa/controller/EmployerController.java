package vn.techmaster.hijpa.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import vn.techmaster.hijpa.model.Employer;
import vn.techmaster.hijpa.repository.EmployerRepo;
import vn.techmaster.hijpa.request.EmployerRequest;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {
    @Autowired private EmployerRepo employerRepo;
    @GetMapping
    public List<Employer> getAll() {
        return employerRepo.findAll();
    }

    @GetMapping(value="/{id}")
    public Optional<Employer> findByID(@PathVariable("id") Long id) {
        return employerRepo.findById(id);
    }

    @PostMapping
    public Employer addEmployer(@RequestBody EmployerRequest employerRequest){
        Employer employer = new Employer();
        employer.setName(employerRequest.getName());
        employer.setEmail(employerRequest.getEmail());
        employer.setWebsite(employerRequest.getWebsite());
        employerRepo.save(employer);
        return  employer;
    }

    @PutMapping(value="/{id}")
    public Optional<Employer> updateFindByID(@PathVariable("id") Long id,@RequestBody EmployerRequest employer) {
        Employer currentEmployer = employerRepo.getById(id);
        currentEmployer.setName(employer.getName());
        currentEmployer.setWebsite(employer.getWebsite());
        currentEmployer.setEmail(employer.getEmail());
        employerRepo.save(currentEmployer);
        return Optional.of(currentEmployer);
    }

    @DeleteMapping (value="/{id}")
    public void deleteFindByID(@PathVariable("id") Long id) {
        employerRepo.deleteById(id);
    }
}
