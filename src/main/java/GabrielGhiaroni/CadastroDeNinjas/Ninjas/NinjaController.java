package GabrielGhiaroni.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota dá uma mensagem de boas vindas ao usuário.")
    public String boasVindas(){
        return "Boas vindas ao sistema!";
    }

    @Operation(summary = "Lista todos os ninjas cadastrados.", description = "Rota lista todos os ninjas cadastrados.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Ninjas encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninjas não encontrados.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> listaDeNinjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(listaDeNinjas);
    }

    @Operation(summary = "Lista o ninja por ID.", description = "Rota lista o ninja pelo ID fornecido pelo usuário.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
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
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja.")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO){
        NinjaDTO ninjaCriado = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso! Nome: " + ninjaCriado.getNome() + "| ID: " + ninjaCriado.getId() + ".");
    }

    @Operation(summary = "Altera o ninja por ID.", description = "Rota altera o ninja pelo ID fornecido pelo usuário.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuário envia o ID do ninja a ser atualizado no caminho da requisição.")
            @PathVariable Long id,
            @Parameter(description = "Usuário envia os dados do ninja a ser atualizado no corpo da requisição.")
            @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum ninja de id " + id + " foi encontrado.");
        }
    }

    @Operation(summary = "Deleta o ninja por ID.", description = "Rota deleta o ninja pelo ID fornecido pelo usuário.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
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

