package com.example.pokedex.ui.components



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier


@Composable
fun RoundedStatBar(value: Int, color: Color, stat: String) {
    val maxStatValue = 300
    val statPercentage = (value / maxStatValue.toFloat())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stat,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(50.dp)

        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)

        ) {

            drawRoundRect(
                color = Color.White,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )

            drawRoundRect(
                color = color,
                size = Size(size.width * statPercentage, size.height),
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )

            val textPaint = Paint().asFrameworkPaint()
            textPaint.textSize = 14.sp.toPx()
            textPaint.textAlign = android.graphics.Paint.Align.CENTER

            val textX = size.width / 2
            val textY = size.height / 2 + textPaint.textSize / 2

            drawContext.canvas.nativeCanvas.drawText(
                "$value / $maxStatValue",
                textX,
                textY,
                textPaint
            )
        }
    }
}