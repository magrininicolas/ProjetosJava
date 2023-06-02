package services;

import java.util.List;
import java.util.Scanner;

import infra.DAO;
import models.Client;

public class BankServices {

  private static DAO dao = new DAO();

  private static List<Client> clients = dao.obtainAll();

  public static void menu() {
    System.out.print("Menu:\n1 - Withdraw\n2 - Deposit\n3 - PIX\n4 - Register Client\n5 - Between Accounts Transfer\n6 - Consult");
  }

  private static boolean idExists(Long id) {
    return clients.stream().anyMatch(c -> c.getId() == id);
  }

  public static void withdraw(Long id, Double amount) {
    if(idExists(id)) {
      Client client = dao.find(Client.class, id);
      if(client.getBalance() >= amount) {
        client.setBalance(client.getBalance() - amount);
        dao.openTransaction().update(client).closeTransaction();
      } else {
        System.out.println("Insufficient Balance!");
      }
    } else {
      System.out.println("ID: " + id + " doesn't exists in our database!");
    }
  }

  public static void deposit(Long id, Double amount) {
    if(idExists(id)) {
      Client client = dao.find(Client.class, id);
      client.setBalance(client.getBalance() + amount);
      dao.openTransaction().update(client).closeTransaction();
    } else {
      System.out.println("ID: " + id + " doesn't exists in our database!");
    }
  }

  public static void pix(Long id, Double amount) {
    if(idExists(id)) {
      Client client = dao.find(Client.class, id);
      if(client.getBalance() >= amount) {
        client.setBalance(client.getBalance() - amount);
        dao.openTransaction().update(client).closeTransaction();
      } else {
        System.out.println("Insufficient Balance!");
      }
    } else {
      System.out.println("ID: " + id + " doesn't exists in our database!");
    }
  }

  public static void registerClient(Scanner sc) {
    System.out.print("Client's name: ");
    String name = sc.nextLine();
    System.out.print("Client's agency: ");
    Integer agency = sc.nextInt();
    sc.nextLine();
    System.out.print("Client's current account: ");
    Long cc = sc.nextLong();
    sc.nextLine();
    System.out.print("Client will deposit some value as start balance? (Yes/No): ");
    String resp = sc.nextLine();
    Double balance;
    if(resp.equalsIgnoreCase("Yes")) {
      System.out.print("Client's balance: ");
      balance = sc.nextDouble();
      sc.nextLine();
    } else {
      balance = 0.0;
    }
    dao.openTransaction().update(new Client(name, agency, cc, balance)).closeTransaction();
  }

  public static void showClient(Long id) { 
    if(idExists(id)) {
      clients.stream()
      .filter(c -> c.getId() == id)
      .forEach(System.out::println);
    } else {
      System.out.println("ID: " + id + " doesn't exists in our database!");
    }
  }

  public static void showClients() {
    System.out.println("Clients:");
    clients.stream()
    .forEach(System.out::println);
  }

  public static void betweenAccountsTrans(Long id1, Long id2, Double amount) {
    withdraw(id1, amount);
    deposit(id2, amount);
  }
}
