package apps.currency_converter_udemy.domain.model

import androidx.compose.ui.graphics.Color
import apps.currency_converter_udemy.ui.theme.freshColor
import apps.currency_converter_udemy.ui.theme.staleColor

enum class RateStatus(
    val title: String,
    val color: Color
) {
    Idle(
        title = "Rates",
        color = Color.White
    ),
    Fresh(
        title = "Fresh rates",
        color = freshColor
    ),
    Stale(
        title = "Rates are not fresh",
        color = staleColor
    )
}