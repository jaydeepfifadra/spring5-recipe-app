package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    CategoryRepository categoryRepository;
    RecipeRepository recipeRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    public List<Recipe> getRecipes(){
        List<Recipe> recipes =  new ArrayList<>();


        Optional<UnitOfMeasure> optionalTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> optionalTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> optionalCup = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> optionalPinch = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> optionalOunce = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> optionalEach = unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasure> optionalDash = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> optionalPint = unitOfMeasureRepository.findByDescription("Pint");


        UnitOfMeasure teaSpoonUom = optionalTeaspoon.get();
        UnitOfMeasure tableSpoonUom = optionalTablespoon.get();
        UnitOfMeasure cupUom = optionalCup.get();
        UnitOfMeasure pinchUom = optionalPinch.get();
        UnitOfMeasure ounceUom = optionalOunce.get();
        UnitOfMeasure eachUom = optionalEach.get();
        UnitOfMeasure dashUom = optionalDash.get();
        UnitOfMeasure pintUom = optionalPint.get();

        Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
        Category categoryMexican = optionalMexican.get();

        Optional<Category> optionalAmerican = categoryRepository.findByDescription("American");
        Category categoryAmerican = optionalAmerican.get();

        Recipe guacRecipe = new Recipe();

        guacRecipe.getCategories().add(categoryMexican);
        guacRecipe.getCategories().add(categoryAmerican);
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setCookTime(0);
        guacRecipe.setPrepTime(20);
        guacRecipe.setDifficulty(Difficulty.EASY);

        guacRecipe.setDirection("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");


        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacRecipe.setNotes(guacNotes);


        guacRecipe.addIngredients(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));

        guacRecipe.addIngredients(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));

        guacRecipe.addIngredients(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));

        guacRecipe.addIngredients(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));

        guacRecipe.addIngredients(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));

        guacRecipe.addIngredients(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));

        guacRecipe.addIngredients(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));

        guacRecipe.addIngredients(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));



        recipes.add(guacRecipe);
        return recipes;
    }



}
