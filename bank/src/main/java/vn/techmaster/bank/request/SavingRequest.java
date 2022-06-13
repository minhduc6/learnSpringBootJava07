package vn.techmaster.bank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.bank.model.KyHan;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingRequest {
    private String user_id;
    private String accountSend_id;
    private String accountRecv_id;
    private KyHan kyHan;
    private Long amount;
}
