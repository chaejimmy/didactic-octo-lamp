# PaceDream Android Integration Guide

This guide provides step-by-step instructions for integrating the enhanced design system and components into your existing PaceDream Android app.

## 🚀 Quick Start

### 1. Update Your Dashboard Screen

Replace your existing `DashboardScreen` with the enhanced version:

```kotlin
// In your DashboardNavigation.kt or wherever you use DashboardScreen
composable(DashboardDestination.HOME.name) {
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val homeScreenRoomsState = homeScreenViewModel.homeScreenRoomsState.collectAsStateWithLifecycle()
    val homeScreenRentedGearsState = homeScreenViewModel.homeScreenRentedGearsState.collectAsStateWithLifecycle()
    
    // Use the enhanced version
    EnhancedDashboardScreenWrapper(
        Modifier,
        homeScreenRoomsState.value,
        homeScreenRentedGearsState.value,
        homeScreenViewModel::onEvent,
    )
}
```

### 2. Test the Enhanced Components

The enhanced components are now available and ready to use. You can test them by:

1. **Running the app** - The enhanced dashboard should now show with the new design system
2. **Comparing with iOS** - The visual design should now match your iOS app more closely
3. **Testing interactions** - All buttons, search, and navigation should work as expected

## 🎨 Design System Usage

### Using Colors

```kotlin
import com.pacedream.common.composables.theme.*

// Use brand colors
val primaryColor = PaceDreamColors.Primary
val secondaryColor = PaceDreamColors.Secondary
val textColor = PaceDreamColors.TextPrimary

// Use in Material 3 components
Card(
    colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card)
) {
    // Your content
}
```

### Using Typography

```kotlin
import com.pacedream.common.composables.theme.*

Text(
    text = "Welcome to PaceDream",
    style = PaceDreamTypography.Title1,
    color = PaceDreamColors.TextPrimary
)
```

### Using Spacing

```kotlin
import com.pacedream.common.composables.theme.*

Column(
    modifier = Modifier.padding(PaceDreamSpacing.LG)
) {
    // Your content with consistent spacing
}
```

## 🧩 Component Usage

### Basic Components

```kotlin
import com.pacedream.common.composables.components.*

// Hero Header
PaceDreamHeroHeader(
    title = "Welcome to PaceDream",
    subtitle = "Find your perfect stay",
    onNotificationClick = { /* Handle notification */ }
)

// Search Bar
PaceDreamSearchBar(
    query = searchQuery,
    onQueryChange = { newQuery -> /* Update query */ },
    onSearchClick = { /* Handle search */ },
    onFilterClick = { /* Handle filter */ }
)

// Metric Card
PaceDreamMetricCard(
    title = "Available Rooms",
    value = "24",
    icon = Icons.Default.Home,
    color = PaceDreamColors.Primary
)

// Category Pill
PaceDreamCategoryPill(
    title = "Rest Room",
    icon = Icons.Default.Bed,
    isSelected = true,
    onClick = { /* Handle selection */ }
)
```

### Property Components

```kotlin
// Property Card
PaceDreamPropertyCard(
    title = "Modern Apartment",
    location = "New York, NY",
    price = "$120/night",
    rating = 4.8,
    reviewCount = 124,
    onClick = { /* Navigate to property detail */ }
)

// Destination Card
PaceDreamDestinationCard(
    name = "London",
    imageUrl = "https://example.com/london.jpg",
    onClick = { /* Navigate to destination */ }
)
```

### Enhanced Components

```kotlin
// Enhanced Property Card
EnhancedPropertyCard(
    propertyName = "Luxury Villa",
    location = "Miami, FL",
    price = "$250/night",
    rating = 4.9f,
    amenities = listOf("WiFi", "Pool", "Gym"),
    isFavorite = true,
    onFavoriteClick = { /* Handle favorite */ },
    onClick = { /* Navigate to detail */ }
)

// Host Card
HostCard(
    hostName = "Sarah Johnson",
    hostTitle = "Superhost",
    rating = 4.9f,
    responseTime = "Within an hour",
    isSuperhost = true,
    onContactClick = { /* Handle contact */ },
    onProfileClick = { /* Handle profile */ }
)

// Amenity Components
AmenityChip(
    amenity = "WiFi",
    icon = Icons.Default.Wifi,
    isSelected = true,
    onClick = { /* Handle selection */ }
)
```

