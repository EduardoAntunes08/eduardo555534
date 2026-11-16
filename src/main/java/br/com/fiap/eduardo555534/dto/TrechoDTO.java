package br.com.fiap.eduardo555534.dto;

import br.com.fiap.eduardo555534.domain.Trecho;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrechoDTO {

    private Long id;

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(max = 120, message = "O logradouro deve ter no máximo 120 caracteres.")
    private String logradouro;

    @NotBlank(message = "O tipo de calçada é obrigatório.")
    @Pattern(regexp = "|" + Trecho.TIPOS_CALCADA_REGEX,
            message = "O tipo de calçada deve ser Regular, Irregular, Escadaria ou Sem iluminação.")
    private String tipoCalcada;

    @NotNull(message = "A altitude é obrigatória.")
    @Min(value = 0, message = "A altitude deve estar entre 0 e 2000 metros.")
    @Max(value = 2000, message = "A altitude deve estar entre 0 e 2000 metros.")
    private Integer altitude;

    @NotNull(message = "O bairro é obrigatório.")
    private Long bairroId;

    private String bairroNome;
}
