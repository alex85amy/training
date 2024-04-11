package com.example.training.service;

import com.example.training.model.dto.PType2InfoDto;
import com.example.training.repository.PType2InfoRepository;
import jakarta.persistence.Access;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PType2InfoService {

    @Autowired
    private PType2InfoRepository pType2InfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PType2InfoDto getPType2InfoById(String )
}
