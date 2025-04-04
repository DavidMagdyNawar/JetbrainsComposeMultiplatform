// commonMain/kotlin/com/example/ui/DataScreen.kt
package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.network.ApiRepository
import com.example.network.DataModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun DataScreen() {
    // Inject the repository using Koin
    val repository = koinInject<ApiRepository>()
    val coroutineScope = rememberCoroutineScope()

    // Collect flow as state
    val dataItems by repository.observeDataChanges().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Data from API",
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    repository.getData()
                }
            }
        ) {
            Text("Refresh Data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (dataItems.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(dataItems) { item ->
                    DataItemCard(item)
                }
            }
        }
    }
}

@Composable
fun DataItemCard(item: DataModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
