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


    // Deu preg, amnh continuo😋
    private String endereco;
    private String comentario;
    private Float economia;
    private Float avaliacao;
    private LocalDate dataVisita;


    // Getters e setters
    public Long getId(){ return id; }
    public String getNome(){ return nome; }
    public String getEndereco(){ return endereco; }
    public String getComentario(){ return comentario; }
    public Float getEconomia(){ return economia; }
    public Float getAvaliacao(){ return avaliacao; }
    public LocalDate getDataVisita(){ return dataVisita; }

    public void setNome(String nome){ this.nome = nome; }
    public void setEndereco(String endereco){ this.endereco = endereco; }
    public void setComentario(String comentario){ this.comentario = comentario; }
    public void setEconomia(Float economia){ this.economia = economia; }
    public void setAvaliacao(Float avaliacao){ this.avaliacao = avaliacao; }
    public void setDataVisita(LocalDate dataVisita){ this.dataVisita = dataVisita; }

}
