package api.recipebook.recipe.payload

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)