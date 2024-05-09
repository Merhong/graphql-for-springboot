package com.example.graphql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FoodController {

    private final FoodService foodService;

    /**
     * @MutationMapping은 @PostMapping과 같은 어노테이션으로 graphql에 Mutation에 사용됩니다.
     * graphql은 endpoint과 하나이므로 @MutationMapping 어노테이션만 지정해 주고 다른 설정은 필요 없습니다.
     */
    @MutationMapping
    public Food save(@Argument String name) { // @Argument 는 @RequestBody, @RequestParam과 같은 인자값을 지정해줄 때 사용합니다.
        return foodService.save(name);
    }

    /**
     * @QueryMapping도 @GetMapping과 같은 어노테이션 입니다.
     * 말고도 @SubscriptionMapping이 있습니다.
     */
    @QueryMapping
    public Food getFood(@Argument String name) {
        return foodService.getFood(name);
    }

    @QueryMapping
    public List<Food> getFoods() {
        return foodService.getFoods();
    }
}