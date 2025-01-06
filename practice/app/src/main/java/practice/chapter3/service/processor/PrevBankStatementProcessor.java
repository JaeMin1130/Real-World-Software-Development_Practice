package practice.chapter3.service.processor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import practice.chapter3.domain.BankTransaction;

public class PrevBankStatementProcessor {
    private final List<BankTransaction> bankTransactions;
    
    public PrevBankStatementProcessor(final List<BankTransaction> bankTransactions){
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

    // 특정 월이나 금액으로 입출금 내역 검색하기
    public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for(BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDate().getMonth() != month || bankTransaction.getAmount() < amount) continue;
            result.add(bankTransaction);
        }
        
        return result;
    }

    // 개방/폐쇄 원칙을 적용한 후(추상화) 유연해진 findTransactions() 메서드
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransactionFilter.test(bankTransaction)){ // 필터의 파라미터를 메서드 실행할 때 결정
                result.add(bankTransaction);
            }
        }

        return result;
    }
    
}
