package hub.com.guesmonsterapi.controller;

import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponse;
import hub.com.guesmonsterapi.dto.Monster.MonstruoDTOResponseQuest;
import hub.com.guesmonsterapi.dto.Response.RespuestaDTORequest;
import hub.com.guesmonsterapi.dto.Response.ResultadoDTOResponse;
import hub.com.guesmonsterapi.service.MonstruoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mons")
public class MonstruoController {

    private final MonstruoService monstruoService;


    // list

    @GetMapping("/list")
    ResponseEntity<List<MonstruoDTOResponse>> listAllMonstruos() {
        return ResponseEntity.ok(monstruoService.listAllMonstruo());
    }

    // findbyId
    @GetMapping("/find/{id}")
    ResponseEntity<MonstruoDTOResponse> findMonstruoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(monstruoService.getMonstruoDTOResponse(id));
    }


    // save
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MonstruoDTOResponse> createMonstruo(
            @RequestParam("name") String name,
            @RequestParam("fileSilueta") MultipartFile fileSilueta,
            @RequestParam("file") MultipartFile file) throws IOException {

        MonstruoDTOResponse response = monstruoService.saveMonstruo(name, fileSilueta, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // generate random
    @GetMapping("/random")
    public ResponseEntity<MonstruoDTOResponseQuest> randomMonstruo() {
        return ResponseEntity.ok(monstruoService.getRandomMonstruo());
    }

    // confirm
    @PostMapping("/verificar")
    public ResponseEntity<ResultadoDTOResponse> verificar(@RequestBody RespuestaDTORequest responseDto) {
        var res = monstruoService.confirmResponse(responseDto);
        return ResponseEntity.ok(res);
    }

}
