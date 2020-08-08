package ru.choicerestaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.Meal;
import ru.choicerestaurant.to.MealTo;
import ru.choicerestaurant.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.choicerestaurant.DataGeneration.*;
import static ru.choicerestaurant.repository.TestData.*;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class MealServTest extends AbstractServiceTest {

    @Autowired
    private MealServ mealServ;

    @Test
    public void create() {
        MealTo newMealTo = getNewMeal();
        Meal mealNew = mealServ.getObjectByToObject(newMealTo);
        Meal created = mealServ.create(newMealTo);
        Integer createdId = created.getId();
        mealNew.setId(createdId);
        assertThat(created).isEqualToIgnoringGivenFields(mealNew, "registered");
    }

    @Test ()
    public void delete() {
        Integer id = getRandomElement(mealTos).getId();
        mealServ.delete(id);
        assertThrows(NotFoundException.class, () -> mealServ.get(id));
    }

    @Test
    public void get() {
        MealTo mealTo = getRandomElement(mealTos);
        Meal meal = mealServ.getObjectByToObject(mealTo);
        assertThat(mealServ.get(mealTo.getId())).isEqualTo(meal);
    }

}
