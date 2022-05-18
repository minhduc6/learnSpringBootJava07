package vn.techmaster.hijpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.hijpa.model.Employer;

public interface EmployerRepo extends JpaRepository<Employer,Long> {
}
