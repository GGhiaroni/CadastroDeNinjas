package GabrielGhiaroni.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    private final MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    public MissoesModel listarMissaoPorId(Long id) {
        Optional<MissoesModel> missaoEncontrada = missoesRepository.findById(id);
        return missaoEncontrada.orElse(null);
    }

    public MissoesModel criarMissao(MissoesModel missao) {
        return missoesRepository.save(missao);
    }

    public MissoesModel atualizarMissao(Long id, MissoesModel missao) {
        Optional<MissoesModel> missaoEncontrada = missoesRepository.findById(id);
        if (missaoEncontrada.isPresent()) {
            missao.setId(id);
            return missoesRepository.save(missao);
        } else {
            return null;
        }
    }

    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }
}