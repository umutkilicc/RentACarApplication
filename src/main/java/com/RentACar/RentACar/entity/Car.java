package com.RentACar.RentACar.entity;

import com.RentACar.RentACar.common.entity.BaseEntity;
import com.RentACar.RentACar.consts.EntityConstants;
import com.RentACar.RentACar.consts.MessageConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE rent_a_car.cars set state=0 where id= ? and version=?")
@Where(clause = EntityConstants.WHERE_CLAUSE)
@Table(name = "cars")
public class Car extends BaseEntity {

    /**
     * Car category information
     */
//    todo kategori tablosu olusturulup baglanabilir veya enum olarak tanımlanabilir.
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Column(name = "category")
    private String category;

    /**
     * Car title information
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 5, max = 30)
    @Column(name = "title")
    private String title;

    /**
     * Car description information
     */
    @NotBlank(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Size(min = 4, max = 500)
    @Column(name = "description")
    private String description;

    /**
     * Car price information
     */
    @NotNull(message = MessageConstants.MESSAGE_NOT_BLANK)
    @Min(50)
    @Max(1000)
    @Column(name = "price")
    private int price;

    /**
     * Cars comments relation
     */
//            --version - 1---
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "car_id",referencedColumnName = "id")
//            --version - 2 --
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "car_id")
    private Set<Comment> comments;
}
