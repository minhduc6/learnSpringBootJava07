package vn.techmaster.bank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhongKyHanRequest {
    private String user_id;
    private String accountSend_id;
    private String accountRecv_id;
    private Long amount;
}
