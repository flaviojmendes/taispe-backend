package com.flaviojmendes.zapperson.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Document(collection="companies")
@TypeAlias("company")
public class Company {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String whatsappNumber;

    private String logo;

    private Long deliveryPrice;

    @NotNull
    @Indexed(unique=true)
    private String email;

    private String primaryColor;

    private String backgroundColor;

    private Boolean requireAddress;

    private Boolean requireName;


    @NotNull
    @Indexed(unique=true)
    private String accessKey;

    @NotNull
    @Indexed(unique=true)
    private String publicKey;

    @DBRef
    private List<Category> categories;


}
