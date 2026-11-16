package br.com.fiap.eduardo555534.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.eduardo555534.domain.Leitura;
import br.com.fiap.eduardo555534.dto.LeituraDTO;
import br.com.fiap.eduardo555534.exception.RecursoNaoEncontradoException;
import br.com.fiap.eduardo555534.mapper.LeituraMapper;
import br.com.fiap.eduardo555534.repository.LeituraRepository;

@Service
public class LeituraService {

    private final LeituraRepository leituraRepository;
    private final TrechoService trechoService;
    private final LeituraMapper mapper;

    private final int limiarAlerta;

    public LeituraService(LeituraRepository leituraRepository,
                          TrechoService trechoService,
                          LeituraMapper mapper,
                          @Value("${app.limiar-alerta}") int limiarAlerta) {
        this.leituraRepository = leituraRepository;
        this.trechoService = trechoService;
        this.mapper = mapper;
        this.limiarAlerta = limiarAlerta;
    }

    @Transactional(readOnly = true)
    public List<LeituraDTO> listar() {
        return leituraRepository.findAllByOrderByDataHoraDesc()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LeituraDTO buscar(Long id) {
        return mapper.paraDTO(buscarEntidade(id));
    }

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        Leitura leitura;
        if (dto.getId() == null) {
            leitura = mapper.paraEntidade(dto);
        } else {
            leitura = buscarEntidade(dto.getId());
            mapper.copiarParaEntidade(dto, leitura);
        }
        leitura.setTrecho(trechoService.buscarEntidade(dto.getTrechoId()));
        leitura.setSituacao(calcularSituacao(dto.getLamina()));
        return mapper.paraDTO(leituraRepository.save(leitura));
    }

    @Transactional
    public void excluir(Long id) {
        leituraRepository.delete(buscarEntidade(id));
    }

    public String calcularSituacao(int lamina) {
        if (lamina >= limiarAlerta) {
            return Leitura.ALERTA;
        }
        if (lamina >= limiarAlerta / 2.0) {
            return Leitura.ATENCAO;
        }
        return Leitura.NORMAL;
    }

    public int getLimiarAlerta() {
        return limiarAlerta;
    }

    private Leitura buscarEntidade(Long id) {
        return leituraRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Leitura " + id + " não encontrada."));
    }
}
