package apps.currency_converter_udemy.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import apps.currency_converter_udemy.domain.model.Currency
import apps.currency_converter_udemy.domain.model.CurrencyCode
import apps.currency_converter_udemy.domain.model.CurrencyType
import apps.currency_converter_udemy.domain.model.DisplayResult
import apps.currency_converter_udemy.domain.model.RateStatus
import apps.currency_converter_udemy.domain.model.RequestState
import apps.currency_converter_udemy.ui.theme.headerColor
import apps.currency_converter_udemy.ui.theme.staleColor
import apps.currency_converter_udemy.utils.displayCurrentDateTime
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.compose_multiplatform
import composemultiplatformsamples.composeapp.generated.resources.ic_currency_exchange
import composemultiplatformsamples.composeapp.generated.resources.ic_swap
import getPlatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    rateStatus: RateStatus,
    source: RequestState<Currency>,
    target: RequestState<Currency>,
    amount: Double,
    onRatesRefresh: () -> Unit,
    onSwitchClick: () -> Unit,
    onAmountChange: (Double) -> Unit,
    onCurrencyTypeSelect: (CurrencyType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .background(color = headerColor)
            .padding(top = if (getPlatform().name == "Android") 0.dp else 24.dp)
            .padding(all = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(height = 24.dp))
        RatesStatus(
            status = rateStatus,
            onRatesRefresh = onRatesRefresh
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
        CurrencyInputs(
            source = source,
            target = target,
            onSwitchClick = onSwitchClick,
            onCurrencyTypeSelect = onCurrencyTypeSelect
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
        AmountInput(
            amount = amount,
            onAmountChange = onAmountChange
        )
    }
}

@Composable
fun RatesStatus(
    modifier: Modifier = Modifier,
    status: RateStatus,
    onRatesRefresh: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                modifier = Modifier.size(size = 50.dp),
                painter = painterResource(resource = Res.drawable.ic_currency_exchange),
                tint = Color.White,
                contentDescription = "Exchange Rates Illustration"
            )
            Spacer(modifier = Modifier.width(width = 12.dp))
            Column {
                Text(
                    text = displayCurrentDateTime(),
                    color = Color.White
                )
                Text(
                    text = status.title,
                    color = status.color,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            }
        }
        if (status == RateStatus.Stale) {
            IconButton(onClick = onRatesRefresh) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    tint = staleColor,
                    contentDescription = "Refresh Icon"
                )
            }
        }
    }
}

@Composable
fun CurrencyInputs(
    modifier: Modifier = Modifier,
    source: RequestState<Currency>,
    target: RequestState<Currency>,
    onSwitchClick: () -> Unit,
    onCurrencyTypeSelect: (CurrencyType) -> Unit
) {

    var rotationToggle by remember { mutableStateOf(false) }
    val rotateAnimation = animateFloatAsState(
        targetValue = if (rotationToggle) 180f else 0f
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CurrencyView(
            placeholder = "From",
            currency = source,
            onClick = {
                if (source.isSuccess()) {
                    onCurrencyTypeSelect(
                        CurrencyType.Source(CurrencyCode.valueOf(source.getSuccessData().code))
                    )
                }
            }
        )
        Spacer(modifier = Modifier.width(width = 14.dp))
        IconButton(modifier = Modifier
            .graphicsLayer {
                rotationY = rotateAnimation.value
            }, onClick = {
            rotationToggle = !rotationToggle
            onSwitchClick()
        }) {
            Icon(
                painter = painterResource(Res.drawable.ic_swap),
                tint = Color.White,
                contentDescription = "Switch Icon"
            )
        }
        Spacer(modifier = Modifier.width(width = 14.dp))
        CurrencyView(
            placeholder = "To",
            currency = target,
            onClick = {
                if (target.isSuccess()) {
                    onCurrencyTypeSelect(
                        CurrencyType.Target(CurrencyCode.valueOf(target.getSuccessData().code))
                    )
                }
            }
        )
    }
}

@Composable
fun RowScope.CurrencyView(
    placeholder: String,
    currency: RequestState<Currency>,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.weight(weight = 1f)) {
        Text(
            text = placeholder,
            color = Color.White,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            modifier = Modifier.padding(start = 12.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .background(Color.White.copy(alpha = 0.05f))
                .height(54.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            currency.DisplayResult(
                onSuccess = { data ->
                    Icon(
                        modifier = Modifier.size(size = 24.dp),
                        painter = painterResource(
                            Res.drawable.compose_multiplatform
                        ),
                        contentDescription = "Country Flag"
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(
                        text = CurrencyCode.valueOf(data.code).name,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = Color.White
                    )
                }
            )
        }
    }
}

@Composable
fun AmountInput(
    modifier: Modifier = Modifier,
    amount: Double,
    onAmountChange: (Double) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .animateContentSize()
            .height(54.dp),
        value = "$amount",
        onValueChange = {
            onAmountChange(it.toDouble())
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.05f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
            disabledContainerColor = Color.White.copy(alpha = 0.05f),
            errorContainerColor = Color.White.copy(alpha = 0.05f),
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        ),
        textStyle = TextStyle(
            color = Color.White,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}