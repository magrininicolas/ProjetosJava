package models;

public class Client {
  
  private Integer id;

  private String name;

  private Integer agency;

  private Long currentAccount;

  private Double balance;

  public Client() {

  }

  public Client(Integer id, String name, Integer agency, Long currentAccount, Double balance) {
    this.id = id;
    this.name = name;
    this.agency = agency;
    this.currentAccount = currentAccount;
    this.balance = balance;
  }

  public Integer getId() {
    return id;
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
    return "ID: " + getId() + " - Name: " + getName() + " - Agency: " + getAgency() + " - CC: " + getCurrentAccount() + " - Balance: " + String.format("%.2f", getBalance());
  }
}
