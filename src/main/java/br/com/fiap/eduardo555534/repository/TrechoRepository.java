package br.com.fiap.eduardo555534.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.eduardo555534.domain.Trecho;

public interface TrechoRepository extends JpaRepository<Trecho, Long> {

    List<Trecho> findAllByOrderByLogradouroAsc();

    boolean existsByBairroId(Long bairroId);
}
