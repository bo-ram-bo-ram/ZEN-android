package com.iccas.zen.presentation.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iccas.zen.R
import com.iccas.zen.presentation.character.characterViewModel.CharacterViewModel
import com.iccas.zen.presentation.components.BasicBackgroundWithNavBar
import com.iccas.zen.presentation.home.components.TitleSticker


@Composable
fun CollectionScreen(
    navController: NavController,
    characterViewModel: CharacterViewModel = viewModel()
) {
    var selectedCharacter by remember { mutableStateOf<CharacterInfo?>(null) }
    val user = characterViewModel.user

    // 캐릭터 선택 로직
    val leaf = user?.leaf ?: 0

    // Leaf 수치에 따라 보여줄 캐릭터 수 계산
    var charactersToShowInSection1 = 0
    if(leaf >= 0)
         charactersToShowInSection1 = minOf(3, leaf / 100 + 1)
    var charactersToShowInSection2 = 0
    var charactersToShowInSection3 = 0
    if (leaf >= 300)
        charactersToShowInSection2 = minOf(3, (leaf - 300) / 100 + 1)
    if (leaf >= 600)
        charactersToShowInSection3 = minOf(3, (leaf - 600) / 100 + 1)


    BasicBackgroundWithNavBar(
        navController = navController
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        TitleSticker(R.drawable.home_title_blue_sticker, "Collection")
        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
        ) {
            item {
                // First section
                SectionTitle(charactersToShowInSection1.toString() + "/3 Flower", R.drawable.background2
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (index in 0 until charactersToShowInSection1) {
                        CollectionItem(
                            imageResId = Characters[index].charImgId,
                            label = Characters[index].name,
                            onClick = {
                                selectedCharacter = CharacterInfo(
                                    Characters[index].name,
                                    Characters[index].charImgId,
                                    "Mozzi is a soft friend.\n" +
                                            "mozzi loves ice cream"
                                )
                            }
                        )
                    }

                    if (charactersToShowInSection1 < 3) {
                        // Placeholder for remaining items
                        for (i in 0 until 3 - charactersToShowInSection1) {
                            CollectionItem(
                                imageResId = R.drawable.question_mark,
                                label = "?",
                                onClick = {}
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Second section
                SectionTitle(charactersToShowInSection2.toString() + "/3 House", R.drawable.background3)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (index in 3 until (3 + charactersToShowInSection2)) {
                        CollectionItem(
                            imageResId = Characters[index].charImgId,
                            label = Characters[index].name,
                            onClick = {
                                selectedCharacter = CharacterInfo(
                                    Characters[index].name,
                                    Characters[index].charImgId,
                                    "Character description here."
                                )
                            }
                        )
                    }
                    if (charactersToShowInSection2 < 3) {
                        // Placeholder for remaining items
                        for (i in 0 until 3 - charactersToShowInSection2) {
                            CollectionItem(
                                imageResId = R.drawable.question_mark,
                                label = "?",
                                onClick = {}
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                // Third section
                SectionTitle(charactersToShowInSection3.toString() + "/3 Room", R.drawable.background4)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (index in 6 until (6 + charactersToShowInSection3)) {
                        CollectionItem(
                            imageResId = Characters[index].charImgId,
                            label = Characters[index].name,
                            onClick = {
                                selectedCharacter = CharacterInfo(
                                    Characters[index].name,
                                    Characters[index].charImgId,
                                    "Character description here."
                                )
                            }
                        )
                    }
                    if (charactersToShowInSection3 < 3) {
                        // Placeholder for remaining items
                        for (i in 0 until 3 - charactersToShowInSection3) {
                            CollectionItem(
                                imageResId = R.drawable.question_mark,
                                label = "?",
                                onClick = {}
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Background Change Section
                SectionTitle2("Change Background")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // First background
                    if (leaf >= 200) {
                        CollectionItem(
                            imageResId = R.drawable.background2,
                            label = "flower",
                            onClick = {
                                characterViewModel.changeBackground(1)
                            }
                        )
                    } else {
                        CollectionItem(
                            imageResId = R.drawable.question_mark,
                            label = "?",
                            onClick = {}
                        )
                    }
                    // Second background
                    if (leaf >= 500) {
                        CollectionItem(
                            imageResId = R.drawable.background3,
                            label = "house",
                            onClick = {
                            characterViewModel.changeBackground(2)
                            }
                        )
                    } else {
                        CollectionItem(
                            imageResId = R.drawable.question_mark,
                            label = "?",
                            onClick = {}
                        )
                    }
                    // Third background
                    if (leaf >= 800) {
                        CollectionItem(
                            imageResId = R.drawable.background4,
                            label = "room",
                            onClick = {
                                characterViewModel.changeBackground(3)
                            }
                        )
                    } else {
                        CollectionItem(
                            imageResId = R.drawable.question_mark,
                            label = "?",
                            onClick = {}
                        )
                    }
                }
            }
        }
    }

    selectedCharacter?.let { character ->
        CharacterPopup(character = character, onDismiss = { selectedCharacter = null })
    }
}

@Composable
fun CollectionItem(
    imageResId: Int,
    label: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(Color.White)
            .border(1.dp, Color.Gray, RoundedCornerShape(10))
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10))
                .background(Color.White)
                .border(2.dp, Color.White, RoundedCornerShape(10))
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = label ?: "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentScale = ContentScale.Fit
            )
        }
        if (label != null) {
            Text(text = label, fontSize = 18.sp, color = Color.Black)
        } else {
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Composable
fun SectionTitle(text: String, iconResId: Int? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 20.sp, color = Color.Black)
        if (iconResId != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.size(24.dp)) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun SectionTitle2(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f) // 텍스트를 왼쪽 정렬하기 위한 weight 추가
        )
    }
}