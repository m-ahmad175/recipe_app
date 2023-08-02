package com.az.reciepeapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.az.reciepeapp.domain.model.Recipe
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeDetailView(recipe: Recipe) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        recipe.featured_image?.let { url ->
            GlideImage(
                model = url, contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .height(260.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            recipe.title?.let { tittle ->
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = tittle, modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                    val rank = recipe.rating.toString()
                    Text(
                        text = rank, modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }
                recipe.publisher?.let {
                    val update = recipe.date_updated
                    Text(
                        text = if (update != null) {
                            "Update $update by $it"
                        } else {
                            "By $it"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                for (ingredient in recipe.ingredients!!) {

                    Text(
                        text = ingredient, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}