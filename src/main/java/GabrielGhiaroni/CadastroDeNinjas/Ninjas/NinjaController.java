package GabrielGhiaroni.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

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
    public List<NinjaDTO> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjaPorId(id);
    };

    @GetMapping("/id")
    public String mostrarNinja(){
        return "Ninja mostrado com sucesso.";
    }

    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaDTO){
        return ninjaService.criarNinja(ninjaDTO);
    }

    @PutMapping("/atualizar/{id}")
    public NinjaDTO atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);
    }
}

