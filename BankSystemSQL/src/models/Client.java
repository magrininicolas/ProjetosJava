package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 200, nullable = false)
  private String name;

  @Column(length = 3, nullable = false)
  private Integer agency;

  @Column(nullable = false)
  private Long currentAccount;

  @Column(nullable = false, precision = 11, scale = 2)
  private Double balance;

  public Client() {
  }

  public Client(String name, Integer agency, Long currentAccount, Double balance) {
    this.name = name;
    this.agency = agency;
    this.currentAccount = currentAccount;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAgency() {
    return agency;
  }

  public void setAgency(Integer agency) {
    this.agency = agency;
  }

  public Long getCurrentAccount() {
    return currentAccount;
  }

  public void setCurrentAccount(Long currentAccount) {
    this.currentAccount = currentAccount;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "ID: " + getId() + " - Name: " + getName() + " - Agency: " + getAgency() + " - CA: " + getCurrentAccount() + " - Balance: " + getBalance();
  }

}
