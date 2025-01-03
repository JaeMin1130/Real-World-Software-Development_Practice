package practice.chapter2.service.parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import practice.chapter2.domain.BankTransaction;

public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // CSV 파일 한 줄 => BankTranscation으로 parse
    @Override
    public BankTransaction parseFrom(final String line) {
        
        final String[] columns = line.split(",");
        
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];
        
        return new BankTransaction(date, amount, description);
    }
    
    // CSV 파일 전체 parse
    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines) {

        final List<BankTransaction> bankTransactions = new ArrayList<>();

        for(final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }
}
