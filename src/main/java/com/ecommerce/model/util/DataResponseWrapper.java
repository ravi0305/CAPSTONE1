package com.ecommerce.model.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
public class DataResponseWrapper<T> extends ResponseWrapper{
    Collection<T> data;
    Integer count;

    @Builder(builderMethodName = "dataResponseWrapperBuilder")
    private DataResponseWrapper(int httpStatusCode, ResponseMessage rootMessage, Collection<? extends ResponseMessage> errors, Collection<ResponseMessage> messages, Long count, Collection<T> data){
        super(httpStatusCode, rootMessage, errors, messages);
        this.count=count.intValue();
        this.data=data;
    }

    public static <T> DataResponseWrapperBuilder<T> ok(T t){
        List<T> list =new ArrayList<>();
        list.add(t);
        return DataResponseWrapper.<T>dataResponseWrapperBuilder()
                .httpStatusCode(HttpStatus.OK.value())
                .data(list)
                .count(1L);
    }

    public static <T> DataResponseWrapperBuilder<T> ok(Collection<T> results){
        return DataResponseWrapper.<T>dataResponseWrapperBuilder()
                .httpStatusCode(HttpStatus.OK.value())
                .data(results)
                .count((long) results.size());
    }
}
