package be.vinci.tuto2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.vinci.tuto2.ui.theme.Tuto2Theme

@Composable
fun ProfileScreen(profile: Profile, back: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) {
        Column(modifier = Modifier.padding(32.dp)) {
            Row {
                Text(
                    text = profile.firstName,
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = profile.lastName,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    text = profile.age.toString(),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.alpha(0.6f),
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = profile.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify,
                letterSpacing = 1.sp,
                lineHeight = 20.sp,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreviewProfileScreen() {
    Tuto2Theme {
        Column() {
            ProfileScreen(getProfiles()[1]){}
        }
    }
}