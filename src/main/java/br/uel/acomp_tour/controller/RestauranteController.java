package br.uel.acomp_tour.controller;

import br.uel.acomp_tour.model.Restaurante;
import br.uel.acomp_tour.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.RuntimeErrorException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    @Autowired
    public RestauranteController(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }

    @GetMapping("/inicio")
    public String listar(Model model){
        model.addAttribute("restaurantes", restauranteService.listar());

        return "view/listagem";
    }

    @GetMapping("/novo")
    public String abrirFormulario (Model model){
        model.addAttribute("restaurante", new Restaurante());
        return "view/formulario";
    }

    @GetMapping("inicio/filtros")
    public String filtrar(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Float minAvaliacao,
            @RequestParam(required = false) Float maxAvaliacao,
            @RequestParam(required = false) Float minEconomia,
            @RequestParam(required = false) Float maxEconomia,
            @RequestParam(required = false) LocalDate minDataVisita,
            @RequestParam(required = false) LocalDate maxDataVisita,
            @RequestParam(required = false) String ordem,
            @RequestParam(required = false) String atributo,
            RedirectAttributes ra
    ){

        List<Restaurante> resultados = restauranteService.buscar(
                id,
                nome,
                minAvaliacao,
                maxAvaliacao,
                minEconomia,
                maxEconomia,
                minDataVisita,
                maxDataVisita,
                ordem,
                atributo
        );

        if(resultados.isEmpty()){
            ra.addFlashAttribute("msg", "Nada encontrado!");
            return "redirect:../inicio";
        }


        ra.addFlashAttribute("restaurantes_filtrados", resultados);
        ra.addFlashAttribute("msg", "Filtros aplicados!");
        return "redirect:../inicio";
    }

    @PostMapping
    public String cadastrar(@Valid BindingResult erros, @ModelAttribute Restaurante restaurante
                            , RedirectAttributes ra) {
        if (erros.hasErrors()) {
            return "view/formulario";
        }

        try {
            restauranteService.adicionar(restaurante);

            ra.addFlashAttribute("msg", "Restaurante cadastrado!");
            return "redirect:restaurantes/inicio";
        }catch(RuntimeException erro){
            ra.addFlashAttribute("msg_erro", erro.getMessage());
            return "view/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model, RedirectAttributes ra) {

        Restaurante rest_para_editar = restauranteService.buscarPorId(id);

        if(rest_para_editar != null){
            model.addAttribute("restaurante_edicao", rest_para_editar);
            return "view/formulario";
        }else{

            ra.addFlashAttribute("msg", "Restaurante não encontrado para edição!");
            return "redirect:restaurantes/inicio";
        }
    }


    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id, @Valid
                            @ModelAttribute Restaurante restaurante, BindingResult erros,
                            RedirectAttributes ra) {
        if (erros.hasErrors()) {
            return "view/formulario";
        }

        restauranteService.atualizar(id, restaurante);
        ra.addFlashAttribute("msg", "Restaurante atualizado!");
        return "redirect:restautantes/inicio";
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
            restauranteService.remover(id);

            ra.addFlashAttribute("msg", "Restaurante excluído!");
            return "redirect:restaurantes/inicio";
    }


}
