package api.recipebook.recipe.controller

import api.recipebook.recipe.payload.*
import api.recipebook.recipe.service.RecipeService
import org.springframework.web.bind.annotation.*

@RestController
class RecipeController(val recipeService: RecipeService) {

    @PostMapping(
        value = ["/api/recipe"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun create(@RequestBody payload: CreateRecipeRequest): WebResponse<RecipeResponse>{

        val recipeResponse = recipeService.create(payload)

        return WebResponse(
            code = 200,
            status = "Success",
            data = recipeResponse
        )
    }

    @GetMapping(
        value = ["/api/recipe/{id}"],
        produces = ["application/json"]
    )
    fun get(@PathVariable("id") id: String) : WebResponse<RecipeResponse>{
        val recipeResponse = recipeService.get(id)

        return WebResponse(
            code = 200,
            status = "Success",
            data = recipeResponse
        )
    }

    @PutMapping(
        value = ["/api/recipe/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun update(@PathVariable("id") id: String, @RequestBody updateRecipeRequest: UpdateRecipeRequest) : WebResponse<RecipeResponse>{
        val recipeResponse = recipeService.update(id, updateRecipeRequest)

        return WebResponse(
            code = 200,
            status = "Success",
            data = recipeResponse
        )
    }

    @DeleteMapping(
        value = ["/api/recipe/{id}"],
        produces = ["application/json"],
    )
    fun delete(@PathVariable("id") id: String) : WebResponse<String>{
        recipeService.delete(id)

        return WebResponse(
            code = 200,
            status = "Success",
            data = id
        )
    }

    @GetMapping(
        value = ["/api/recipe"],
        produces = ["application/json"]
    )
    fun list(@RequestParam(value = "size", defaultValue = "10") size: Int, @RequestParam(value = "page", defaultValue = "0") page: Int) : WebResponse<List<RecipeResponse>>{
        val request = ListRecipeRequest(size, page)
        val recipeResponse = recipeService.list(request)

        return WebResponse(
            code = 200,
            status = "Success",
            data = recipeResponse
        )
    }
}