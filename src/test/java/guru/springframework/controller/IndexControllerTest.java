package guru.springframework.controller;

import guru.springframework.model.Recipe;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class IndexControllerTest {

    IndexController indexController;
    @Mock
    RecipeServiceImpl recipeService;
    @Mock
    Model model;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.setId(2L);
        recipes.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String str = indexController.getIndexPage(model);
        assertEquals("index",str);

        Mockito.verify(recipeService,Mockito.times(1)).getRecipes();
        Mockito.verify(model,Mockito.times(1)).addAttribute(ArgumentMatchers.eq("recipes"),argumentCaptor.capture());

        assertEquals(2,argumentCaptor.getValue().size());
    }
}