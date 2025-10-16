package hub.com.guesmonsterapi.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponse;
import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponseQuest;
import hub.com.guesmonsterapi.dto.Response.RespuestaDTORequest;
import hub.com.guesmonsterapi.dto.Response.ResultadoDTOResponse;
import hub.com.guesmonsterapi.mapper.MonsterMapper;
import hub.com.guesmonsterapi.model.Monster;
import hub.com.guesmonsterapi.repo.MonsterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MonsterServiceImpl implements MonstruoService{

    private final MonsterRepo monsterRepo;
    private final Cloudinary cloudinary;
    private final MonsterMapper monsterMapper;


    @Override
    public List<MonstruoDTOResponse> listAllMonstruo() {
        var list = monsterRepo.findAll();
        return list.stream()
                .map(m -> monsterMapper.toMonstruoDTOResponse(m))
                .toList();
    }

    @Override
    public MonstruoDTOResponse getMonstruoDTOResponse(Long id) {
        Monster monster = monsterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Monster not found!"));
        return monsterMapper.toMonstruoDTOResponse(monster);
    }

    @Override
    public MonstruoDTOResponse saveMonstruo(String name, MultipartFile fileSilueta, MultipartFile file) throws IOException {
        try {
            // Validaciones simples (optional)
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("El nombre es requerido");
            }

            // Subir silueta
            String siluetaUrl = null;
            if (fileSilueta != null && !fileSilueta.isEmpty()) {
                Map<?, ?> resSilueta = cloudinary.uploader()
                        .upload(fileSilueta.getBytes(), ObjectUtils.emptyMap());
                siluetaUrl = resSilueta.get("secure_url").toString();
            }

            // Subir imagen real
            String realUrl = null;
            if (file != null && !file.isEmpty()) {
                Map<?, ?> resReal = cloudinary.uploader()
                        .upload(file.getBytes(), ObjectUtils.emptyMap());
                realUrl = resReal.get("secure_url").toString();
            }

            // Crear entidad y guardar
            Monster monster = new Monster();
            monster.setName(name);
            monster.setImagenSilueta(siluetaUrl);
            monster.setImagenReal(realUrl);

            Monster saved = monsterRepo.save(monster);

            // Devolver DTO (record)
            return  monsterMapper.toMonstruoDTOResponse(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear Monstruo: " + e.getMessage(), e);
        }
    }

    // generate random

    @Override
    public MonstruoDTOResponseQuest getRandomMonstruo() {
        Monster rndmonstruo = monsterRepo.findRandom();

        List<String> nombre = monsterRepo.findAll()
                .stream()
                .map(Monster::getName)
                .filter(n -> !n.equals(rndmonstruo.getName()))
                .collect(Collectors.toList());

        // generar opciones 1 + 3
        Collections.shuffle(nombre);
        List<String> opciones = new ArrayList<>(nombre.stream().limit(3).toList());
        opciones.add(rndmonstruo.getName());
        Collections.shuffle(opciones);

        // recuperar imagen
        String imagenSilueta = monsterRepo.findImagenSiluetaById(rndmonstruo.getId());

        return new MonstruoDTOResponseQuest(
                rndmonstruo.getId(),
                imagenSilueta,
                opciones
        );
    }

    @Override
    public ResultadoDTOResponse confirmResponse(RespuestaDTORequest req) {

        Monster monster =  monsterRepo.findById(req.id())
                .orElseThrow(() -> new RuntimeException("Monster not found!"));

        // match
        boolean respuesta = monster.getName().equalsIgnoreCase(req.respuestaSeleccionada());

        // recuperar imagenReal
        String imagenReal = monsterRepo.findImagenRealById(monster.getId());

        return new ResultadoDTOResponse(
                respuesta,
                respuesta ? "¡Correcto! Era " + monster.getName()
                        : "Fallaste, era " + monster.getName(),
                imagenReal
        );
    }


}
