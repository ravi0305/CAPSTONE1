package com.ecommerce.model;

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
public class ItemDto {
    @ApiModelProperty(name = "id", example = "r1")
    String id;
    @ApiModelProperty(name = "name", example = "refrigerator")
    String name;
    @ApiModelProperty(name = "price", example = "18000")
    long price;

    public static ItemDto from(Item item){
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
