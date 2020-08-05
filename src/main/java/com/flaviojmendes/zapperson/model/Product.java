package com.flaviojmendes.zapperson.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Document(collection="products")
@TypeAlias("product")
public class Product {

    @Id
    private String id;

    @DBRef
    private Category category;

    @NotNull
    private String label;

    @NotNull
    private Float price;

    private String img;

    private Integer qtyAvailable;

    private Integer order;

    private List<String> description;

    @DBRef
    private List<Product> subProducts;

}
