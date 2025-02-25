package practice.chapter3.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import practice.chapter3.domain.BankTransaction;
import practice.chapter3.service.parser.BankStatementParser;
import practice.chapter3.service.processor.BankStatementProcessor;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public BankStatementProcessor analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException{
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        return bankStatementProcessor;
    }
}
