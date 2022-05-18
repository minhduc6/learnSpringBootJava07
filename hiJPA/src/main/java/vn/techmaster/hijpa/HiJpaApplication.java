package vn.techmaster.hijpa;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.techmaster.hijpa.model.Employer;
import vn.techmaster.hijpa.model.Job;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootApplication
public class HiJpaApplication implements  CommandLineRunner  {

    @Autowired
    private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(HiJpaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0 ; i < 100 ; i++){
             var employer = Employer.builder()
                     .name(faker.company().name()).email(faker.internet().emailAddress())
                     .website("https://" + faker.internet().domainName()).build();
                     em.persist(employer);
                var job = Job.builder().title(faker.programmingLanguage().name()).description(faker.internet().domainWord()).build();
                em.persist(job);
        }
        em.flush();
    }
}
