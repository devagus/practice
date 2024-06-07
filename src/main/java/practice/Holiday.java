package practice;


import java.time.LocalDate;

public class Holiday {
    private LocalDate date;

    public Holiday(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

}
