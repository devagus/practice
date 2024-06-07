package practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private Utils utils;

    private List<DayOfWeek> laborDays;

    private List<Holiday> holidays;


    @BeforeEach
    public void setUp() {
        utils = new Utils();
        laborDays = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
        Holiday juneHoliday = new Holiday(LocalDate.of(2024, 6, 17));
        Holiday julyHoliday = new Holiday(LocalDate.of(2024, 6, 21));
        holidays = List.of(juneHoliday, julyHoliday);
    }

    @Test
    void calculateDueDate_whenVacationIsZero_andStartDateItsLaborDay() {
        LocalDate startDate = LocalDate.of(2024, 6, 5); // weekday
        int vacation = 0;

        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);
        assertEquals(startDate, result);
    }

    @Test
    void calculateDueDate_whenVacationIsZero_andStartDateIsNotLaborDay() {
        LocalDate startDate = LocalDate.of(2024, 6, 8); // saturday
        int vacation = 0;

        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);

        LocalDate expectedDueDate = LocalDate.of(2024, 6, 10); // monday
        assertEquals(expectedDueDate, result, "The worker must return the next working day");
    }

    @Test
    void calculateDueDate_whenVacationIsOne_andStartDateIsLaborDay() {
        LocalDate startDate = LocalDate.of(2024, 6, 5); // wednesday
        int vacation = 1;

        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);

        LocalDate expectedDueDate = LocalDate.of(2024, 6, 6); // tuesday
        assertEquals(expectedDueDate, result, "The worker must return the next working day");
    }

    @Test
    void calculateDueDate_whenVacationIsOne_andStartDateIsTheLastLaborDay_IncludeStartDay() {
        LocalDate startDate = LocalDate.of(2024, 6, 7); // friday
        int vacation = 1;

        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);

        LocalDate expectedDueDate = LocalDate.of(2024, 6, 10); // monday
        assertEquals(expectedDueDate, result);
    }

    @Test
    void calculateDueDate_whenVacationIsOne_andStartDateIsNotLaborDay() {
        LocalDate startDate = LocalDate.of(2024, 6, 8); // saturday
        int vacation = 1;

        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);

        LocalDate expectedDueDate = LocalDate.of(2024, 6, 12); // tuesday
        assertEquals(expectedDueDate, result);
    }

    @Test
    void calculateDueDate_whenThereIsHoliday() {
        LocalDate startDate = LocalDate.of(2024, 6, 14); // friday
        int vacation = 2;

        // june 17 is a holiday
        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);

        LocalDate expectedDueDate = LocalDate.of(2024, 6, 19); // wednesday
        assertEquals(expectedDueDate, result);
    }

    @Test
    void calculateDueDate_whenThereAreMoreThanOneHolidays() {
        LocalDate startDate = LocalDate.of(2024, 6, 14); // friday
        int vacation = 5;

        // june 17 and june 21 are holidays
        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);

        LocalDate expectedDueDate = LocalDate.of(2024, 6, 25); // tuesday
        assertEquals(expectedDueDate, result);
    }

    @Test
    void calculateDueDate_whenVacationIsNegative_returnStartDate() {
        LocalDate startDate = LocalDate.of(2024, 6, 14); // friday
        int vacation = -4;

        LocalDate result = utils.calculateDueDate(laborDays, holidays, startDate, vacation);
        assertEquals(startDate, result);
    }

    @Test
    void calculateDueDate_startDateIsNull_WillThrowException() {
        assertThrows(NullPointerException.class, () -> utils.calculateDueDate(laborDays, holidays, null, 5));
    }


    @Test
    void fizzbuzz_isDivisibleBy5() {
        String result = utils.fizzbuzz(20);
        assertEquals(Utils.FIZZ, result);
    }

    @Test
    void fizzbuzz_isDivisibleBy3() {
        String result = utils.fizzbuzz(9);
        assertEquals(Utils.BUZZ, result);
    }

    @Test
    void fizzbuzz_isDivisibleBy3And5() {
        String result = utils.fizzbuzz(30);
        assertEquals(Utils.FIZZBUZZ, result);
    }

    @Test
    void fizzbuzz_isNotDivisibleBy3Or5_returnNumber() {
        String result = utils.fizzbuzz(7);
        assertEquals("7", result);
    }

    @Test
    void fizzbuzz_isNotDivisibleBy3Or5_returnNumber_testWithLowNumber() {
        String result = utils.fizzbuzz(2);
        assertEquals("2", result);
    }

    @Test
    void fizzbuzz_nullNumber_WillThrowException() {
        assertThrows(NullPointerException.class, () -> utils.fizzbuzz(null));
    }


    @Test
    void fizzbuzz_whenNumberIsZero_SucceedWithFizzBuzz() {
        String result = utils.fizzbuzz(0);
        assertEquals(Utils.FIZZBUZZ, result);
    }
}