package vn.techmasterr.jobhunt.repository;

import org.springframework.stereotype.Repository;
import vn.techmasterr.jobhunt.model.Employer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class EmployerRepository {
    public ConcurrentMap<String,Employer> initEmployerRepository() {
        ConcurrentMap<String,Employer> listEmployer = new ConcurrentHashMap<>();
        listEmployer.put("1",new Employer("1","Viettel","https://vietteltelecom.vn/","viettel@gmail.com","số 3 tôn thất thuyết"));
        listEmployer.put("2",new Employer("2","FPT","https://FPT.com.vn/","fpt@gmail.com","số 3 Cầu Giấy"));
        listEmployer.put("3",new Employer("3","TOPCV","https://topcv.com.vn/","topcv@gmail.com","số 2 Cầu Giấy"));
        listEmployer.put("4",new Employer("4","CMC","https://CMC.com.vn/","cmc@gmail.com","số 1 Cầu Giấy"));
        return  listEmployer;
    }
}
