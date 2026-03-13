package GabrielGhiaroni.CadastroDeNinjas.Missoes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }


    @Operation(summary = "Lista todas as missões cadastradas", description = "Rota lista todas as missões cadastradas.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Missões encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missões não encontradas.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoesListadas = missoesService.listarMissoes();
        return ResponseEntity.ok(missoesListadas);
    }


    @Operation(summary = "Lista a missão por ID", description = "Rota lista a missão pelo ID fornecido pelo usuário.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id){
        MissoesDTO missao = missoesService.listarMissaoPorId(id);
        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão de id " +
                    id + " não encontrada.");
        }
    }

    @Operation(summary = "Cria uma nova missão", description = "Rota cria uma nova missão e insere no banco de dados.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão.")
    })
    @PostMapping("/criar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missaoDTO){
        MissoesDTO missaoCriada = missoesService.criarMissao(missaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(missaoCriada);
    }

    @Operation(summary = "Altera a missão por ID.", description = "Rota altera a missão pelo ID fornecido pelo usuário.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMissao(
            @Parameter(description = "Usuário envia o ID da missão a ser atualizada no caminho da requisição.")
            @PathVariable Long id,
            @Parameter(description = "Usuário envia os dados da missão a ser atualizada no corpo da requisição.")
            @RequestBody MissoesDTO missaoAtualizada){
        MissoesDTO missao = missoesService.atualizarMissao(id, missaoAtualizada);
        if(missao != null){
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos uma missão com o id "
                    + id + ".");
        }

    }

    @Operation(summary = "Deleta a missão por ID.", description = "Rota deleta a missão pelo ID fornecido pelo usuário.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
       if( missoesService.listarMissaoPorId(id) != null){
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missão de id " + id + " deletada com sucesso.");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão de id " + id +
                   " não encontrada.");
       }
    }
}

