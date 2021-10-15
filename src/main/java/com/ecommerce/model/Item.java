package com.ecommerce.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Item {
    String id;
    String name;
    long price;
}
