package vn.techmasterr.jobhunt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.repository.EmployerRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class EmployerServiceImp implements EmployerService{
    private  ConcurrentMap<String,Employer> listEmployer ;
    public  EmployerServiceImp(){
        listEmployer = new ConcurrentHashMap<>();
        listEmployer.put("1",new Employer("1","Viettel","https://vietteltelecom.vn/","viettel@gmail.com","số 3 tôn thất thuyết"));
        listEmployer.put("2",new Employer("2","FPT","https://FPT.com.vn/","fpt@gmail.com","số 3 Cầu Giấy"));
        listEmployer.put("3",new Employer("3","TOPCV","https://topcv.com.vn/","topcv@gmail.com","số 2 Cầu Giấy"));
        listEmployer.put("4",new Employer("4","CMC","https://CMC.com.vn/","cmc@gmail.com","số 1 Cầu Giấy"));
    }
    @Override
    public ConcurrentMap<String,Employer> getListEmployer() {
        return listEmployer;
    }

    @Override
    public Employer getById(String id) {
        return listEmployer.get(id);
    }

    @Override
    public Employer addEmployer(Employer employer) {
        String id  = UUID.randomUUID().toString();
        employer.setId(id);
        listEmployer.put(id,employer);
        return employer;
    }

    @Override
    public Employer updateEmployer(Employer employerRequest, String id) {
        Employer employerAfter = listEmployer.get(id);
        employerAfter.setName(employerRequest.getName());
        employerAfter.setName(employerRequest.getWebsite());
        employerAfter.setAddress(employerRequest.getEmail());
        employerAfter.setAddress(employerRequest.getAddress());
        return employerAfter;
    }

    @Override
    public Employer deleteEmployer(String id) {
        return listEmployer.remove(id);
    }
}
