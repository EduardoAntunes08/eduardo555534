package br.com.fiap.eduardo555534.domain;

import java.time.LocalDateTime;

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
@Table(name = "GS534_LEITURA")
@Getter
@Setter
@NoArgsConstructor
public class Leitura {

    public static final String NORMAL = "NORMAL";
    public static final String ATENCAO = "ATENCAO";
    public static final String ALERTA = "ALERTA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEITURA")
    private Long id;

    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "LAMINA", nullable = false)
    private Integer lamina;

    @Column(name = "SITUACAO", nullable = false, length = 10)
    private String situacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TRECHO", nullable = false,
            foreignKey = @ForeignKey(name = "FK_GS534_LEITURA_TRECHO"))
    private Trecho trecho;
}
