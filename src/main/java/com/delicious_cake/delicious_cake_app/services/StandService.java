package com.delicious_cake.delicious_cake_app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.StandDTO;
import com.delicious_cake.delicious_cake_app.entities.StandEntity;
import com.delicious_cake.delicious_cake_app.mappers.StandMapper;
import com.delicious_cake.delicious_cake_app.repositories.StandRepository;

@Service
public class StandService {

    private final StandRepository standRepository;

    public StandService(StandRepository standRepository) {
        this.standRepository = standRepository;
    }

    //Create Method
    public StandDTO create(StandDTO dto) {

        validateStand(dto);

        StandEntity stand = StandMapper.toEntity(dto);
        StandEntity savedStand = standRepository.save(stand);
        
        return StandMapper.toDTO(savedStand);
    }

    //Get Method
    public StandDTO getById(Long id) {

        StandEntity stand = standRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Stand not found with id: " + id));

        return StandMapper.toDTO(stand);
    }

    //Get All Method
    public List<StandDTO> getAll() {

        return standRepository.findAll()
                .stream()
                .map(StandMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method
    public StandDTO update(Long id, StandDTO dto) {

        StandEntity existingStand = standRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Stand not found with id: " + id));

        validateStand(dto);

        existingStand.setNumber(dto.getNumber());
        existingStand.setPersons(dto.getPersons());
        existingStand.setIsAvailable(dto.getIsAvailable());

        StandEntity updatedStand = standRepository.save(existingStand);

        return StandMapper.toDTO(updatedStand);
    }

    //Delete Method
    public void delete(Long id) {

        StandEntity stand = standRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Stand not found with id: " + id));

        standRepository.delete(stand);
    }

    //Validation Method
    private void validateStand(StandDTO dto) {

        if (dto.getNumber() == null) {
            throw new IllegalArgumentException(
                    "Stand number cannot be null");
        }

        if (dto.getIsAvailable() == null) {
            throw new IllegalArgumentException(
                    "Stand availability cannot be null");
        }
    }
}