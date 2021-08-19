package api.recipebook.recipe.payload

data class ListRecipeRequest (
    val size: Int,
    val page: Int
)