import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import tpo.task_2.HashTableInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashTableIntegerTest {

    private HashTableInteger table;

    @BeforeEach
    void init() {
        table = new HashTableInteger(5);
        table.add_element(4);
        table.add_element(9);
        table.add_element(14);
        table.add_element(10);
    }

    @ParameterizedTest(name = "{index} - test insert command")
    @CsvSource({
            "4, '[[10][null][null][null][4,14,9,4]]'",
            "1, '[[10][1][null][null][14,9,4]]'"
    })
    public void testInsertNumbers(int number, String expectedArray) {
        table.add_element(number);
        assertEquals(expectedArray, table.getAsString());
    }

    @ParameterizedTest(name = "{index} - test delete command")
    @CsvFileSource(resources = "/delete_values.csv", numLinesToSkip = 1, delimiter = ';')
    public void testDeleteNumbers(int number, boolean result, String expectedArray) {
        boolean cur_result = table.delete_element(number);
        assertEquals(result, cur_result);
        assertEquals(expectedArray, table.getAsString());
    }

    @ParameterizedTest(name = "{index} - test find command")
    @CsvFileSource(resources = "/find_values.csv", numLinesToSkip = 1, delimiter = ';')
    public void testFindNumbers(int number, boolean result, String expectedArray) {
        boolean cur_result = table.find_element(number);
        assertEquals(result, cur_result);
        assertEquals(expectedArray, table.getAsString());
    }

    @ParameterizedTest(name = "{index} - test insert command")
    @CsvSource({
            "4, '[[10][null][null][null][4,4,14,9,4]]'",
    })
    public void testInsertSameNumbers(int number, String expectedArray) {
        table.add_element(number);
        table.add_element(number);
        assertEquals(expectedArray, table.getAsString());
    }

    @ParameterizedTest(name = "{index} - test insert command")
    @CsvSource({
            "'[[10][null][2147483647][null][14,9,4]]'",
    })
    public void testInsertMaxIntNumber(String expectedArray) {
        table.add_element(Integer.MAX_VALUE);
        assertEquals(expectedArray, table.getAsString());
    }

    @ParameterizedTest(name = "{index} - test delete command")
    @CsvSource({
            "4, '[[10][null][null][null][4,14,9,4]]'",
    })
    public void testDeleteSameNumbers(int number, String expectedArray) {
        table.add_element(number);
        table.add_element(number);
        table.delete_element(number);
        assertEquals(expectedArray, table.getAsString());
    }

    @Test
    @DisplayName("Check not being able to add negative values")
    void testAddingNegativeNumbers() {
        Throwable exception = assertThrows(Exception.class, () ->  table.add_element(-5));
        assertEquals("Числа должны быть положительными!", exception.getMessage());
    }

}
