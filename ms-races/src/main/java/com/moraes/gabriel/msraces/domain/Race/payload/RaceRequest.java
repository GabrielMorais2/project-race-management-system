package com.moraes.gabriel.msraces.domain.Race.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moraes.gabriel.msraces.domain.Race.RaceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceRequest {

    @NotBlank
    @Pattern(regexp = "^(?!\\s)[\\p{L}\\d]+(?:[\\s-][\\p{L}\\d]+)*$", message = "model should only contain letters, numbers, and spaces")
    private String name;
    @NotNull
    private String idTrack;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private RaceStatus status;
}
