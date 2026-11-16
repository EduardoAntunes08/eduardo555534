package br.com.fiap.eduardo555534.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS534_BAIRRO")
@Getter
@Setter
@NoArgsConstructor
public class Bairro {

    public static final List<String> ZONAS = List.of("Centro", "Norte", "Sul", "Leste", "Oeste");

    public static final String ZONAS_REGEX = "Centro|Norte|Sul|Leste|Oeste";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BAIRRO")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 80)
    private String nome;

    @Column(name = "ZONA", nullable = false, length = 10)
    private String zona;

    @Column(name = "POPULACAO", nullable = false)
    private Integer populacao;
}
