package br.uel.acomp_tour.repository;

import br.uel.acomp_tour.model.Restaurante;
import org.springframework.data.domain.Sort;
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
                "AND (:minAvaliacao IS NULL OR r.avaliacao >= :minAvaliacao) " +
                "AND (:maxAvaliacao IS NULL OR r.avaliacao <= :maxAvaliacao) " +
                "AND (:minEconomia IS NULL OR r.economia >= :minEconomia) " +
                "AND (:maxEconomia IS NULL OR r.economia <= :maxEconomia) " +
                "AND (:minDataVisita IS NULL OR r.dataVisita >= :minDataVisita) " +
                "AND (:maxDataVisita IS NULL OR r.dataVisita <= :maxDataVisita)"
    )
    List<Restaurante> buscarRestaurantes(
            @Param("id") Long id,
            @Param("nome") String nome,
            @Param("minAvaliacao") Float minAvaliacao,
            @Param("maxAvaliacao") Float maxAvaliacao,
            @Param("minEconomia") Float minEconomia,
            @Param("maxEconomia") Float maxEconomia,
            @Param("minDataVisita") LocalDate minDataVisita,
            @Param("maxDataVisita") LocalDate maxDataVisita,
            Sort sort // Parece que só de add isso aq já funciona
    );

    Boolean existsByNome(String nome);
}
