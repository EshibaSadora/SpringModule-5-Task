package ru.shornikov.restapi.enity.xmlservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    String FieldA;
    String FieldB;
}
