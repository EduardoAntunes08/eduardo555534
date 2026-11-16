package br.com.fiap.eduardo555534.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.eduardo555534.domain.Trecho;
import br.com.fiap.eduardo555534.dto.TrechoDTO;
import br.com.fiap.eduardo555534.exception.RecursoNaoEncontradoException;
import br.com.fiap.eduardo555534.exception.RegraNegocioException;
import br.com.fiap.eduardo555534.mapper.TrechoMapper;
import br.com.fiap.eduardo555534.repository.LeituraRepository;
import br.com.fiap.eduardo555534.repository.TrechoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrechoService {

    private final TrechoRepository trechoRepository;
    private final LeituraRepository leituraRepository;
    private final BairroService bairroService;
    private final TrechoMapper mapper;

    @Transactional(readOnly = true)
    public List<TrechoDTO> listar() {
        return trechoRepository.findAllByOrderByLogradouroAsc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TrechoDTO buscar(Long id) {
        return mapper.paraDTO(buscarEntidade(id));
    }

    @Transactional
    public TrechoDTO salvar(TrechoDTO dto) {
        Trecho trecho;
        if (dto.getId() == null) {
            trecho = mapper.paraEntidade(dto);
        } else {
            trecho = buscarEntidade(dto.getId());
            mapper.copiarParaEntidade(dto, trecho);
        }
        trecho.setBairro(bairroService.buscarEntidade(dto.getBairroId()));
        return mapper.paraDTO(trechoRepository.save(trecho));
    }

    @Transactional
    public void excluir(Long id) {
        Trecho trecho = buscarEntidade(id);
        if (leituraRepository.existsByTrechoId(id)) {
            throw new RegraNegocioException(
                    "O trecho " + trecho.getLogradouro() + " não pode ser excluído porque possui leituras cadastradas.");
        }
        trechoRepository.delete(trecho);
    }

    @Transactional(readOnly = true)
    public Trecho buscarEntidade(Long id) {
        return trechoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Trecho " + id + " não encontrado."));
    }
}
