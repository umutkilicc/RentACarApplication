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

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE rent_a_car.comments set state=0 where id= ? and version=?")
@Where(clause = EntityConstants.WHERE_CLAUSE)
@Table(name = "comments")
public class Comment extends BaseEntity {

    /**
     * Comment information
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Car Relation
     */
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_id")
    private Car car;
}
