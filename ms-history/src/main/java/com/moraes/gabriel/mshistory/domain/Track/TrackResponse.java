package com.moraes.gabriel.mshistory.domain.Track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackResponse {

    private String id;
    private String name;
    private String country;
}
