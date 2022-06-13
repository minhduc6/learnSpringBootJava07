package vn.techmaster.bank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TatToanRequest {
    private int saving_id;
    private String user_id;
    private String account_id;
}
