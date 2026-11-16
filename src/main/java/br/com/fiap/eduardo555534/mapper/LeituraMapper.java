package br.com.fiap.eduardo555534.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.fiap.eduardo555534.domain.Leitura;
import br.com.fiap.eduardo555534.dto.LeituraDTO;

@Component
public class LeituraMapper {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Leitura paraEntidade(LeituraDTO dto) {
        Leitura leitura = new Leitura();
        copiarParaEntidade(dto, leitura);
        return leitura;
    }

    public void copiarParaEntidade(LeituraDTO dto, Leitura leitura) {
        leitura.setDataHora(dto.getDataHora());
        leitura.setLamina(dto.getLamina());
    }

    public LeituraDTO paraDTO(Leitura leitura) {
        LeituraDTO dto = new LeituraDTO();
        dto.setId(leitura.getId());
        dto.setDataHora(leitura.getDataHora());
        dto.setLamina(leitura.getLamina());
        dto.setSituacao(leitura.getSituacao());
        dto.setTrechoId(leitura.getTrecho().getId());
        dto.setTrechoLogradouro(leitura.getTrecho().getLogradouro());
        dto.setDataHoraFormatada(leitura.getDataHora().format(FORMATO));
        return dto;
    }
}
