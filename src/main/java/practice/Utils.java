package practice;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Utils {
    /**
     * @param laborDays The days that are workable, that is, we can work from Monday to Friday, from Monday to Saturday, or the entire week, or only 3 days a week
     * @param holidays  All holidays that will be relevant for the calculation, take December into account for this, you may need the holidays of the following year
     * @param startDate Start date to calculate the vacation, this date is inclusive, that is, the vacation starts from here
     * @param vacation  The number of days to vacation
     * @return The date the worker must return
     * @implNote The last day of vacation also counts, that is, you return the next working day
     */
    public LocalDate calculateDueDate(List<DayOfWeek> laborDays, List<Holiday> holidays, LocalDate startDate, int vacation) {
        LocalDate result = startDate; // Its immutable data type
        int vactions = 0;
        while (true) {
            vactions += getIsLaborDay(laborDays, holidays, result);
            if (vactions <= vacation) {
                result = result.plusDays(1);
            } else {
                break;
            }
        }
        return result;
    }

    private int getIsLaborDay(List<DayOfWeek> laborDays, List<Holiday> holidays, LocalDate dateToFind) {
        if (holidays.stream().anyMatch(holiday -> holiday.getDate().isEqual(dateToFind))) {
            return 0;
        }
        return laborDays.contains(dateToFind.getDayOfWeek()) ? 1 : 0;
    }

    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";
    public static final String FIZZBUZZ = FIZZ + BUZZ;

    /**
     * A number is Fizz when it is divisible by 5
     * A number is Buzz when it is divisible by 3
     * A number is FizzBuzz when it is divisible between 3 and 5 at the same time
     * If it does not meet any previous condition, the number is returned
     *
     * @param number number to check to see if it is Fizz, Buzz or FizzBuzz
     * @return A string that is equal to Fizz, Buzz, or FizzBuzz
     */
    public String fizzbuzz(Integer number) {
        if (number == null) {
            throw new NullPointerException("Number cant be null");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (number % 5 == 0) {
            stringBuilder.append(FIZZ);
        }
        if (number % 3 == 0) {
            stringBuilder.append(BUZZ);
        }
        if (stringBuilder.isEmpty()) {
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }
}
