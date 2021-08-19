package api.recipebook.recipe.payload

data class RecipeResponse(

    val id: String,
    val name: String,
    val ingredient: String,
    val step: String,
    val createdDate: String,
    val lastUpdated: String?
)