package br.com.fiap.eduardo555534.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraDTO {

    private Long id;

    @NotNull(message = "A data e a hora são obrigatórias.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataHora;

    @NotNull(message = "A lâmina de água é obrigatória.")
    @Min(value = 0, message = "A lâmina de água deve estar entre 0 e 300 cm.")
    @Max(value = 300, message = "A lâmina de água deve estar entre 0 e 300 cm.")
    private Integer lamina;

    @NotNull(message = "O trecho é obrigatório.")
    private Long trechoId;

    private String trechoLogradouro;

    private String situacao;

    private String dataHoraFormatada;
}
