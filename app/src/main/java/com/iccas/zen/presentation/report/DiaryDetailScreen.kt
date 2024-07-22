package com.iccas.zen.presentation.report

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iccas.zen.R
import com.iccas.zen.presentation.components.BasicBackgroundWithLogo
import com.iccas.zen.presentation.components.TitleWithHighligher
import com.iccas.zen.presentation.report.reportComponents.ReportTitle
import com.iccas.zen.presentation.report.viewModel.ReportViewModel

@Composable
fun DiaryDetailScreen(
    navController: NavController,
    emotionDiaryId: Long,
    reportViewModel: ReportViewModel = viewModel()
) {
    val context = LocalContext.current
    val diaryDetail by reportViewModel.diaryDetail.collectAsState()
    val diaryByEmotion by reportViewModel.diaryByEmotion.collectAsState()

    LaunchedEffect(emotionDiaryId) {
        reportViewModel.getDiaryDetail(emotionDiaryId)
    }

    LaunchedEffect(diaryDetail) {
        diaryDetail?.data?.emotion?.let { emotion ->
            reportViewModel.getDiaryByEmotion(emotion)
        }
    }

    BasicBackgroundWithLogo {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                ReportTitle(backOnClick = { navController.navigateUp() }, highlightText = "Emotional Diary")

                diaryDetail?.data?.let { detail ->
                    TitleWithHighligher(title = "Analysis", highLighterWidth = 100.dp)
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            TitleWithHighligher(title = "When", highLighterWidth = 75.dp)
                            Text(text = detail.whenDetail, fontSize = 18.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TitleWithHighligher(title = "Emotion", highLighterWidth = 100.dp)
                            val emotionImageRes = getEmotionImageRes(detail.emotion ?: "Soso")
                            Image(
                                painter = painterResource(id = emotionImageRes), // 감정 이미지 리소스
                                contentDescription = "Emotion",
                                modifier = Modifier.size(32.dp)
                            )
                            Text(text = detail.emotion ?: "Unknown", fontSize = 18.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    TitleWithHighligher(title = "Summary", highLighterWidth = 110.dp)
                    Text(
                        text = detail.summary,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TitleWithHighligher(title = "Solution", highLighterWidth = 100.dp)
                    Text(
                        text = detail.solution,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    TitleWithHighligher(title = "Emotion Frequency", highLighterWidth = 210.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            diaryByEmotion.forEach { date ->
                                Text(text = date, fontSize = 10.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            repeat(diaryByEmotion.size) {
                                val emotionImageRes = getEmotionImageRes(detail.emotion ?: "Soso")
                                Image(
                                    painter = painterResource(id = emotionImageRes), // 감정 이미지 리소스
                                    contentDescription = detail.emotion,
                                    modifier = Modifier.size(32.dp)
                                )
                                if (it < diaryByEmotion.size - 1) {
                                    DashedDivider()
                                }
                            }
                        }
                    }
                } ?: run {
                    Text(
                        text = "Loading...",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DashedDivider() {
    Canvas(modifier = Modifier
        .width(24.dp)
        .height(1.dp)) {
        val paint = Paint().apply {
            color = Color.Gray
        }
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
        }
        drawPath(
            path = path,
            color = Color.Gray,
            style = Stroke(
                width = 4f,
                pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 10f),
                    phase = 0f
                )
            )
        )
    }
}

fun getEmotionImageRes(emotion: String): Int {
    return when (emotion) {
        "Angry" -> R.drawable.chat_angry
        "Happy" -> R.drawable.chat_happy
        "Soso" -> R.drawable.chat_soso
        "Sad" -> R.drawable.chat_sad
        else -> R.drawable.chat_soso // 기본 이미지 설정
    }
}