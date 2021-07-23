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
public class Hotel {

    private String paymentId;

    private LocalDate begin;

    private LocalDate finish;

    private Long adults;

    private Long kids;

}
