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
@Document("owner")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Owner {

    @Id
    @NotEmpty
    @JsonIgnore
    @Field("owner_id")
    private final String ownerId = UUID.randomUUID().toString();

    @NotEmpty
    @Field("name")
    private String name;

    @NotEmpty
    @Indexed(unique = true)
    @Field("phone_number")
    private String phoneNumber;

    @JsonIgnore
    @Field("count_send_sms")
    private Long countSendSms = 0L;

    @JsonIgnore
    @Field("balance")
    private String balance;

    @JsonIgnore
    @Field("create_date")
    private final LocalDateTime createDate = LocalDateTime.now();

    @JsonIgnore
    @Field("is_delete")
    private boolean isDelete = false;

}
