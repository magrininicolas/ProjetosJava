package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Client;

public abstract class BankServices extends Client {

  private static Integer clientCounter = 0;
  
  public static List<Client> clients = new ArrayList<>();

  public static void menu() {
    System.out.print("Menu:\n1 - Withdraw\n2 - Deposit\n3 - PIX\n4 - Register Client\n5 - Between Accounts Transfer\n6 - Consult");
  }

  private static boolean idExists(Integer id) {
    return clients.stream().anyMatch(c -> c.getId() == id);
  }


  public static void withdraw(Integer id, Double amount) {
    if(idExists(id)) {
      clients.stream()
      .filter(c -> c.getId() == id && c.getBalance() > amount) 
      .forEach(c -> c.setBalance(c.getBalance() - amount));
    } else {
      System.out.println("ID: " + id + " doesn't exists in our database!");
    }
  }

  public static void deposit(Integer id, Double amount) {
    if(idExists(id)){
      clients.stream()
      .filter(c -> c.getId() == id)
      .forEach(c -> c.setBalance(c.getBalance() + amount));
    } else {
      System.out.println("ID: " + id + " doesn't exists in our database!");
    }
  }

  public static void pix(Integer id, Double amount) {
    if(idExists(id)) {
      for(Client c : clients) {
        if(c.getId() == id && c.getBalance() > amount) {
          c.setBalance(c.getBalance() - amount);
        }
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
    clientCounter++;
    clients.add(new Client(clientCounter, name, agency, cc, balance));
  }

    public static void betweenAccountsTrans(Integer id1, Integer id2, double amount) {
      withdraw(id1, amount);
      deposit(id2, amount);
    }

    public static void showClient(Integer id) { 
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
}
