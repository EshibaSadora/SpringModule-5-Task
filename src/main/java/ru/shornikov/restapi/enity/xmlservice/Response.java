package ru.shornikov.restapi.enity.xmlservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    String Out;

    public Response(String out) {
        Out = out;
    }
}
