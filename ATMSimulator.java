import java.util.Scanner;

public class ATMSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 1000.0;
        int choice;

        do {
            // 菜单
            System.out.println("\n1. Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("0. Exit");
            System.out.print("Choose action: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // 查询余额
                    System.out.println("Current balance: " + balance);
                    break;

                case 2:
                    // 取款
                    System.out.print("Enter amount: ");
                    double withdraw = sc.nextDouble();

                    if (withdraw <= 0) {
                        System.out.println("Error: amount must be positive.");
                    } else if (withdraw % 100 != 0) {
                        System.out.println("Error: amount must be multiple of 100.");
                    } else if (withdraw > balance) {
                        System.out.println("Error: insufficient funds. Available: " + balance);
                    } else {
                        balance -= withdraw;
                        System.out.println("Withdraw success. New balance: " + balance);
                    }
                    break;

                case 3:
                    // 存款
                    System.out.print("Enter amount: ");
                    double deposit = sc.nextDouble();

                    if (deposit <= 0) {
                        System.out.println("Error: amount must be positive.");
                    } else if (deposit % 100 != 0) {
                        System.out.println("Error: amount must be multiple of 100.");
                    } else {
                        balance += deposit;
                        System.out.println("Deposit success. New balance: " + balance);
                    }
                    break;

                case 0:
                    System.out.println("Exit ATM.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}
