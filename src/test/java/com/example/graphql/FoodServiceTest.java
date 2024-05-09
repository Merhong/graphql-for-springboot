package com.example.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureGraphQlTester
class FoodServiceTest {

    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private FoodRepository foodRepository;

    /**
     * service와 controller 에서 void로 반환하기에 반환 값이 null로 표시되기에
     * Food로 반환 값을 지정하면 테스트가 가능합니다.
     */
    @Test
    void save_쿼리_테스트() throws Exception {
        graphQlTester.documentName("save") // /resource/graphql-test에 만든 쿼리 이름을 적어주면 됩니다.
                .variable("name", "딸기") // 다음으로 인자 값을 적어줍니다. 첫음에는 인자 명, 두 번째는 인자 값
                .execute()
                .path("data.save.id") // 쿼리에 id를 반환하지 않을 경우 해당 값이 없어 예외가 터집니다.
                .entity(Long.class) // 반환 값에 자료형은 자바 클래스를 맞춰 주면 됩니다.
                .isEqualTo(1L)
                .path("data.save.name")
                .entity(String.class)
                .isEqualTo("딸기");
    }

    @Test
    void getFood_쿼리_테스트() throws Exception {
        foodRepository.save(Food.from("망고"));
        graphQlTester.documentName("getFood")
                .variable("name", "망고")
                .execute()
                .path("data.getFood.id")
                .entity(Long.class)
                .isEqualTo(1L)
                .path("data.getFood.name")
                .entity(String.class)
                .isEqualTo("망고");
    }

    @Test
    void getFoods_쿼리_테스트() throws Exception {
        foodRepository.save(Food.from("망고"));
        foodRepository.save(Food.from("딸기"));
        foodRepository.save(Food.from("키위"));
        foodRepository.save(Food.from("사과"));
        foodRepository.save(Food.from("배"));
        foodRepository.save(Food.from("귤"));

        graphQlTester.documentName("getFoods")
                .execute()
                .path("data.getFoods[*].name")  // 반환 값이 배열일 경우 배열 사용하듯 []로 테스트가 가능합니다.
                .entityList(String.class)
                .containsExactly("망고", "딸기", "키위", "사과", "배", "귤"); // 저장한 순서와 일치해야 합니다.
        //              .path("getFoods.graphql[0].name") // 배열에 특정 값만 테스트할 경우
        //              .entity(String.class)
        //              .isEqualTo("망고");
    }
}