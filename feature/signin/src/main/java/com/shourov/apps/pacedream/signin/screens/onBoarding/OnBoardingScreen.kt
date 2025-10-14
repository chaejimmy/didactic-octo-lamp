/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shourov.apps.pacedream.signin.screens.onBoarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pacedream.common.composables.VerticalSpacer
import com.shourov.apps.pacedream.feature.signin.R
import com.shourov.apps.pacedream.signin.navigation.SignInRoutes
import com.shourov.apps.pacedream.signin.screens.onBoarding.components.CustomDotIndicator
import com.shourov.apps.pacedream.signin.screens.onBoarding.components.OnBoardingScreenItems
import com.shourov.apps.pacedream.signin.screens.onBoarding.components.OnBoardingScreenItems.onBoardingScreenItems
import com.shourov.apps.pacedream.signin.screens.onBoarding.components.SwipeAbleOnBoardingItemDescription
import com.shourov.apps.pacedream.signin.screens.onBoarding.components.SwipeAbleOnBoardingItemImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    val pagerState = rememberPagerState(
        pageCount = { onBoardingScreenItems.size },
        // take middle item as the initial page
        initialPage = 0//OnBoardingScreenItems.onBoardingScreenItems.size / 2,
    )
    
    LaunchedEffect(key1 = Unit) {
        while (true){
            yield()
            delay(2500)
            val targetPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(targetPage)

        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
        ) {
            HorizontalPager(
                pageSize = PageSize.Fill,
                state = pagerState,
            ) { index ->
                val item = onBoardingScreenItems[index]
                SwipeAbleOnBoardingItemImage(image = item.image)
            }

            VerticalSpacer(height = 10)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            }

            SwipeAbleOnBoardingItemDescription(
                title = onBoardingScreenItems[pagerState.currentPage].title,
                description = onBoardingScreenItems[pagerState.currentPage].description,
            )

            VerticalSpacer(height = 10)

            CustomDotIndicator(
                currentPage = pagerState.currentPage,
                numberOfPages = onBoardingScreenItems.size,
                pagerState = pagerState,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                //.padding(bottom = 30.dp)
                contentAlignment = Alignment.BottomCenter,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    OutlinedButton(
                        onClick = {
                            /*navigate to create account*/
                            navHostController.navigate(route = SignInRoutes.CREATE_ACCOUNT.name)
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(id = R.string.feature_signin_onboarding_create_account),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }

                    Button(
                        onClick = {
                            /* navigate to sign in*/
                            navHostController.navigate(route = SignInRoutes.SIGN_IN.name)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground,
                            contentColor = MaterialTheme.colorScheme.background,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = WindowInsets.systemBars
                                    .asPaddingValues()
                                    .calculateBottomPadding()
                            ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.feature_signin_onboarding_sign_in).uppercase(),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun OnBoardingPrev(){
    //OnBoardingScreen()
}