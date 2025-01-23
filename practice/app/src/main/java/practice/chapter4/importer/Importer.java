package practice.chapter4.importer;

import java.io.File;
import java.io.IOException;

import practice.chapter4.Document;

public interface Importer {
    Document importFile(File file) throws IOException;
}
