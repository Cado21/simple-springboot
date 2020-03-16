package com.fresh.restapi.models.item;

import com.fresh.restapi.models.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "item_table")
public class ItemEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private Integer quantity;
}
