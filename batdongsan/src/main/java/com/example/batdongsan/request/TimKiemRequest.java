package com.example.batdongsan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimKiemRequest {
    private String thanhPho;
    private String quanHuyen;
    private String phuongXa;
    private String keyword;
    private int loaiNha_id;
    private String muaGia;
    private String dienTich;
}
