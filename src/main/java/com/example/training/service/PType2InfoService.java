package com.example.training.service;

import com.example.training.model.dto.PType2InfoDto;
import com.example.training.model.po.PType2Info;
import com.example.training.repository.PType2InfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PType2InfoService {

    @Autowired
    private PType2InfoRepository pType2InfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PType2InfoDto getPType2InfoById(String category) {
        Optional<PType2Info> pType2InfoOpt = Optional.ofNullable(pType2InfoRepository.findByCategory(category));
        if (pType2InfoOpt.isPresent()) {
            PType2Info pType2Info = pType2InfoOpt.get();
            return modelMapper.map(pType2Info, PType2InfoDto.class);
        }
        return null;
    }

    public List<PType2InfoDto> findAll() {
        List<PType2Info> pType2Infos = pType2InfoRepository.findAll();
        return pType2Infos
                .stream()
                .map(pType2Info -> modelMapper.map(pType2Info, PType2InfoDto.class))
                .toList();
    }

    public void add(PType2InfoDto pType2InfoDto) {
        PType2Info pType2Info = modelMapper.map(pType2InfoDto, PType2Info.class);
        pType2InfoRepository.save(pType2Info);
    }

    public void update(PType2InfoDto pType2InfoDto, String category) {
        Optional<PType2Info> pType2InfoOpt = Optional.ofNullable(pType2InfoRepository.findByCategory(category));
        if (pType2InfoOpt.isPresent()) {
            PType2Info pType2Info = pType2InfoOpt.get();
            pType2Info.setName(pType2InfoDto.getName());
            pType2InfoRepository.save(pType2Info);
        }
    }

    public void delete(String category) {
        Optional<PType2Info> pType2InfoOpt = Optional.ofNullable(pType2InfoRepository.findByCategory(category));
        if (pType2InfoOpt.isPresent()) {
            pType2InfoRepository.delete(pType2InfoOpt.get());
        }
    }
}
