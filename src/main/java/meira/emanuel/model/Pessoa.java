package meira.emanuel.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Pessoa")
@NoArgsConstructor
@Data
public class Pessoa {
    @Id
    private String id;
    private String nome;
    private int idade;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    private List<Endereco> endereco;

    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Endereco {
        private String estado;
        private String cidade;
    }
}
// colocar mais um endereço dentro do array endereco sem buscar pessoa
//atulizar endereco dentro do array sem buscar entidade pessoa
// deletar endereco so usando o indice do array