package br.com.fiap.eduardo555534.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.fiap.eduardo555534.domain.Leitura;

class LeituraServiceTest {

    private final LeituraService service = new LeituraService(null, null, null, 14);

    @Test
    @DisplayName("lamina abaixo de L/2 e NORMAL")
    void laminaBaixaEhNormal() {
        assertEquals(Leitura.NORMAL, service.calcularSituacao(0));
        assertEquals(Leitura.NORMAL, service.calcularSituacao(3));
        assertEquals(Leitura.NORMAL, service.calcularSituacao(6));
    }

    @Test
    @DisplayName("lamina entre L/2 e L e ATENCAO")
    void laminaIntermediariaEhAtencao() {
        assertEquals(Leitura.ATENCAO, service.calcularSituacao(7));
        assertEquals(Leitura.ATENCAO, service.calcularSituacao(9));
        assertEquals(Leitura.ATENCAO, service.calcularSituacao(12));
        assertEquals(Leitura.ATENCAO, service.calcularSituacao(13));
    }

    @Test
    @DisplayName("lamina a partir de L e ALERTA")
    void laminaAltaEhAlerta() {
        assertEquals(Leitura.ALERTA, service.calcularSituacao(14));
        assertEquals(Leitura.ALERTA, service.calcularSituacao(18));
        assertEquals(Leitura.ALERTA, service.calcularSituacao(300));
    }

    @Test
    @DisplayName("os tres casos do roteiro de correcao da prova")
    void casosDoRoteiroDaProva() {
        assertEquals(Leitura.NORMAL, service.calcularSituacao(3));
        assertEquals(Leitura.ATENCAO, service.calcularSituacao(9));
        assertEquals(Leitura.ALERTA, service.calcularSituacao(18));
    }
}
