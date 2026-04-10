package Lesson01_SolidPrinciples.lsp;

import java.util.ArrayList;
import java.util.List;

interface Account {
    void deposit();

    void withdraw();
}

class SavingsAccount implements Account {
    @Override
    public void deposit() {
        System.out.println("Deposit to savings account");
    }

    @Override
    public void withdraw() {
        System.out.println("Withdraw from Savings account");
    }
}

class CurrentAccount implements Account {
    @Override
    public void deposit() {
        System.out.println("Deposit to savings account");
    }

    @Override
    public void withdraw() {
        System.out.println("Withdraw from Savings account");
    }
}

class FDAccount implements Account {
    @Override
    public void deposit() {
        System.out.println("Deposit to savings account");
    }

    @Override
    public void withdraw() {
        throw new UnsupportedOperationException("'withdraw' not allowed");
    }
}

public class LspViolated {
    // The client is aware of the implementation of the interface Account, FDAccount
    // without knowing it will raise an exception and violating lsp.
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>(
                List.of(new CurrentAccount(), new SavingsAccount(), new FDAccount()));

        for (Account account : accounts) {
            account.deposit();
            if (account instanceof FDAccount) {
                System.out.println("Withdraw not possible for fd account");
            } else {
                account.withdraw();
            }
        }
    }
}