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
@Document(collection="categories")
@TypeAlias("category")
public class Category {

    @Id
    private String id;

    @NotNull
    private String label;

    private String dependsOn;

    private Integer order;

    @DBRef
    private List<Product> products; 

    @DBRef
    private Company company;

}
