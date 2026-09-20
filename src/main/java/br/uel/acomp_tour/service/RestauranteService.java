package br.uel.acomp_tour.service;

import br.uel.acomp_tour.model.Restaurante;
import br.uel.acomp_tour.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    // Busca restaurantes
    public List<Restaurante> buscar(
            Long id,
            String nome,
            Float minAvaliacao,
            Float maxAvaliacao,
            Float minEconomia,
            Float maxEconomia,
            LocalDate minDataVisita,
            LocalDate maxDataVisita,
            String ordem,
            String atributo
    ){
        String nomeValidado = (nome != null && !nome.isBlank()) ? nome : null;

        // Ordenação
        Sort sortAtr =  Sort.by(atributo);
        Sort sortAtrOrdem = ordem.equals("DESC") ? sortAtr.descending() : sortAtr.ascending();

        return restauranteRepository.buscarRestaurantes(
                id,
                nomeValidado,
                minAvaliacao,
                maxAvaliacao,
                minEconomia,
                maxEconomia,
                minDataVisita,
                maxDataVisita,
                sortAtrOrdem
        );
    }

    // Add restaurante
    public Restaurante adicionar(Restaurante r){
        if(restauranteRepository.existsByNome(r.getNome())){
            throw new RuntimeException("Restaurante já cadastrado!");
        }

        return restauranteRepository.save(r);
    }

    // Remover restaurante
    public void remover(Long id) {
        if (!restauranteRepository.existsById(id)) {
            throw new RuntimeException("Restaurante não encontrado com id: " + id);
        }

        restauranteRepository.deleteById(id);
    }

    // Att restaurante
    public Restaurante atualizar(Long id, Restaurante r){

        return restauranteRepository.findById(id).map(
                r_antigo -> {
                    r_antigo.setNome(r.getNome());
                    r_antigo.setEndereco(r.getEndereco());
                    r_antigo.setComentario(r.getComentario());
                    r_antigo.setEconomia(r.getEconomia());
                    r_antigo.setAvaliacao(r.getAvaliacao());
                    r_antigo.setDataVisita(r.getDataVisita());

                    return r_antigo;
                }
        ).orElseThrow(
                () -> new RuntimeException("Restaurante não encontrado com id:" + id)
        );

    }
}
