package com.techzen.academy_n0325c1.service;

import com.techzen.academy_n0325c1.model.Clazz;
import com.techzen.academy_n0325c1.repository.IClazzRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ClazzService implements IClazzService {
    IClazzRespository clazzRespository;

    public Clazz findById(int id) {
        return clazzRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clazz not found #" + id));
    }
}
