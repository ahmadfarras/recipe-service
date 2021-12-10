package api.recipebook.recipe.payload

data class ListRecipeResponse<T> (
    val page: Int,
    val totalPage: Int,
    val totalData: Long,
    val list: List<T>
    )