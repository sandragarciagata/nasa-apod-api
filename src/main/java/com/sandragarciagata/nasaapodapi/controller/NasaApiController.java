package com.sandragarciagata.nasaapodapi.controller;

import com.sandragarciagata.nasaapodapi.dto.ApodDto;
import com.sandragarciagata.nasaapodapi.service.NasaApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nasa")
public class NasaApiController {

    private final NasaApiService nasaApiService;

    public NasaApiController(NasaApiService nasaApiService) {
        this.nasaApiService = nasaApiService;
    }

    @GetMapping("/apod")
    public ResponseEntity<ApodDto> getPictureOfTheDay() {
        ApodDto apodDto = nasaApiService.getPictureOfTheDay();
        return ResponseEntity.ok(apodDto);
    }

    @GetMapping("/apod/archive")
    public ResponseEntity<List<ApodDto>> getArchivedPictures(
            @RequestParam int year,
            @RequestParam int month) {
        List<ApodDto> archivedPictures = nasaApiService.getArchivedPictures(year, month);
        return ResponseEntity.ok(archivedPictures);
    }
}