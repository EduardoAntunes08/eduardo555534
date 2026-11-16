package br.com.fiap.eduardo555534.mapper;

import org.springframework.stereotype.Component;

import br.com.fiap.eduardo555534.domain.Trecho;
import br.com.fiap.eduardo555534.dto.TrechoDTO;

@Component
public class TrechoMapper {

    public Trecho paraEntidade(TrechoDTO dto) {
        Trecho trecho = new Trecho();
        copiarParaEntidade(dto, trecho);
        return trecho;
    }

    public void copiarParaEntidade(TrechoDTO dto, Trecho trecho) {
        trecho.setLogradouro(dto.getLogradouro());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
    }

    public TrechoDTO paraDTO(Trecho trecho) {
        TrechoDTO dto = new TrechoDTO();
        dto.setId(trecho.getId());
        dto.setLogradouro(trecho.getLogradouro());
        dto.setTipoCalcada(trecho.getTipoCalcada());
        dto.setAltitude(trecho.getAltitude());
        dto.setBairroId(trecho.getBairro().getId());
        dto.setBairroNome(trecho.getBairro().getNome());
        return dto;
    }
}
