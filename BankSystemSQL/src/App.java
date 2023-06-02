import java.util.Scanner;

import infra.DAO;
import services.BankServices;

public class App {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
        
    Integer respOP;
    Long id;
    Double amount;
    DAO dao = new DAO();

    System.out.println("Bank's app: ");

    BankServices.menu();
    System.out.print("\nWhat option do you want? ");
    respOP = sc.nextInt();
    sc.nextLine();

    switch(respOP){
      case 1:
        BankServices.showClients();
        System.out.print("What client do you want to change? ");
        id = sc.nextLong();
        sc.nextLine();
        System.out.print("How much do you want to withdraw? ");
        amount = sc.nextDouble();
        sc.nextLine();
        BankServices.withdraw(id, amount);
      break;

      case 2: 
        BankServices.showClients();
        System.out.print("What client do you want to change? ");
        id = sc.nextLong();
        sc.nextLine();
        System.out.print("How much do you want to deposit? ");
        amount = sc.nextDouble();
        sc.nextLine();
        BankServices.deposit(id, amount);
      break;

      case 3:
        BankServices.showClients();
        System.out.print("What client do you want to change? ");
        id = sc.nextLong();
        sc.nextLine();
        System.out.print("How much do you want to pay with PIX? ");
        amount = sc.nextDouble();
        sc.nextLine();
        BankServices.pix(id, amount);
      break;

      case 4: 
        System.out.println("Register client:");
        BankServices.registerClient(sc);
      break;

      case 5: 
        BankServices.showClients();
        System.out.println("What client will pay the transference? ");
        Long idWithdraw = sc.nextLong();
        sc.nextLine();
        System.out.println("How much this client will transfer? ");
        amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("What client will receive the transference? ");
        Long idDeposit = sc.nextLong();
        sc.nextLine();
        BankServices.betweenAccountsTrans(idWithdraw, idDeposit, amount);
      break;

      case 6:
        String respShow;
        System.out.print("Do you want to show one or all the clients? (One/All) ");
        respShow = sc.nextLine();
        if(respShow.equalsIgnoreCase("one")){
          System.out.println("Type the id: ");
          id = sc.nextLong();
          sc.nextLine();
          BankServices.showClient(id);
        } else {
          BankServices.showClients();
        }
      break;
    }
    sc.close();
    dao.close();
  }
}
