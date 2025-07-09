package com.sandragarciagata.nasaapodapi;

import com.sandragarciagata.nasaapodapi.dto.ApodDto;
import com.sandragarciagata.nasaapodapi.dto.NasaApodResponseDto;
import com.sandragarciagata.nasaapodapi.model.ApodEntity;
import com.sandragarciagata.nasaapodapi.repository.ApodRepository;
import com.sandragarciagata.nasaapodapi.service.NasaApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NasaApiServiceTest {

    // Crear mocks de las dependencias externas
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApodRepository apodRepository;

    // Inyectar mocks en el servicio
    @InjectMocks
    private NasaApiService nasaApiService;

    // Antes de cada test, se inyecta una clave de API falsa
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(nasaApiService, "apiKey", "test-key");
        // Reiniciar el restTemplate en el servicio para que use nuestro mock
        ReflectionTestUtils.setField(nasaApiService, "restTemplate", restTemplate);
    }

    @Test
    void getPictureOfTheDay_DeberiaLlamarANasaYDevolverDto() {
        // --- Preparación (Arrange) ---
        // 1. Crear una respuesta falsa de la NASA
        NasaApodResponseDto nasaResponse = new NasaApodResponseDto();
        nasaResponse.setTitle("Supernova Remnant");
        nasaResponse.setExplanation("An explanation of the supernova.");
        nasaResponse.setUrl("https://example.com/image.jpg");

        // 2. Decirle al RestTemplate simulado que, cuando se le llame, devuelva la respuesta falsa
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(nasaResponse);

        // 3. El repositorio simulado guarda y devuelve la entidad
        when(apodRepository.save(any(ApodEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // --- Ejecución (Act) ---
        ApodDto resultado = nasaApiService.getPictureOfTheDay();

        // --- Verificación (Assert) ---
        assertNotNull(resultado);
        assertEquals("Supernova Remnant", resultado.getNombre());
        assertEquals("https://example.com/image.jpg", resultado.getUrl());
    }

    @Test
    void getArchivedPictures_DeberiaDevolverListaDeDtos() {
        // --- Preparación (Arrange) ---
        ApodEntity entity1 = new ApodEntity();
        entity1.setTitle("Galaxy One");
        ApodEntity entity2 = new ApodEntity();
        entity2.setTitle("Nebula Two");

        // Decir al repositorio que devuelva una lista con las dos entidades falsas
        when(apodRepository.findByYearAndMonth(2025, 7)).thenReturn(List.of(entity1, entity2));

        // --- Ejecución (Act) ---
        List<ApodDto> resultado = nasaApiService.getArchivedPictures(2025, 7);

        // --- Verificación (Assert) ---
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Galaxy One", resultado.get(0).getNombre());
    }
}