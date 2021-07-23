package br.com.iteris.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    private String paymentId;

    private BigDecimal value;

    private Hotel hotel;

    private Car car;

    private Attraction attraction;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Hotel {

        private LocalDate begin;

        private LocalDate finish;

        private Long adults;

        private Long kids;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Car {

        private LocalDate begin;

        private LocalDate finish;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Attraction {

        private String name;

        private LocalDate date;

        private Long tickets;

    }

}
