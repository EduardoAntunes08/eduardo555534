package br.com.fiap.eduardo555534.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS534_TRECHO")
@Getter
@Setter
@NoArgsConstructor
public class Trecho {

    public static final List<String> TIPOS_CALCADA =
            List.of("Regular", "Irregular", "Escadaria", "Sem iluminação");

    public static final String TIPOS_CALCADA_REGEX = "Regular|Irregular|Escadaria|Sem iluminação";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRECHO")
    private Long id;

    @Column(name = "LOGRADOURO", nullable = false, length = 120)
    private String logradouro;

    @Column(name = "TIPO_CALCADA", nullable = false, length = 20)
    private String tipoCalcada;

    @Column(name = "ALTITUDE", nullable = false)
    private Integer altitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_BAIRRO", nullable = false,
            foreignKey = @ForeignKey(name = "FK_GS534_TRECHO_BAIRRO"))
    private Bairro bairro;
}
