package com.sandragarciagata.nasaapodapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO para la respuesta de la NASA con los datos iguales a los
 * devueltos por la NASA para mapear su JSON de forma automática.
 */
@Data
public class NasaApodResponseDto {

    // Usamos @JsonProperty para mapear los nombres del JSON de la NASA
    // (guion bajo) a los nombres java (camelCase).

    private String title;
    private String explanation;
    private String url;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("service_version")
    private String serviceVersion;

    private String date;
    private String copyright;
}