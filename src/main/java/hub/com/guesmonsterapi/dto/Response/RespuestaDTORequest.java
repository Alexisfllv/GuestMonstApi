package hub.com.guesmonsterapi.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Petición para verificar la respuesta seleccionada por el usuario")
public record RespuestaDTORequest(

        @Schema(description = "ID del monstruo mostrado", example = "3")
        Long id,

        @Schema(description = "Nombre del monstruo seleccionado por el usuario", example = "Fantasma")
        String respuestaSeleccionada
) {
}
