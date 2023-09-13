package com.moraes.gabriel.msraces.domain.Track;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String country;
}
