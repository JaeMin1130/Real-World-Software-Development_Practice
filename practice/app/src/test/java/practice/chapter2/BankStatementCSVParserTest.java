package practice.chapter2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import practice.chapter2.domain.BankTransaction;
import practice.chapter2.service.parser.BankStatementCSVParser;
import practice.chapter2.service.parser.BankStatementParser;


// 테스트 클래스명에는 "Test"라는 접미어를 붙이는 것이 관습이다.
public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    // @Test를 테스트 메서드에 추가한다. 이 애너테이션으로 해당 메서드가 유닛테스트의 실행 대상임을 지정한다.
    @Test
    // 테스트 메서드의 구현 코드를 보지 않고도 무엇을 테스트하는지 쉽게 알 수 있도록 서술적인 이름을 붙이는 것이 좋다.
    public void shouldParseOneCorrectLine() throws IOException {
        // Given: 테스트에 사용할 context를 설정한다. => 파싱할 행을 설정
        final String line = "30-01-2017,-50,Tesco";

        // When: 동작을 실행한다. => 입력 행을 파싱
        final BankTransaction result = statementParser.parseFrom(line);

        // Then: 예상된 결과를 Assert로 지정한다. => 날짜, 금액, 설명이 제대로 파싱되었는지 확인
        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
        // Assert.fail("Not yet implemented");
    }
}
