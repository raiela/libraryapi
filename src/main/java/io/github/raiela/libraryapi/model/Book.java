package io.github.raiela.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String title;

    @Column(name = "data_publicacao")
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private BookGenre genre;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Author author;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime cadastreDate;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime atualizationDate;

    @Column(name = "id_usuario")
    private UUID idUser;
}
