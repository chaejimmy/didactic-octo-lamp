package com.shourov.apps.pacedream.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.shourov.apps.pacedream.feature.home.presentation.DashboardScreen
import com.shourov.apps.pacedream.feature.home.presentation.HomeScreenViewModel
import com.shourov.apps.pacedream.feature.home.presentation.components.PropertyDetailScreen
import com.shourov.apps.pacedream.feature.home.presentation.components.EnhancedDashboardScreen
import com.shourov.apps.pacedream.feature.home.presentation.components.SearchScreen
import com.shourov.apps.pacedream.feature.home.presentation.components.FilterScreen
import com.shourov.apps.pacedream.feature.home.presentation.components.CategoryListScreen
import com.shourov.apps.pacedream.feature.home.presentation.components.DestinationListScreen
import com.shourov.apps.pacedream.feature.home.presentation.components.RecentSearchesScreen
import com.shourov.apps.pacedream.feature.booking.presentation.BookingTabScreen
import com.shourov.apps.pacedream.feature.host.presentation.PostTabScreen
import com.shourov.apps.pacedream.feature.chat.presentation.InboxTabScreen
import com.shourov.apps.pacedream.feature.profile.presentation.ProfileTabScreen
import com.shourov.apps.pacedream.signin.navigation.DASHBOARD_ROUTE

