package vn.techmaster.bank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithDrawRequest {
    private String user_id;
    private String account_id;
    private Long amount;
}
