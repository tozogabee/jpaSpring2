package com.jpa.repository.model;

import com.jpa.config.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQuery(
        name = "findByTitle",
        query = "select b from Book b where b.title = :title"
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Version
    private Long version;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "PUBLICATION_DATE", nullable = false)
    private LocalDateTime publicationDate;

    public Book() {
    }

    public Book(String title, LocalDateTime publicationDate) {
        this.title = title;
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", version=" + version +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
