package be.vinci.exo2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.vinci.exo2.ui.theme.Exo2Theme

@Composable
fun NoteScreen(note: Note, back: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Show Note") },
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
                    text = note.title,
                    style = MaterialTheme.typography.h5,
                )

            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = note.content,
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
fun DefaultPreviewNoteScreen() {
    Exo2Theme() {
        Column() {
            NoteScreen(getNote()[1]){}
        }
    }
}