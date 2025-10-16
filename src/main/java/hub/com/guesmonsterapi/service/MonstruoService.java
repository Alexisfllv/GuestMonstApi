package hub.com.guesmonsterapi.service;

import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponse;
import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponseQuest;
import hub.com.guesmonsterapi.dto.Response.RespuestaDTORequest;
import hub.com.guesmonsterapi.dto.Response.ResultadoDTOResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MonstruoService {
    List<MonstruoDTOResponse> listAllMonstruo();

    MonstruoDTOResponse getMonstruoDTOResponse(Long id);
    MonstruoDTOResponse saveMonstruo(String name, MultipartFile fileSilueta, MultipartFile file) throws IOException;


    MonstruoDTOResponseQuest getRandomMonstruo();

    ResultadoDTOResponse confirmResponse(RespuestaDTORequest respuestaRequest);
//
//    Resource getMonstruoRealFindId(Long id);

}
