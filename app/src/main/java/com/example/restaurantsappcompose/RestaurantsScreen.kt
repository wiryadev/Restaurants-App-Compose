package com.example.restaurantsappcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RestaurantsScreen() {
    val viewModel: RestaurantsViewModel = viewModel()

    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        )
    ) {
        items(viewModel.getRestaurants()) { restaurant ->
            RestaurantItem(restaurant)
        }
    }
}

@Composable
fun RestaurantItem(
    item: Restaurant,
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            RestaurantIcon(
                icon = Icons.Rounded.Place,
                modifier = Modifier.weight(0.15f),
            )
            RestaurantDetails(
                title = item.title,
                description = item.description,
                modifier = Modifier.weight(0.70f)
            )
            FavoriteIcon(Modifier.weight(0.15f))
        }
    }
}

@Composable
private fun FavoriteIcon(modifier: Modifier) {
    var favoriteState by remember {
        mutableStateOf(false)
    }

    val icon = if (favoriteState) {
        Icons.Rounded.Favorite
    } else {
        Icons.Rounded.FavoriteBorder
    }

    Image(
        imageVector = icon,
        contentDescription = "Favorite restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable {
                favoriteState = !favoriteState
            },
    )
}

@Composable
private fun RestaurantIcon(
    icon: ImageVector,
    modifier: Modifier,
) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier.padding(8.dp),
    )
}

@Composable
private fun RestaurantDetails(
    title: String,
    description: String,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}