package hub.com.guesmonsterapi.dto.Monster;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Petición para registrar un nuevo monstruo en el sistema")
public record MonstruoDTORequest(

        @Schema(description = "Nombre del monstruo", example = "Fantasma")
        String name,

        @Schema(description = "Nombre del archivo de la imagen silueta (almacenado en el servidor)", example = "bb81b67d-d979-4b6a-998e-6870b174bbb7.jpg")
        String imagenSilueta,

        @Schema(description = "Nombre del archivo de la imagen real (almacenado en el servidor)", example = "1b5d1286-732e-4465-b87e-3a424bc87ba4.jpg")
        String imagenReal
) {}
