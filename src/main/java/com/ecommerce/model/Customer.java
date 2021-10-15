package com.ecommerce.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Customer {
    String id;
    String name;
    String email;
    String phone;
    List<Item> items;
}
