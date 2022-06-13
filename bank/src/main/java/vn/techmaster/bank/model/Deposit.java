package vn.techmaster.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Deposit extends Command{
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    public Deposit() {
    }

    public Deposit(User requester, Long amount, Account account) {
        super(requester);
        this.amount = amount;
        this.account = account;
    }
}
