package vn.techmaster.job_hunt.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {
    @Id
    private String id;

    private String title;

    private String description;

    public Job(String id, String title, String description, City city, LocalDateTime update_at, LocalDateTime create_at, Employer employer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.city = city;
        this.update_at = update_at;
        this.create_at = create_at;
        this.employer = employer;
    }

    @Enumerated(EnumType.STRING)
    private City city;

    private LocalDateTime update_at;

    private LocalDateTime create_at;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Applicant> applicants = new ArrayList<>();




}
