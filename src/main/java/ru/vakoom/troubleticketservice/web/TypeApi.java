package ru.vakoom.troubleticketservice.web;

import io.swagger.annotations.ApiOperation;
import ru.vakoom.troubleticketservice.model.Type;

import java.util.List;

public interface TypeApi {

    @ApiOperation(value = "Get types",
            notes = "Get actual list of types",
            response = List.class)
    List<Type> getTypes();

}
