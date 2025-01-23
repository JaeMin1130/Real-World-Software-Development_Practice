package practice.chapter4.importer;

import static java.util.stream.Collectors.toList;
import static practice.chapter4.Attributes.PATH;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;

    TextFile(final File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }

    Map<String, String> getAttributes() {
        return attributes;
    }

    int addLines(
            final int start, final Predicate<String> isEnd, final String attributeName) {
        final StringBuilder accumulator = new StringBuilder(); // 누적할 문자열을 저장할 StringBuilder 객체를 생성합니다.
        int lineNumber;
        for (lineNumber = start; lineNumber < lines.size(); lineNumber++) { // start 부터 목록의 끝까지 루프를 돌립니다.
            final String line = lines.get(lineNumber); // 현재 줄을 가져옵니다.
            if (isEnd.test(line)) break; // isEnd 조건이 true이면 루프를 종료합니다.

            accumulator.append(line); // 누적기에 현재 줄을 추가합니다.
            accumulator.append("\n"); // 줄바꿈 문자를 추가합니다.
        }
        attributes.put(attributeName, accumulator.toString().trim()); // 누적된 문자열을 속성 맵에 추가합니다.
        return lineNumber; // 현재 줄 번호를 반환합니다.
    }


    void addLineSuffix(final String prefix, final String attributeNames) {
        for (final String line : lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeNames, line.substring(prefix.length()));
                break;
            }
        }
    }
}
