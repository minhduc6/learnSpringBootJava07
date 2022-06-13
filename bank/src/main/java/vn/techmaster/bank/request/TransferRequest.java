package vn.techmaster.bank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private String user_id;
    private String account_send;
    private String account_recv;
    private Long amount;
}
