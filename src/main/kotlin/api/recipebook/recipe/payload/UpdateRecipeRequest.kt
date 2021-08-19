package api.recipebook.recipe.payload

import javax.validation.constraints.NotBlank

data class UpdateRecipeRequest(

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val ingredient: String?,

    @field:NotBlank
    val step: String?
)