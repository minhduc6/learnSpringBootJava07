package vn.techmaster.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutLaiTheoThang {
    private int saving_id;
    private  double tienLai;
    private LocalDateTime time;
}
