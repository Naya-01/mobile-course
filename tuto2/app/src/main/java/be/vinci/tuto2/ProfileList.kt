package be.vinci.tuto2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



    @Composable
    fun ProfileList(profiles: List<Profile>, action: (Profile) -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Index") })
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                for (profile in profiles) {
                    Row(
                        modifier = Modifier
                            .clickable { action(profile) }
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "${profile.firstName} ${profile.lastName}",
                            style = MaterialTheme.typography.body1,
                        )
                    }
                    Divider()
                }
            }
        }
    }


