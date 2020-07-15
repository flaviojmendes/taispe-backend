package com.flaviojmendes.zapperson.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection="orders")
@TypeAlias("order")
public class Order {

    @Id
    private String id;

    private List<Product> products;

    @DBRef
    private Company company;

}
