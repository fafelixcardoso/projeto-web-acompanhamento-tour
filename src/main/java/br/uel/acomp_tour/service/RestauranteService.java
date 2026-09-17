package br.uel.acomp_tour.service;

import br.uel.acomp_tour.model.Restaurante;
import br.uel.acomp_tour.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listarRestaurantes(){
        return restauranteRepository.findAll();
    }

    // Se tu achar q ficou mt feio pd mudar
    public List<Restaurante> listarRestaurantesOrdenado(String ordem, String atributo){
        Sort sort_atr =  Sort.by(atributo);
        Sort sort_atr_ordem = ordem.equals("desc") ? sort_atr.descending() : sort_atr.ascending();

        return restauranteRepository.findAll(sort_atr_ordem);
    }

    public Restaurante buscarRestauranteId(Long id){
        return restauranteRepository.findById(id).orElse(null);
    }

    public List<Restaurante> buscarRestaurantePorNome(String nome){
        return restauranteRepository.findByNomeContainingIgnoreCase(nome);
    }







}
