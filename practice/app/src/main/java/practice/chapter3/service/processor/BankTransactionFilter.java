package practice.chapter3.service.processor;

import practice.chapter3.domain.BankTransaction;

// 한 개의 추상 메서드를 포함하는 인터페이스를 함수형 인터페이스라 부른다.
@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction BankTransaction);
}