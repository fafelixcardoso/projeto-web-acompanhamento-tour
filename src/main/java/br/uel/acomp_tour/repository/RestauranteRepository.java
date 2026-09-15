package br.uel.acomp_tour.repository;

import br.uel.acomp_tour.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository
    extends JpaRepository<Restaurante, Long> {
        // Nada aqui por enquanto, mas acho q vamos precisar mexer dps
}
