package hub.com.guesmonsterapi.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resultado de la verificación de la respuesta del usuario")
public record ResultadoDTOResponse(
        @Schema(description = "Indica si la respuesta fue correcta o no", example = "true")
        boolean acierto,

        @Schema(description = "Mensaje con el resultado de la respuesta", example = "¡Correcto! Era Fantasma.")
        String mensaje,

        @Schema(description = "URL de la imagen real del monstruo revelada tras responder", example = "http://localhost:8080/v1/imagen-real/3")
        String imagenReal
) {
}
