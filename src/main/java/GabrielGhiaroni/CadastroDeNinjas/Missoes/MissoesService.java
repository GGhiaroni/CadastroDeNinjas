package GabrielGhiaroni.CadastroDeNinjas.Missoes;

import GabrielGhiaroni.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO listarMissaoPorId(Long id) {
        Optional<MissoesModel> missaoEncontrada = missoesRepository.findById(id);
        return missaoEncontrada.map(missoesMapper::map).orElse(null);
    }

    public MissoesDTO criarMissao(MissoesDTO missaoDTO) {
        MissoesModel missaoModel = missoesMapper.map(missaoDTO);
        missaoModel = missoesRepository.save(missaoModel);
        return missoesMapper.map(missaoModel);
    }

    public MissoesDTO atualizarMissao(Long id, MissoesDTO missaoDTO) {
        Optional<MissoesModel> missaoEncontrada = missoesRepository.findById(id);
        if (missaoEncontrada.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missaoDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        } else {
            return null;
        }
    }

    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }
}