package practice.chapter3.service.parser;

import java.util.List;

import practice.chapter3.domain.BankTransaction;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