fun NavGraphBuilder.DashboardNavigation(
    hostModeManager: com.shourov.apps.pacedream.feature.host.domain.HostModeManager
) {
    with(this) {
        navigation(
            startDestination = DashboardDestination.HOME.name,
            route = DASHBOARD_ROUTE,
        ) {
            composable(route = DashboardDestination.HOME.name) {
                val navController = rememberNavController()
                val bottomNavList = mutableListOf(
                    BottomNavigationItem(
                        com.shourov.apps.pacedream.R.drawable.ic_home,
                        com.shourov.apps.pacedream.R.string.home,
                    ),
                    BottomNavigationItem(
                        com.shourov.apps.pacedream.R.drawable.ic_booking,
                        com.shourov.apps.pacedream.R.string.booking,
                    ),
                    BottomNavigationItem(
                        com.shourov.apps.pacedream.R.drawable.ic_post,
                        com.shourov.apps.pacedream.R.string.post,
                    ),
                    BottomNavigationItem(
                        com.shourov.apps.pacedream.R.drawable.ic_notifications,
                        com.shourov.apps.pacedream.R.string.inbox,
                    ),
                    BottomNavigationItem(
                        com.shourov.apps.pacedream.R.drawable.ic_profile,
                        com.shourov.apps.pacedream.R.string.profile,
                    ),
                )
                bottomNavList.apply {
                    val bottomNavigationItems = remember {
                        bottomNavList
                    }

                    val backStackState = navController.currentBackStackEntryAsState().value
                    var selectedItem by rememberSaveable {
                        mutableIntStateOf(0)
                    }

                    val isBottomBarShow = remember(key1 = backStackState) {
                        backStackState?.destination?.route == DashboardDestination.HOME.name ||
                            backStackState?.destination?.route == DashboardDestination.BOOKING.name ||
                            backStackState?.destination?.route == DashboardDestination.POST.name ||
                            backStackState?.destination?.route == DashboardDestination.INBOX.name ||
                            backStackState?.destination?.route == DashboardDestination.PROFILE.name
                    }

                    selectedItem = remember(backStackState) {
                        when (backStackState?.destination?.route) {
                            DashboardDestination.HOME.name -> 0
                            DashboardDestination.BOOKING.name -> 1
                            DashboardDestination.POST.name -> 2
                            DashboardDestination.INBOX.name -> 3
                            else -> 4
                        }
                    }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (isBottomBarShow)
                                AppBottomNavigation(
                                    items = bottomNavigationItems,
                                    selectedIndex = selectedItem,
                                    onItemClick = { index ->
                                        when (index) {
                                            0 -> {
                                                navigateToTab(
                                                    navController,
                                                    DashboardDestination.HOME.name,
                                                )
                                            }

                                            1 -> {
                                                navigateToTab(
                                                    navController,
                                                    DashboardDestination.BOOKING.name,
                                                )
                                            }

                                            2 -> {
                                                navigateToTab(
                                                    navController,
                                                    DashboardDestination.POST.name,
                                                )
                                            }

                                            3 -> {
                                                navigateToTab(
                                                    navController,
                                                    DashboardDestination.INBOX.name,
                                                )
                                            }

                                            4 -> {
                                                navigateToTab(
                                                    navController,
                                                    DashboardDestination.PROFILE.name,
                                                )
                                            }
                                        }

                                    },
                                )

                        },
                    ) {
                        val bottomPadding = it.calculateBottomPadding()

                        NavHost(
                            navController = navController,
                            startDestination = DashboardDestination.HOME.name,
                            modifier = Modifier.padding(bottom = bottomPadding),
                        ) {
                            composable(DashboardDestination.HOME.name) {
                                val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                                val homeScreenRoomsState =
                                    homeScreenViewModel.homeScreenRoomsState.collectAsStateWithLifecycle()
                                val homeScreenRentedGearsState =
                                    homeScreenViewModel.homeScreenRentedGearsState.collectAsStateWithLifecycle()
                                
                                // Use the enhanced dashboard screen with navigation
                                EnhancedDashboardScreen(
                                    roomsState = homeScreenRoomsState.value,
                                    gearsState = homeScreenRentedGearsState.value,
                                    onTimeBasedRoomsChanged = { type ->
                                        homeScreenViewModel.onEvent(
                                            com.shourov.apps.pacedream.feature.home.presentation.HomeScreenEvent.OnTimeBasedRoomsChanged(type)
                                        )
                                    },
                                    onRentedGearsChanged = { type ->
                                        homeScreenViewModel.onEvent(
                                            com.shourov.apps.pacedream.feature.home.presentation.HomeScreenEvent.OnRentedGearsChanged(type)
                                        )
                                    },
                                    onPropertyClick = { propertyId ->
                                        navController.navigate("${PropertyDestination.DETAIL.name}/$propertyId")
                                    },
                                    onCategoryClick = { category ->
                                        navController.navigate("${PropertyDestination.CATEGORY_LIST.name}/$category")
                                    },
                                    onViewAllClick = { section ->
                                        when (section) {
                                            "categories" -> navController.navigate(PropertyDestination.CATEGORY_LIST.name)
                                            "destinations" -> navController.navigate(PropertyDestination.DESTINATION_LIST.name)
                                            "recent" -> navController.navigate(PropertyDestination.RECENT_SEARCHES.name)
                                            "time-based" -> navController.navigate(PropertyDestination.SEARCH.name)
                                            "gear" -> navController.navigate(PropertyDestination.SEARCH.name)
                                            else -> { /* Handle other sections */ }
                                        }
                                    },
                                    onSearchClick = {
                                        navController.navigate(PropertyDestination.SEARCH.name)
                                    },
                                    onFilterClick = {
                                        navController.navigate(PropertyDestination.FILTER.name)
                                    },
                                    onNotificationClick = {
                                        navController.navigate(DashboardDestination.INBOX.name)
                                    }
                                )
                            }
                            composable(DashboardDestination.BOOKING.name) {
                                BookingTabScreen(
                                    onBookingClick = { bookingId ->
                                        // Navigate to booking details
                                    },
                                    onNewBookingClick = {
                                        // Navigate to property search
                                    }
                                )
                            }
                            composable(DashboardDestination.POST.name) {
                                PostTabScreen(
                                    onPropertyClick = { propertyId ->
                                        // Navigate to property details
                                    },
                                    onAddPropertyClick = {
                                        // Navigate to add property
                                    },
                                    onAnalyticsClick = {
                                        // Navigate to analytics
                                    },
                                    onEarningsClick = {
                                        // Navigate to earnings
                                    }
                                )
                            }
                            composable(DashboardDestination.INBOX.name) {
                                InboxTabScreen(
                                    onChatClick = { chatId ->
                                        // Navigate to chat
                                    },
                                    onNewMessageClick = {
                                        // Navigate to new message
                                    }
                                )
                            }
                            composable(DashboardDestination.PROFILE.name) {
                                val isHostMode by hostModeManager.isHostMode.collectAsState()
                                ProfileTabScreen(
                                    onEditProfileClick = {
                                        // Navigate to edit profile
                                    },
                                    onSettingsClick = {
                                        // Navigate to settings
                                    },
                                    onHelpClick = {
                                        // Navigate to help
                                    },
                                    onAboutClick = {
                                        // Navigate to about
                                    },
                                    onLogoutClick = {
                                        // Handle logout
                                    },
                                    onSwitchToHostMode = {
                                        hostModeManager.setHostMode(true)
                                    },
                                    onSwitchToGuestMode = {
                                        hostModeManager.setHostMode(false)
                                    },
                                    isHostMode = isHostMode
                                )
                            }
                            
                            // Property Detail Screen
                            composable(
                                route = "${PropertyDestination.DETAIL.name}/{propertyId}",
                                arguments = listOf(
                                    navArgument("propertyId") {
                                        type = NavType.StringType
                                    }
                                )
                            ) { backStackEntry ->
                                val propertyId = backStackEntry.arguments?.getString("propertyId") ?: ""
                                PropertyDetailScreen(
                                    propertyId = propertyId,
                                    onBackClick = { navController.popBackStack() },
                                    onBookClick = { 
                                        navController.navigate("${BookingDestination.BOOKING_FORM.name}/$propertyId")
                                    },
                                    onShareClick = { /* Handle share */ },
                                    onFavoriteClick = { /* Handle favorite */ }
                                )
                            }
                            
                            // Search Screen
                            composable(PropertyDestination.SEARCH.name) {
                                SearchScreen(
                                    onBackClick = { navController.popBackStack() },
                                    onPropertyClick = { propertyId ->
                                        navController.navigate("${PropertyDestination.DETAIL.name}/$propertyId")
                                    }
                                )
                            }
                            
                            // Filter Screen
                            composable(PropertyDestination.FILTER.name) {
                                FilterScreen(
                                    onBackClick = { navController.popBackStack() },
                                    onApplyFilters = { /* Handle filter application */ }
                                )
                            }
                            
                            // Category List Screen
                            composable(
                                route = "${PropertyDestination.CATEGORY_LIST.name}/{category}",
                                arguments = listOf(
                                    navArgument("category") {
                                        type = NavType.StringType
                                    }
                                )
                            ) { backStackEntry ->
                                val category = backStackEntry.arguments?.getString("category") ?: ""
                                CategoryListScreen(
                                    category = category,
                                    onBackClick = { navController.popBackStack() },
                                    onPropertyClick = { propertyId ->
                                        navController.navigate("${PropertyDestination.DETAIL.name}/$propertyId")
                                    }
                                )
                            }
                            
                            // Destination List Screen
                            composable(PropertyDestination.DESTINATION_LIST.name) {
                                DestinationListScreen(
                                    onBackClick = { navController.popBackStack() },
                                    onDestinationClick = { destination ->
                                        navController.navigate("${PropertyDestination.SEARCH.name}?destination=$destination")
                                    }
                                )
                            }
                            
                            // Recent Searches Screen
                            composable(PropertyDestination.RECENT_SEARCHES.name) {
                                RecentSearchesScreen(
                                    onBackClick = { navController.popBackStack() },
                                    onSearchClick = { searchQuery ->
                                        navController.navigate("${PropertyDestination.SEARCH.name}?query=$searchQuery")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun navigateToTab(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

