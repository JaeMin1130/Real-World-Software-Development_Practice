package practice.chapter2.service.parser;

import java.util.List;

import practice.chapter2.domain.BankTransaction;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
