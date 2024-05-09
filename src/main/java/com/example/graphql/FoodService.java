package com.example.graphql;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public Food save(String name) {
        return foodRepository.save(Food.from(name));
    }

    public Food getFood(String name) {
        return foodRepository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Food> getFoods() {
        return foodRepository.findAll();
    }
}
