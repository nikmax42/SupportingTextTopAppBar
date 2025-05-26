package nikmax.supportingTextTopAppBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeSupportingTextTopBar(
    title: @Composable (() -> Unit),
    supportingText: @Composable (RowScope.() -> Unit),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
    collapsedHeight: Dp = SupportingTextTopAppBarDefaults.Large.CollapsedHeight,
    expandedHeight: Dp = SupportingTextTopAppBarDefaults.Large.ExpandedHeight,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors()
) {
    val isExpanded by remember {
        derivedStateOf {
            when (scrollBehavior != null) {
                true -> scrollBehavior.state.collapsedFraction < 0.5
                false -> true
            }
        }
    }
    LargeTopAppBar(
        title = {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    when (isExpanded) {
                        true -> 4.dp
                        false -> 0.dp
                    }
                )
            ) {
                ProvideTextStyle(
                    when (isExpanded) {
                        true -> MaterialTheme.typography.displaySmall
                        false -> MaterialTheme.typography.titleLarge
                    }
                ) {
                    title()
                }
                ProvideTextStyle(
                    when (isExpanded) {
                        true -> MaterialTheme.typography.titleMedium
                        false -> MaterialTheme.typography.labelMedium
                    }
                ) {
                    Row {
                        supportingText()
                    }
                }
            }
        },
        navigationIcon = { navigationIcon() },
        actions = { actions() },
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            LargeSupportingTextTopBar(
                title = { Text("Title text") },
                supportingText = { Text("Some supporting text") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddings ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddings)
                .verticalScroll(scrollState)
        ) {
            repeat(30) {
                Box(Modifier.padding(16.dp)) {
                    Text("Item $it")
                }
            }
        }
    }
}
