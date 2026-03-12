package GabrielGhiaroni.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Boas vindas ao sistema!";
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> listaDeNinjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(listaDeNinjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id){
        NinjaDTO ninjaEncontrado = ninjaService.listarNinjaPorId(id);
        if(ninjaEncontrado != null) {
            return ResponseEntity.ok(ninjaEncontrado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja de id " +
                    id + " não encontrado.");
        }
    };

    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO){
        NinjaDTO ninjaCriado = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso! Nome: " + ninjaCriado.getNome() + "| ID: " + ninjaCriado.getId() + ".");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum ninja de id " + id + " foi encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id){
        if(ninjaService.listarNinjaPorId(id) != null){
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja de id " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id " + id + " não encontrado.");
        }
    }
}

