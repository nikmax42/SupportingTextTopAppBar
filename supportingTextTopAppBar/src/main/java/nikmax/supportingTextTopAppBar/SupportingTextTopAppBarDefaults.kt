package nikmax.supportingTextTopAppBar

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SupportingTextTopAppBarDefaults {
    object Small {
        val height: Dp = 64.dp
    }
    
    object Medium {
        val ExpandedHeight: Dp = 136.dp
        val CollapsedHeight: Dp = Small.height
    }
    
    object Large {
        val ExpandedHeight: Dp = 152.dp
        val CollapsedHeight: Dp = Small.height
    }
}
