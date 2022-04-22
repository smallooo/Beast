package com.thebeastshop.beast.ui.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.customview.R
import com.thebeastshop.beast.data.DemoDataProvider.item
import com.thebeastshop.beast.theme.LoginBackground
import com.thebeastshop.beast.ui.settings.SettingsActivity
import com.thebeastshop.beast.ui.theme.login_background
import com.thebeastshop.beast.ui.theme.login_buttom_background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(darkTheme: Boolean) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f)

    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(color = LoginBackground)
        ) {
            if (true) {
                Column() {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        val imageModifierSetting = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .padding(6.dp)
                            .clickable {
                                context.startActivity(
                                    SettingsActivity.newIntent(
                                        context,
                                        true
                                    )
                                )
                            }
                            .clip(shape = androidx.compose.material.MaterialTheme.shapes.medium)
                        val imageModifierMessages = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .padding(6.dp)
                            .clickable {
                                context.startActivity(
                                    SettingsActivity.newIntent(
                                        context,
                                        true
                                    )
                                )
                            }
                            .clip(shape = androidx.compose.material.MaterialTheme.shapes.medium)
                        Image(
                            painter = painterResource(item.imageId),
                            modifier = imageModifierSetting,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                        Image(
                            painter = painterResource(item.imageId),
                            modifier = imageModifierMessages,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Box {
                        val imageModifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .padding(30.dp, 20.dp, 30.dp, 0.dp)
                            .clip(RoundedCornerShape(8.dp))

                        Image(
                            painter = painterResource(com.thebeastshop.beast.R.drawable.bg_account_index_top_view),
                            modifier = imageModifier,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )

                        val imageModifier1 = Modifier
                            .height(15.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)

                        Image(
                            painter = painterResource(com.thebeastshop.beast.R.drawable.bg_account_index_top_view_bottom_circle),
                            modifier = imageModifier1,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = "野兽派会员中心",
                            color = login_background,
                            modifier = Modifier.padding(48.dp, 28.dp, 0.dp, 0.dp)
                        )

                        val imageModifier2 = Modifier
                            .width(171.dp)
                            .height(180.dp)
                            .padding(4.dp, 10.dp, 0.dp, 0.dp)
                            .align(Alignment.BottomEnd)

                        Image(
                            painter = painterResource(com.thebeastshop.beast.R.drawable.bg_icon_account_index_logo),
                            modifier = imageModifier2,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .padding(79.dp, 130.dp, 79.dp, 0.dp)
                                .clip(shape = RoundedCornerShape(26.dp)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = login_buttom_background,
                                contentColor = Color.Black
                            ),
                        ) {
                            Text(
                                text = "立即加入 解锁权益",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }

        val imageModifier2 = Modifier
            .width(44.dp)
            .height(44.dp)
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .align(Alignment.CenterHorizontally)

        Row(modifier = Modifier.height(390.dp).padding(0.dp, 30.dp, 0.dp, 0.dp)) {
            Column(Modifier.weight(1f)){
                Image(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_account_index_order_normal),
                    modifier = imageModifier2,
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
                Text(text = stringResource(id = com.thebeastshop.beast.R.string.account_order), color = Color.Black
                , modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Column(Modifier.weight(1f)
            ) {
                Image(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_account_index_coupon_normal),
                    modifier = imageModifier2,
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
                Text(text = stringResource(id = com.thebeastshop.beast.R.string.account_coupon), color = Color.Black
                    , modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Column(Modifier.weight(1f)){
                Image(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_account_index_favorite_normal),
                    modifier = imageModifier2,
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
                Text(text = stringResource(id = com.thebeastshop.beast.R.string.account_favorite), color = Color.Black
                    , modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Column(Modifier.weight(1f)
            ) {
                Image(painter = painterResource(com.thebeastshop.beast.R.drawable.icon_account_index_service_normal),
                    modifier = imageModifier2,
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
                Text(text = stringResource(id = com.thebeastshop.beast.R.string.account_service), color = Color.Black
                    , modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}