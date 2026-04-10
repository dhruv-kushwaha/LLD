package Lesson01_SolidPrinciples.lsp;

import java.util.ArrayList;
import java.util.List;

interface NonWithdrawableAccount {
    void deposit();
}

interface WithdrawableAccount extends NonWithdrawableAccount {
    void withdraw();
}

class SavingsAccount implements WithdrawableAccount {
    @Override
    public void deposit() {
        System.out.println("Deposit to savings account");
    }

    @Override
    public void withdraw() {
        System.out.println("Withdraw from Savings account");
    }
}

class CurrentAccount implements WithdrawableAccount {
    @Override
    public void deposit() {
        System.out.println("Deposit to savings account");
    }

    @Override
    public void withdraw() {
        System.out.println("Withdraw from Savings account");
    }
}

class FDAccount implements NonWithdrawableAccount {
    @Override
    public void deposit() {
        System.out.println("Deposit to savings account");
    }
}

public class LspFollowed {
    public static void main(String[] args) {
        ArrayList<WithdrawableAccount> withdrawableAccounts = new ArrayList<>(
                List.of(new CurrentAccount(), new SavingsAccount()));

        ArrayList<NonWithdrawableAccount> nonWithdrawableAccounts = new ArrayList<>(
                List.of(new FDAccount()));

        for (WithdrawableAccount withdrawableAccount : withdrawableAccounts) {
            withdrawableAccount.deposit();
            withdrawableAccount.withdraw();
        }

        for (NonWithdrawableAccount nonwithdrawableAccount : nonWithdrawableAccounts) {
            nonwithdrawableAccount.deposit();
        }
    }
}
