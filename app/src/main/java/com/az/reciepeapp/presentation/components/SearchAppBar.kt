package com.az.reciepeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.az.reciepeapp.presentation.ui.recipe_list.FoodCategory
import com.az.reciepeapp.presentation.ui.recipe_list.getAllCategories

// state hoisting --> making a composable such that it can be used anywhere w want

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit, selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onToggleTheme: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column() {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.background)
            ) {
                TextField(

                    value = query,

                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp)
                        .background(MaterialTheme.colors.background),
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "")
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()

                            keyboardController?.hide()
                        }
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                    )
                )
                ConstraintLayout(modifier = Modifier.align(Alignment.CenterVertically)) {

                    val menu = createRef()
                    IconButton(onClick = onToggleTheme,
                        modifier = Modifier.constrainAs(menu) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {

                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu Icon")
                    }
                }

            }
            val scrollState = rememberScrollState()
            LazyRow(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .background(MaterialTheme.colors.background)
                    .scrollable(scrollState, orientation = Orientation.Horizontal)
            ) {
                items(getAllCategories()) { item ->
                    FoodCategoryChip(category = item.value,
                        isSelected = selectedCategory == item,
                        onExecuteSearch = onExecuteSearch,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                        })
                }
            }
        }


    }
}