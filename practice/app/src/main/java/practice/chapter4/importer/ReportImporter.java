package practice.chapter4.importer;

import static practice.chapter4.Attributes.BODY;
import static practice.chapter4.Attributes.PATIENT;
import static practice.chapter4.Attributes.TYPE;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import practice.chapter4.Document;

public class ReportImporter implements Importer{
    private static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line -> false, BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }


}
