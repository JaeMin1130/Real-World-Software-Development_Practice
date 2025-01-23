package practice.chapter4.importer;

import static practice.chapter4.Attributes.AMOUNT;
import static practice.chapter4.Attributes.PATIENT;
import static practice.chapter4.Attributes.TYPE;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import practice.chapter4.Document;

public class InvoiceImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";
    private static final String AMOUNT_PREFIX = "Amount: ";

    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");
        return new Document(attributes);
    }

}
