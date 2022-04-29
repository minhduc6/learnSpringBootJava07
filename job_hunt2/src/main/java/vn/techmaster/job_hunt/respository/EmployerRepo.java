package vn.techmaster.job_hunt.respository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Employer;

@Repository
public class EmployerRepo {
  private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();
  public EmployerRepo() {

  }

  //Cần sinh UUID ở đây
  public Employer add(Employer employer) {
    String id = UUID.randomUUID().toString();
    employer.setId(id);
    employers.put(id, employer);
    return employer;
  }

  public Collection<Employer> getAll(){
    return employers.values();
  }

  public Employer findById(String id){
    return employers.get(id);
  }


  public Employer updateEmployer(Employer employerRequest, String id) {
    Employer employerAfter = employers.get(id);
    employerAfter.setName(employerRequest.getName());
    employerAfter.setWebsite(employerRequest.getWebsite());
    employerAfter.setEmail(employerRequest.getEmail());
    return employerAfter;
  }

  public Employer deleteById(String id) {
    //var emp = employers.get(id);
    return employers.remove(id);
  }

  @PostConstruct
	public void addSomeData() {
		this.add(Employer.builder()
    .name("FPT")
    .website("https://fpt.com.vn")
    .logo_path("fpt.jpeg")
    .email("hr@fpt.com.vn").build());

    this.add(Employer.builder()
    .name("CMC")
    .website("https://cmc.com.vn")
    .logo_path("cmc.png")
    .email("hr@cmc.com.vn").build());
	}

}
