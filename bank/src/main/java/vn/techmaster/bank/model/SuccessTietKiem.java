package vn.techmaster.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessTietKiem {
    private String mess;
    private String account_id;
    private Long soDu;
}
