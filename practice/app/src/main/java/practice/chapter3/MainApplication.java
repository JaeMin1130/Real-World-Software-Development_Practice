package practice.chapter3;

import java.io.IOException;
import java.time.Month;
import java.util.List;

import practice.chapter3.domain.BankTransaction;
import practice.chapter3.service.BankStatementAnalyzer;
import practice.chapter3.service.parser.BankStatementCSVParser;
import practice.chapter3.service.parser.BankStatementParser;
import practice.chapter3.service.processor.BankStatementProcessor;
import practice.chapter3.service.processor.BankTransactionIsInFeburaryAndExpensive;

public class MainApplication {
    public static void main(final String... args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        final BankStatementProcessor bankStatementProcessor = bankStatementAnalyzer.analyze(args[0],
                bankStatementParser);

        // 특정 BankTransactionFilter 구현으로 findTransactions() 호출
        final List<BankTransaction> transactions1 = bankStatementProcessor
                .findTransactions(new BankTransactionIsInFeburaryAndExpensive());

        // 람다 표현식으로 BankTransactionFilter 구현하기
        final List<BankTransaction> transactions2 = bankStatementProcessor
                .findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == Month.FEBRUARY
                        && bankTransaction.getAmount() >= 1_000);
    }
}
