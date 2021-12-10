package api.recipebook.recipe.service

import api.recipebook.recipe.payload.*

interface RecipeService {

    fun create(createRecipeRequest: CreateRecipeRequest): RecipeResponse
    fun get(id : String) : RecipeResponse
    fun update(id: String, updateRecipeRequest: UpdateRecipeRequest): RecipeResponse
    fun delete(id: String)
    fun list(listRecipeRequest: ListRecipeRequest) : ListRecipeResponse<RecipeResponse>
}