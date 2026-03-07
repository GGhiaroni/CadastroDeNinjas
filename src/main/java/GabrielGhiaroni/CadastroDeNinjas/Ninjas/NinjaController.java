package GabrielGhiaroni.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Boas vindas ao sistema!";
    }

    @GetMapping("/todos")
    public String mostraTodosOsNinjas(){
        return "Mostrar todos os ninjas.";
    }

    @GetMapping("/todos/id")
    public String mostrarNinjaPorId(){
        return "Mostrando ninja por ID.";
    }

    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado com sucesso.";
    }

    @PutMapping("/atualizar/id")
    public String atualizarNinja(){
        return "Ninja atualizado com sucesso.";
    }

    @DeleteMapping("/deletar/id")
    public String deletarNinja(){
        return "Ninja deletado com sucesso.";
    }


}

