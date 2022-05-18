package vn.techmaster.hijpa.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
}
