package vn.techmaster.bank.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class Transact extends Command {

  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable=true, insertable=true, updatable=false)
  private Account fromAcc; //Chuyển từ Account nào

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(nullable=true, insertable=true, updatable=false)
  private Account toAcc; //Chuyển đến Account nào


  private Long amount;


  public Transact(User requester, Account fromAcc, Account toAcc, Long amount) {
    super(requester);
    this.fromAcc = fromAcc;
    this.toAcc = toAcc;
    this.amount = amount;
  }

  public Transact() {

  }
}
