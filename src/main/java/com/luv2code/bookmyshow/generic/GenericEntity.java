package com.luv2code.bookmyshow.generic;

import com.luv2code.bookmyshow.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class GenericEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    protected final LocalDateTime createdTime;
    @ManyToOne
    @JoinColumn(name = "created_by")
    protected User createdBy;

    protected GenericEntity() {
        this.createdTime = LocalDateTime.now();
    }

}
