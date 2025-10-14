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

package com.shourov.apps.pacedream.core.database.di

import android.content.Context
import com.shourov.apps.pacedream.core.database.PaceDreamDatabase
import com.shourov.apps.pacedream.core.database.dao.BookingDao
import com.shourov.apps.pacedream.core.database.dao.CategoryDao
import com.shourov.apps.pacedream.core.database.dao.ChatDao
import com.shourov.apps.pacedream.core.database.dao.MessageDao
import com.shourov.apps.pacedream.core.database.dao.PropertyDao
import com.shourov.apps.pacedream.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePaceDreamDatabase(
        @ApplicationContext context: Context
    ): PaceDreamDatabase {
        return PaceDreamDatabase.create(context)
    }

    @Provides
    fun provideUserDao(database: PaceDreamDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun providePropertyDao(database: PaceDreamDatabase): PropertyDao {
        return database.propertyDao()
    }

    @Provides
    fun provideBookingDao(database: PaceDreamDatabase): BookingDao {
        return database.bookingDao()
    }

    @Provides
    fun provideMessageDao(database: PaceDreamDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    fun provideCategoryDao(database: PaceDreamDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideChatDao(database: PaceDreamDatabase): ChatDao {
        return database.chatDao()
    }
}
