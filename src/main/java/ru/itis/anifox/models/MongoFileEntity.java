package ru.itis.anifox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Document(collection = "anifox")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MongoFileEntity {
    @Id
    private UUID id;

    private Binary content;
}
