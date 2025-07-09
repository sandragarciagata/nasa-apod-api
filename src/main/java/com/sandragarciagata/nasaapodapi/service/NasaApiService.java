package com.sandragarciagata.nasaapodapi.service;

import com.sandragarciagata.nasaapodapi.dto.ApodDto;
import com.sandragarciagata.nasaapodapi.dto.NasaApodResponseDto;
import com.sandragarciagata.nasaapodapi.model.ApodEntity;
import com.sandragarciagata.nasaapodapi.repository.ApodRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NasaApiService {

    private final RestTemplate restTemplate;
    private final ApodRepository apodRepository;

    @Value("${nasa.api.key}") // Se inyecta desde application.properties
    private String apiKey;

    public NasaApiService(ApodRepository apodRepository) {
        this.apodRepository = apodRepository;
        this.restTemplate = new RestTemplate();
    }

    public ApodDto getPictureOfTheDay() {
        // 1. Llamar a la API de la NASA
        String nasaApiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;
        NasaApodResponseDto nasaResponse = restTemplate.getForObject(nasaApiUrl, NasaApodResponseDto.class);

        if (nasaResponse == null) {
            throw new RuntimeException("No se pudo obtener la imagen de la NASA");
        }

        // 2. Guardar en base de datos
        ApodEntity entityToSave = new ApodEntity();
        entityToSave.setTitle(nasaResponse.getTitle());
        entityToSave.setExplanation(nasaResponse.getExplanation());
        entityToSave.setUrl(nasaResponse.getUrl());
        entityToSave.setMediaType(nasaResponse.getMediaType());
        apodRepository.save(entityToSave);

        // 3. Devolver el DTO final
        ApodDto finalDto = new ApodDto();
        finalDto.setNombre(nasaResponse.getTitle());
        finalDto.setDescripcion(nasaResponse.getExplanation());
        finalDto.setUrl(nasaResponse.getUrl());

        return finalDto;
    }

    public List<ApodDto> getArchivedPictures(int year, int month) {
        return apodRepository.findByYearAndMonth(year, month).stream()
                .map(entity -> {
                    ApodDto dto = new ApodDto();
                    dto.setNombre(entity.getTitle());
                    dto.setDescripcion(entity.getExplanation());
                    dto.setUrl(entity.getUrl());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
