package api.recipebook.recipe.repository

import api.recipebook.recipe.entity.Recipe
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository:MongoRepository<Recipe, String> {
}