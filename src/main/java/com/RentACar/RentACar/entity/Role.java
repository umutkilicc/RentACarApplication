package com.RentACar.RentACar.entity;

import com.RentACar.RentACar.common.entity.BaseEntity;
import com.RentACar.RentACar.consts.EntityConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE rent_a_car.roles set state=0 where id= ? and version=?")
@Where(clause = EntityConstants.WHERE_CLAUSE)
@Table(name = "roles")
public class Role extends BaseEntity {

    /**
     * Role entity user relation information
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Role Entity name information
     */
    @Column(name = "name")
    private String name;

    public Role(User user) {
        this.user = user;
        this.name = "ADMIN";
    }

    public Role(UUID id, String admin) {

        this.setId(id);
        this.name = admin;
    }
}
