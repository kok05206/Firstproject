package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체로 만들고, 이를 IoC 컨테이너에 등록하는 어노테이션!
public class Chef {
    // 셰프는 식재료 공장을 알고있음
    private IngredientFactory ingredientFactory;

    // 셰프가 식재료 공장과 협업하기 위한 DI , DI : Dependency Injection , 의존성 주입 , 동작에 필요한 객체를 외부에서 받아옴
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 요리 재료 준비!
       Ingredient ingredient = ingredientFactory.get(menu);

        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
