package vn.techmaster.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhongKyHan {
    private String user_id;
    private String accountSend_id;
    private String accountRecv_id;
    private String thoiHan;
    private Double laiSuat;
    private Long soTienGui;
    private Double tienDuKien;
    private LocalDateTime timeStart;
}
