package br.uel.acomp_tour.repository;

import br.uel.acomp_tour.model.Restaurante;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestauranteRepository
    extends JpaRepository<Restaurante, Long> {

    // Função para buscar restaurantes
    @Query(
            "SELECT r FROM Restaurante r " +
                "WHERE (:id IS NULL OR r.id = :id) " +
                "AND (:nome IS NULL OR r.nome LIKE CONCAT('%', :nome, '%')) " +
                "AND (:avaliacao IS NULL OR r.avaliacao = :avaliacao) " +
                "AND (:minEconomia IS NULL OR r.economia >= :minEconomia) " +
                "AND (:maxEconomia IS NULL OR r.economia <= :maxEconomia) " +
                "AND (:minVisita IS NULL OR r.dataVisita >= :minVisita) " +
                "AND (:maxVisita IS NULL OR r.dataVisita <= :maxVisita)"
    )
    List<Restaurante> buscarRestaurante(
            @Param("id") Long id,
            @Param("nome") String nome,
            @Param("avaliacao") Float avaliacao,
            @Param("minEconomia") Float minEconomia,
            @Param("maxEconomia") Float maxEconomia,
            @Param("minDataVisita") LocalDate minDataVisita,
            @Param("maxDataVisita") LocalDate maxDataVisita
    );

    Boolean existsByNome(String nome);
}
