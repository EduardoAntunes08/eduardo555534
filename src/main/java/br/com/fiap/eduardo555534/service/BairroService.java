package br.com.fiap.eduardo555534.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.eduardo555534.domain.Bairro;
import br.com.fiap.eduardo555534.dto.BairroDTO;
import br.com.fiap.eduardo555534.exception.RecursoNaoEncontradoException;
import br.com.fiap.eduardo555534.exception.RegraNegocioException;
import br.com.fiap.eduardo555534.mapper.BairroMapper;
import br.com.fiap.eduardo555534.repository.BairroRepository;
import br.com.fiap.eduardo555534.repository.TrechoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;
    private final TrechoRepository trechoRepository;
    private final BairroMapper mapper;

    @Transactional(readOnly = true)
    public List<BairroDTO> listar() {
        return bairroRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public BairroDTO buscar(Long id) {
        return mapper.paraDTO(buscarEntidade(id));
    }

    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro;
        if (dto.getId() == null) {
            bairro = mapper.paraEntidade(dto);
        } else {
            bairro = buscarEntidade(dto.getId());
            mapper.copiarParaEntidade(dto, bairro);
        }
        return mapper.paraDTO(bairroRepository.save(bairro));
    }

    @Transactional
    public void excluir(Long id) {
        Bairro bairro = buscarEntidade(id);
        if (trechoRepository.existsByBairroId(id)) {
            throw new RegraNegocioException(
                    "O bairro " + bairro.getNome() + " não pode ser excluído porque possui trechos cadastrados.");
        }
        bairroRepository.delete(bairro);
    }

    @Transactional(readOnly = true)
    public Bairro buscarEntidade(Long id) {
        return bairroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Bairro " + id + " não encontrado."));
    }
}
