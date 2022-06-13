package vn.techmaster.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingInfo {
    private String user_id;
    private String account_id;
    private String thoiHan;
    private Double laiSuat;
    private Long soTienGui;
    private Double tienDuKien;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
}
