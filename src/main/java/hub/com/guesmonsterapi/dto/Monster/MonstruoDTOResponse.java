package hub.com.guesmonsterapi.dto.Monster;

public record MonstruoDTOResponse(
        Long id,
        String name,
        String imagenSilueta,
        String imagenReal
) { }