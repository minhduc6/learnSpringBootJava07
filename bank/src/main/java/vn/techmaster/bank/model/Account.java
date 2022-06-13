package vn.techmaster.bank.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class Account {
  @Id
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Bank bank; //Mỗi account phải mở ở một ngân hàng
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user; //Mỗi account phải gắn vào một user

  private Long balance;

  private boolean status;

  public Account(String id, Bank bank, User user, Long balance ,boolean status) {
    this.id = id;
    this.bank = bank;
    this.user = user;
    this.balance = balance;
    this.status = status;
  }
}
