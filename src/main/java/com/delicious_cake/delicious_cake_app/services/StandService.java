package com.delicious_cake.delicious_cake_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.StandEntity;
import com.delicious_cake.delicious_cake_app.repositories.StandRepository;

@Service 
public class StandService {

    private final StandRepository standRepository;

    public StandService(StandRepository standRepository) {
        this.standRepository = standRepository;
    }

    //Create Method
    public StandEntity createStand(StandEntity stand) {
        if (stand.getNumber() == null) {
            throw new IllegalArgumentException("Stand number cannot be null");
        }
        if (stand.getIsAvailable() == null) {
            throw new IllegalArgumentException("Stand availability cannot be null");
        }
        return standRepository.save(stand);
    }

    //Get Method
    public StandEntity getStandById(Long id) {
        return standRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stand not found with id: " + id));
    }

    //Get All Method
    public List<StandEntity> getAllStands() {
        return standRepository.findAll();
    }

    //Update Method
    public StandEntity updateStand(Long id, StandEntity stand) {
        StandEntity existingStand = getStandById(id);

        if (stand.getNumber() == null) {
            throw new IllegalArgumentException("Stand number cannot be null");
        }
        if (stand.getIsAvailable() == null) {
            throw new IllegalArgumentException("Stand availability cannot be null");
        }

        existingStand.setNumber(stand.getNumber());
        existingStand.setIsAvailable(stand.getIsAvailable());

        return standRepository.save(existingStand);
    }

    //Delete Method
    public void deleteStand(Long id) {
        try{
            StandEntity existingStand = getStandById(id);
            standRepository.delete(existingStand);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting stand with id: " + id, e);
        }
    }
}