package ru.vakoom.troubleticketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.troubleticketservice.model.Type;
import ru.vakoom.troubleticketservice.repo.TypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Type findByShowName(String showName) {
        return typeRepository.findByShowName(showName);
    }

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

}
