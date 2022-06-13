package vn.techmaster.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.bank.model.Command;

import java.util.UUID;

@Repository
public interface CommandRepo extends JpaRepository<Command, Integer> {
}
