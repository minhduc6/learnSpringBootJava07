package vn.techmasterr.jobhunt.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Job {
    private String id;
    private String title;
    private String address;
    private String description;
}