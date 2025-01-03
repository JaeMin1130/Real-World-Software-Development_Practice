package practice.chapter2.service;

import java.time.Month;
import java.util.List;

import practice.chapter2.domain.BankTransaction;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;
    
    public BankStatementProcessor(final List<BankTransaction> bankTransactions){
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount(){
        double total = 0d;
        for(final BankTransaction bankTransaction : bankTransactions){
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month){
        double total = 0d;
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDate().getMonth() != month) continue;
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalForCategory(final String category){
        double total = 0d;
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDescription().equals(category)) continue;
            total += bankTransaction.getAmount();
        }
        return total;
    }
}
