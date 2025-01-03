package practice.chapter2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import practice.chapter2.domain.BankTransaction;
import practice.chapter2.service.BankStatementProcessor;
import practice.chapter2.service.parser.BankStatementCSVParser;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";
    private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(final String... args) throws IOException {


        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor){
        System.out.printf("The total for all Transactions is %d.", bankStatementProcessor.calculateTotalAmount());
        System.out.printf("The total for all Transactions in January is %d.", bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.printf("The total salary received is %d.", bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
