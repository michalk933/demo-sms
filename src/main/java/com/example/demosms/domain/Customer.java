package com.example.demosms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "customer")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Customer {

    @Id
    @Field("id")
    @JsonIgnore
    private String customerId = UUID.randomUUID().toString();

    @NotEmpty
    @Field("name")
    private String name;

    @NotEmpty
    @Indexed(unique = true)
    @Field("phone_number")
    private String phoneNumber;

    @NotEmpty
    @Field("owner_id")
    private String ownerId;

    @JsonIgnore
    @Field("create_date")
    private final LocalDateTime createDate = LocalDateTime.now();

}
