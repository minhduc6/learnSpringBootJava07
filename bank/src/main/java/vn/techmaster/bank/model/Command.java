package vn.techmaster.bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public class Command {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private  User requester;

    private LocalDateTime startAt;
    private LocalDateTime finishAt;

    private  CommandStatus commandStatus;

    public Command(User requester) {
        this.requester = requester;
    }

    @PrePersist
    public void prePersist(){
        startAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        finishAt = LocalDateTime.now();
    }
}
