package com.ecommerce.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDto {
    @ApiModelProperty(name = "id", example = "1234")
    String id;
    @ApiModelProperty(name = "id", example = "john")
    String name;
    @ApiModelProperty(name = "id", example = "john@gmail.com")
    String email;
    @ApiModelProperty(name = "phone", example = "7896541236")
    String phone;
    @ApiModelProperty(name = "items")
    List<Item> items;

    public static CustomerDto from(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .items(customer.getItems())
                .build();
    }
}
