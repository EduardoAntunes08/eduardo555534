package br.com.fiap.eduardo555534.mapper;

import org.springframework.stereotype.Component;

import br.com.fiap.eduardo555534.domain.Bairro;
import br.com.fiap.eduardo555534.dto.BairroDTO;

@Component
public class BairroMapper {

    public Bairro paraEntidade(BairroDTO dto) {
        Bairro bairro = new Bairro();
        copiarParaEntidade(dto, bairro);
        return bairro;
    }

    public void copiarParaEntidade(BairroDTO dto, Bairro bairro) {
        bairro.setNome(dto.getNome());
        bairro.setZona(dto.getZona());
        bairro.setPopulacao(dto.getPopulacao());
    }

    public BairroDTO paraDTO(Bairro bairro) {
        BairroDTO dto = new BairroDTO();
        dto.setId(bairro.getId());
        dto.setNome(bairro.getNome());
        dto.setZona(bairro.getZona());
        dto.setPopulacao(bairro.getPopulacao());
        return dto;
    }
}
