package api.recipebook.recipe.payload

import javax.validation.constraints.NotBlank

data class CreateRecipeRequest (

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val ingredient: String?,

    @field:NotBlank
    val step: String?
)