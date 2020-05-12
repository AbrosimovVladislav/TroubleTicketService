package ru.vakoom.troubleticketservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.troubleticketservice.model.Type;
import ru.vakoom.troubleticketservice.service.TypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @CrossOrigin
    @GetMapping("/types")
    public List<Type> getTypes(){
        return typeService.findAll();
    }

}
