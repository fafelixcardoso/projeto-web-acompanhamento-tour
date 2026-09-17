package br.uel.acomp_tour.repository;

import br.uel.acomp_tour.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestauranteRepository
    extends JpaRepository<Restaurante, Long> {

    // Filtrar por nome (id é nativo)
    List<Restaurante> findByNomeContainingIgnoreCase(String nome);

    // Filtrar por valor fixo
    List<Restaurante> findByEconomia(Float economia_min);
    List<Restaurante> findByAvaliacao(Float avaliacao);
    List<Restaurante> findByDataVisita(LocalDate data);

    // Filtrar por range
    List<Restaurante> findByEconomiaBetween(Float economia_min, Float economia_max);
    List<Restaurante> findByAvaliacaoBetween(Float avaliacao_min, Float avaliacao_max);
    List<Restaurante> findByDataVisitaBetween(LocalDate data_inicial, LocalDate data_final);



}
