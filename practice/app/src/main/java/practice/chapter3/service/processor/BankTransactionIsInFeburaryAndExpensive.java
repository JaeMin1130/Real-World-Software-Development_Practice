package practice.chapter3.service.processor;

import java.time.Month;

import practice.chapter3.domain.BankTransaction;

// BankTransactionFilter를 구현하는 클래스 선언
public class BankTransactionIsInFeburaryAndExpensive implements BankTransactionFilter {

    @Override
    public boolean test(final BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY
                && bankTransaction.getAmount() >= 1_000;
    }
}
