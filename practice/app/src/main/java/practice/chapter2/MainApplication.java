package practice.chapter2;

import java.io.IOException;

import practice.chapter2.service.BankStatementAnalyzer;
import practice.chapter2.service.parser.BankStatementCSVParser;
import practice.chapter2.service.parser.BankStatementParser;

public class MainApplication {
    public static void main(final String... args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
