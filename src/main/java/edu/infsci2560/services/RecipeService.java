package edu.infsci2560.services;

import edu.infsci2560.models.Recipe;
import edu.infsci2560.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author xinyi
 */
@RestController
@RequestMapping("/public/api/recipe")
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Recipe>> list() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(repository.findAll(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Recipe> list(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(repository.findOne(id), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "recipe/{id}", 
            method = RequestMethod.DELETE, 
            consumes="application/x-www-form-urlencoded", 
            produces = "application/json")
    public ModelAndView delete( @Valid Recipe recipe, BindingResult result) {
        repository.delete(recipe);
        return new ModelAndView("recipe", "recipes", repository.findAll());
    }
    
//    @RequestMapping(value = "recipes/edit/{id}", 
//            method = RequestMethod.GET, 
//            consumes="application/x-www-form-urlencoded", 
//            produces = "application/json")
//    public ModelAndView edit( @Valid Recipe recipe, Long id) {
//        return new ModelAndView("recipe", "recipes", repository.findOne(id));
//    } 
    
}