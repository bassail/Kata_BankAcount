package service;

import java.time.LocalDate;

public class DateServiceImpl implements DateService{

    private final LocalDate date;


    private static final DateServiceImpl dateService = new DateServiceImpl();

    public static DateService getInstance() {
        return dateService;
    }

    private DateServiceImpl() {
        this.date = LocalDate.now();
    }

    @Override
    public LocalDate getDate() {
        return date;
    }
}
