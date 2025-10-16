package hub.com.guesmonsterapi.dto.Monster;

import java.util.List;

public record MonstruoDTOResponseQuest(
        Long id,
        String imagenSilueta,
        List<String> opciones
) {}