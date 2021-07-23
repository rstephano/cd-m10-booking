package br.com.iteris.booking.integrations.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attraction {

    private String paymentId;

    private String name;

    private LocalDate date;

    private Long tickets;

}
