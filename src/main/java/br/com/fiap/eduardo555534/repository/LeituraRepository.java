package br.com.fiap.eduardo555534.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.eduardo555534.domain.Leitura;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {

    List<Leitura> findAllByOrderByDataHoraDesc();

    boolean existsByTrechoId(Long trechoId);
}
