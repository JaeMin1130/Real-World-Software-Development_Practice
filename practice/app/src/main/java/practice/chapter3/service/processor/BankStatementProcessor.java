package practice.chapter3.service.processor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import practice.chapter3.domain.BankTransaction;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    // 암묵적 API
    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }

        return result;
    }

    // 명시적 API: 가장 흔히 사용하는 연산이라면, 사용자가 쉽게 이해하고 사용하도록 명시적 API로 만든다.
    public double calculateTotalInMonth(Month month) {
        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month
                ? acc + bankTransaction.getAmount()
                : acc);
    }

    // 암묵적 API
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) { // 필터의 파라미터를 메서드 실행할 때 결정
                result.add(bankTransaction);
            }
        }

        return result;
    }

    // 명시적 API
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }

    // 스트림 API를 사용하면 수많은 연산 패턴을 쉽게 구현할 수 있다.
    // public List<BankTransaction> findTransactionsGreaterThanEqualUsingStream(final int amount) {
    //     return bankTransactions
    //             .stream()
    //             .filter(bankTransaction -> bankTransaction.getAmount() >= amount)
    //             .collect(toList());
    // }
}
