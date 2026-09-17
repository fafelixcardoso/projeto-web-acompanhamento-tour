package br.uel.acomp_tour.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


// Não vamos conseguir testar a validação e o cadastro no banco pq
// não tem controller ainda, mas acho melhor ir criando assim direto doq fazer
// td e ir mudando dps

@Entity
@Table(name = "restaurantes")
@JsonPropertyOrder({
        "id", "nome", "endereco", "comentario", "economia", "avaliacao", "dataVisita"
})
public class Restaurante {

    // Atributos dos restaurantes (provisório)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="O nome do restaurante não deve ser nulo/vazio")
    @Size(max = 150, message = "Nome do restaurante muito longo!")
    private String nome;

    @NotBlank(message="O endereço do restaurante é obrigatório")
    @Size(max = 200, message = "Endereço do restaurante muito longo!")
    private String endereco;

    @Size(max = 500, message = "Comentário muito longo!")
    private String comentario;

    @NotNull(message = "O valor de economia é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private Float economia;

    @NotNull(message = "A avaliação é obrigatória")
    @Min(value = 0, message = "A avaliação mínima é 0")
    @Max(value = 5, message = "A avaliação máxima é 5")
    private Float avaliacao;

    @PastOrPresent(message = "A data da visita não pode estar no futuro")
    private LocalDate dataVisita;


    // Getters e setters
    public Long getId(){ return id; }
    public String getNome(){ return nome; }
    public String getEndereco(){ return endereco; }
    public String getComentario(){ return comentario; }
    public Float getEconomia(){ return economia; }
    public Float getAvaliacao(){ return avaliacao; }
    public LocalDate getDataVisita(){ return dataVisita; }

    public void setId(Long id){ this.id = id; }
    public void setNome(String nome){ this.nome = nome; }
    public void setEndereco(String endereco){ this.endereco = endereco; }
    public void setComentario(String comentario){ this.comentario = comentario; }
    public void setEconomia(Float economia){ this.economia = economia; }
    public void setAvaliacao(Float avaliacao){ this.avaliacao = avaliacao; }
    public void setDataVisita(LocalDate dataVisita){ this.dataVisita = dataVisita; }
}
