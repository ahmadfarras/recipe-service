package api.recipebook.recipe.service.impl

import api.recipebook.recipe.entity.Recipe
import api.recipebook.recipe.error.NotFoundException
import api.recipebook.recipe.payload.*
import api.recipebook.recipe.repository.RecipeRepository
import api.recipebook.recipe.service.RecipeService
import api.recipebook.recipe.utility.GeneralUtil
import api.recipebook.recipe.utility.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

@Service
class RecipeServiceImpl(
    val recipeRepository: RecipeRepository,
    val validationUtil: ValidationUtil,
    val generalUtil: GeneralUtil
    ) : RecipeService {

    @Transactional
    override fun create(createRecipeRequest: CreateRecipeRequest): RecipeResponse {
        validationUtil.validate(createRecipeRequest)

        val recipe = Recipe(
            id = generalUtil.generateRecipeId("recipe_sequence"),
            name = createRecipeRequest.name!!,
            ingredient = createRecipeRequest.ingredient!!,
            step = createRecipeRequest.step!!,
            createdDate = Date(),
            lastUpdated = null
        )

        recipeRepository.save(recipe)

        return convertDataRecipe(recipe)
    }

    override fun get(id: String): RecipeResponse {
        val recipe = findProductByIdOrThrowNotFound(id)
        return convertDataRecipe(recipe)
    }

    override fun list(listRecipeRequest: ListRecipeRequest): ListRecipeResponse<RecipeResponse> {
        val page = recipeRepository.findAll(PageRequest.of(listRecipeRequest.page, listRecipeRequest.size))
        val recipe : List<Recipe> = page.get().collect(Collectors.toList())
        return ListRecipeResponse(listRecipeRequest.page, page.totalPages, page.totalElements, recipe.map { convertDataRecipe(it) })
    }

    @Transactional
    override fun update(id: String, updateRecipeRequest: UpdateRecipeRequest): RecipeResponse {
        validationUtil.validate(updateRecipeRequest)

        val recipe = findProductByIdOrThrowNotFound(id)

        recipe.apply {
            name = updateRecipeRequest.name.toString()
            ingredient = updateRecipeRequest.ingredient.toString()
            step = updateRecipeRequest.step.toString()
            lastUpdated = Date()
        }
        recipeRepository.save(recipe)

        return convertDataRecipe(recipe)

    }

    @Transactional
    override fun delete(id: String) {
        val recipe = findProductByIdOrThrowNotFound(id)

        recipeRepository.delete(recipe)
    }

    private fun findProductByIdOrThrowNotFound(id: String) : Recipe{
        val recipe = recipeRepository.findByIdOrNull(id)

        if (recipe == null){
            throw NotFoundException()
        }else{
            return recipe
        }
    }

    private fun convertDataRecipe(recipe: Recipe) : RecipeResponse{
        return RecipeResponse(
            id = recipe.id,
            name = recipe.name,
            ingredient = recipe.ingredient,
            step = recipe.step,
            createdDate = convertDate(recipe.createdDate),
            lastUpdated = recipe.lastUpdated?.let { convertDate(it) }
        )
    }

    private fun convertDate(date: Date): String{
        val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
        return dateFormat.format(date)
    }
}