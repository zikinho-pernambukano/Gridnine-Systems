package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        LocalDateTime currentTime = LocalDateTime.now(); // переменная указывает на текущее время
        System.out.println("Вылет до текущего момента времени");
        for (Flight flight : flights) {

            if (flight.getSegments().get(0).getDepartureDate().isBefore(currentTime)) {
                System.out.println(flight);
            }

        }
        System.out.println("имеются сегменты с датой прилета раньше даты вылета");
        for (Flight flight : flights) {
// взяли у полета список сегментов , у каждого сегмента будем проверять время вылета и прилета
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size(); i++) {


                if (segments.get(i).getDepartureDate().isAfter(segments.get(i).getArrivalDate())) {
                    System.out.println(flight);
                    break;
                }
            }
        }
        System.out.println("общее время на земле превышает 2 часа");
        for (Flight flight : flights) {
// проходимся по сегментам и считаем время на земле
            List<Segment> segments = flight.getSegments();
            Duration duration = Duration.ZERO;

            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime arrivalDate = segments.get(i).getArrivalDate();
                LocalDateTime departureDate = segments.get(i + 1).getDepartureDate();
                Duration duration1 = Duration.between(arrivalDate, departureDate);
                duration = duration.plus(duration1);

            }
            Duration twoHours = Duration.ofHours(2);
            if (duration.compareTo(twoHours) > 0) {
                System.out.println(flight);
            }
        }
    }


}

