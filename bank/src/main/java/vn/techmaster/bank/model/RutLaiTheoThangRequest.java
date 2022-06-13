package vn.techmaster.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutLaiTheoThangRequest {
    private int saving_id;
    private String account_id;
    private  String user_id;
}
