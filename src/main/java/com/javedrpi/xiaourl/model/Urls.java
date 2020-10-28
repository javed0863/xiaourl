package com.javedrpi.xiaourl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.function.Consumer;

@Entity
@Table(name="urls")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Urls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="original_url")
    private String originalUrl;

    @Column(name="shorten_url")
    private String shortenUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="expiration_date")
    private Date expirationDate;

    @Column(name="is_active")
    private int isActive;

    public Urls(String originalUrl, String shortenUrl, Date expirationDate, int isActive) {
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
    }

    public Urls with(Consumer<Urls> builderFunc){
        builderFunc.accept(this);
        return this;
    }

    public Urls build(){
        return new Urls(originalUrl, shortenUrl, expirationDate, isActive);
    }
}
