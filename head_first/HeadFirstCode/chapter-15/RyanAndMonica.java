class BankAccount {
   private int balance = 100;
  
   public int getBalance() {
      return balance;
   }

   public void withdraw(int amount) {
      balance = balance - amount;
   }
}

 public class RyanAndMonica implements Runnable {

   private BankAccount account = new BankAccount();

   public static void main (String [] args) {
      RyanAndMonica  theJob = new RyanAndMonica();
      Thread one = new Thread(theJob);
      Thread two = new Thread(theJob);
      one.setName("Ryan");
      two.setName("Monica");
      one.start();
      two.start();
    }

  public void run() {
   for (int x = 0; x < 10; x++) {
      makeWithdrawal(10);
      if (account.getBalance() < 0) {
        System.out.println("Overdrawn!");
      }
    }
 }

  // to demonstrate the "overdrawn" error remove the "synchronized" modifier

  private synchronized void makeWithdrawal(int amount) {
     if (account.getBalance() >= amount) {
     System.out.println(Thread.currentThread().getName() + " is about to withdraw");
      try {
        System.out.println(Thread.currentThread().getName() + " is going to sleep");
        Thread.sleep(500);  
      } catch(InterruptedException ex) {ex.printStackTrace(); }
      System.out.println(Thread.currentThread().getName() + " woke up.");
      account.withdraw(amount);
      System.out.println(Thread.currentThread().getName() + " completes the withdrawl");
     }
    else {
      System.out.println("Sorry, not enough money for " + Thread.currentThread().getName());
   }
 }
}

  
     
      