## 📱 Screen Implementations

### Enhanced Dashboard Screen

```kotlin
@Composable
fun MyDashboardScreen() {
    EnhancedDashboardScreen(
        roomsState = roomsState,
        gearsState = gearsState,
        onTimeBasedRoomsChanged = { type -> /* Handle change */ },
        onRentedGearsChanged = { type -> /* Handle change */ },
        onPropertyClick = { id -> /* Navigate to property */ },
        onCategoryClick = { category -> /* Filter by category */ },
        onViewAllClick = { section -> /* Navigate to section */ }
    )
}
```

### Property Detail Screen

```kotlin
@Composable
fun MyPropertyDetailScreen(propertyId: String) {
    PropertyDetailScreen(
        propertyId = propertyId,
        onBackClick = { /* Navigate back */ },
        onBookClick = { /* Handle booking */ },
        onShareClick = { /* Handle share */ },
        onFavoriteClick = { /* Handle favorite */ }
    )
}
```

## 🔧 Customization

### Creating Custom Components

When creating new components, follow the design system patterns:

```kotlin
@Composable
fun MyCustomComponent(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaceDreamSpacing.MD),
        colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamRadius.MD)
    ) {
        Text(
            text = title,
            style = PaceDreamTypography.Title3,
            color = PaceDreamColors.TextPrimary,
            modifier = Modifier.padding(PaceDreamSpacing.MD)
        )
    }
}
```

### Extending the Design System

To add new colors or typography:

1. **Add to PaceDreamDesignSystem.kt**:
```kotlin
object PaceDreamColors {
    // ... existing colors
    val CustomColor = Color(0xFF123456)
}
```

2. **Use in components**:
```kotlin
val customColor = PaceDreamColors.CustomColor
```

## 🎯 Best Practices

### 1. Consistency
- Always use design system components instead of custom implementations
- Follow the established spacing and typography patterns
- Use the provided color palette consistently

### 2. Performance
- Components are optimized for Compose performance
- Use `remember` for expensive calculations
- Avoid unnecessary recompositions

### 3. Accessibility
- All components include proper accessibility labels
- Use semantic colors for better contrast
- Test with screen readers

### 4. Testing
- Components include test tags for UI testing
- Use the provided preview functions for development
- Test on different screen sizes

## 🐛 Troubleshooting

### Common Issues

1. **Import Errors**
   ```kotlin
   // Make sure to import the design system
   import com.pacedream.common.composables.theme.*
   import com.pacedream.common.composables.components.*
   ```

2. **Theme Not Applied**
   ```kotlin
   // Ensure your app is wrapped with PaceDreamTheme
   PaceDreamTheme {
       // Your app content
   }
   ```

3. **Component Not Found**
   - Check that the component is properly exported from the common module
   - Verify the import path is correct

### Debug Mode

Enable debug mode to see design system boundaries:

```kotlin
// Add this to your theme for debugging
@Composable
fun DebugPaceDreamTheme(content: @Composable () -> Unit) {
    PaceDreamTheme {
        Box(modifier = Modifier.background(Color.Red.copy(alpha = 0.1f))) {
            content()
        }
    }
}
```

## 📚 Additional Resources

- [Design System README](DESIGN_SYSTEM_README.md) - Complete design system documentation
- [Material Design 3](https://m3.material.io/) - Material Design guidelines
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Compose documentation

## 🚀 Next Steps

1. **Replace existing components** with enhanced versions
2. **Add navigation** to property detail screens
3. **Implement search functionality** using the enhanced search components
4. **Add animations** for smooth transitions
5. **Test on different devices** to ensure responsiveness

## 📞 Support

If you encounter any issues or need help with integration:

1. Check the troubleshooting section above
2. Review the component documentation
3. Test with the provided examples
4. Create an issue with specific details

---

The enhanced design system is now ready to use! Start by replacing your existing dashboard screen and gradually migrate other screens to use the new components.
