package GabrielGhiaroni.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listarMissoes(){
        return "Missões listadas com sucesso.";
    }

    @GetMapping("/id")
    public String mostrarMissao(){
        return "Missão mostrada com sucesso.";
    }

    @PostMapping("/criar")
    public String criarMissao(){
        return "Missão criada com sucesso.";
    }

    @PutMapping("atualizar/id")
    public String atualizarMissao(){
        return "Missão alterada com sucesso.";
    }

    @DeleteMapping("deletar/id")
    public String deletarMissao(){
        return "Missão deletada com sucesso.";
    }
}

