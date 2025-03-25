package io.github.raiela.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate birthDate;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nationality ;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime cadastreDate;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime atualizationDate;

    @Column(name = "id_usuario")
    private UUID idUser;

}
