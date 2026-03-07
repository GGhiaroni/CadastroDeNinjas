package GabrielGhiaroni.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Boas vindas ao sistema!";
    }

    @GetMapping("/listar")
    public String listarNinjas(){
        return "Mostrar todos os ninjas.";
    }

    @GetMapping("/id")
    public String mostrarNinja(){
        return "Ninja mostrado com sucesso.";
    }

    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado com sucesso.";
    }

    @PutMapping("atualizar/id")
    public String atualizarNinja(){
        return "Ninja atualizado com sucesso.";
    }

    @DeleteMapping("deletar/id")
    public String deletarNinja(){
        return "Ninja deletado com sucesso.";
    }


}

