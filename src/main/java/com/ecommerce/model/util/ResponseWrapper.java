package com.ecommerce.model.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

import static org.springframework.http.HttpStatus.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseWrapper {
    int httpStatusCode;

    @NonNull
    ResponseMessage rootMessage;

    Collection<? extends ResponseMessage> errors;

    Collection<ResponseMessage> messages;

    public static ResponseWrapperBuilder ok(){
        return ResponseWrapper.builder().httpStatusCode(OK.value());
    }

    public static ResponseWrapperBuilder badRequest(){
        return ResponseWrapper.builder().httpStatusCode(BAD_REQUEST.value());
    }

    public static ResponseWrapperBuilder notFound(){
        return ResponseWrapper.builder().httpStatusCode(NOT_FOUND.value());
    }

    public static ResponseWrapperBuilder internalServerError(){
        return ResponseWrapper.builder().httpStatusCode(INTERNAL_SERVER_ERROR.value());
    }

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ResponseMessage {
        @NonNull
        final String code;
        @NonNull
        final String reason;
        @NonNull
        final String message;

        public static ResponseMessage of(String code, String reason, String message){
            return new ResponseMessage(code, reason, message);
        }

    }

}
