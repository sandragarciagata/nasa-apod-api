package com.sandragarciagata.nasaapodapi.dto;

/**
 * La clase que devuelve nuestra api
 */

import lombok.Data;

@Data
public class ApodDto {
    private String nombre;
    private String descripcion;
    private String url;
}