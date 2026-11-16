package br.com.fiap.eduardo555534.dto;

import br.com.fiap.eduardo555534.domain.Bairro;
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
public class BairroDTO {

    private Long id;

    @NotBlank(message = "O nome do bairro é obrigatório.")
    @Size(max = 80, message = "O nome deve ter no máximo 80 caracteres.")
    private String nome;

    @NotBlank(message = "A zona é obrigatória.")
    @Pattern(regexp = "|" + Bairro.ZONAS_REGEX,
            message = "A zona deve ser Centro, Norte, Sul, Leste ou Oeste.")
    private String zona;

    @NotNull(message = "A população é obrigatória.")
    @Min(value = 0, message = "A população deve ser maior ou igual a zero.")
    private Integer populacao;
}
