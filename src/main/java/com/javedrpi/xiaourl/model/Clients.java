package com.javedrpi.xiaourl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="clients")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="client_id")
    private String clientId;

    @Column(name="api_token")
    private String apiToken;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;

    @Column(name="is_active")
    private int isActive;
}
