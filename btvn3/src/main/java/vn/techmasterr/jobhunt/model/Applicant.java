package vn.techmasterr.jobhunt.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Applicant {
    private  String id;
    private String name;
    private String email;
    private String phone;
    private String skills;
}
