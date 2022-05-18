package vn.techmaster.hijpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.hijpa.model.Job;
import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

@DataJpaTest
public class TestJob {
    @Autowired
    private EntityManager em;

    @Test
    public void addJob(){
        Job job  = Job.builder().title("Java Developer")
                .description("Kinh Nghiem 12 thang").build();
        em.persist(job);
        assertThat(job.getId()).isNotNull();
    }
}
