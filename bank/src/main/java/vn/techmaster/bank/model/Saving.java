package vn.techmaster.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Saving extends Command{
    private Long amount;

    private String kyHan;

    private Double laiSuat;

    private String statusSaving;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;



    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account accountSend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account accountRecv;

    public Saving() {
    }

    public Saving(User requester, Long amount, Account accountSend ,Account accountRecv,String kyHan, Double laiSuat,String statusSaving, LocalDateTime timeStart , LocalDateTime timeEnd) {
        super(requester);
        this.amount = amount;
        this.accountSend = accountSend;
        this.accountRecv = accountRecv;
        this.laiSuat = laiSuat;
        this.kyHan = kyHan;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.statusSaving = statusSaving;
    }
}
