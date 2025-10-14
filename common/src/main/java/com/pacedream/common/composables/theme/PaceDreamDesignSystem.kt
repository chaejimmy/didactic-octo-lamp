package com.pacedream.common.composables.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * PaceDream Design System
 * Comprehensive design tokens matching iOS app design
 */

// Spacing System
object PaceDreamSpacing {
    val XS = 4.dp
    val SM = 8.dp
    val MD = 16.dp
    val LG = 24.dp
    val XL = 32.dp
    val XXL = 48.dp
    val XXXL = 64.dp
}

// Corner Radius System
object PaceDreamRadius {
    val XS = 4.dp
    val SM = 8.dp
    val MD = 12.dp
    val LG = 16.dp
    val XL = 20.dp
    val XXL = 24.dp
    val Round = 50.dp
}

// Icon Size System
object PaceDreamIconSize {
    val XS = 12.dp
    val SM = 16.dp
    val MD = 20.dp
    val LG = 24.dp
    val XL = 32.dp
    val XXL = 48.dp
    val XXXL = 64.dp
}

// Component Dimensions
object PaceDreamSearchBar {
    val Height = 56.dp
    val CornerRadius = PaceDreamRadius.MD
    val Padding = PaceDreamSpacing.SM
    val IconSize = PaceDreamIconSize.MD
}

object PaceDreamMetricCard {
    val MinHeight = 120.dp
    val Elevation = 4.dp
    val CornerRadius = PaceDreamRadius.MD
    val Padding = PaceDreamSpacing.MD
    val IconSize = PaceDreamIconSize.LG
}

object PaceDreamCategoryPill {
    val Height = 40.dp
    val CornerRadius = PaceDreamRadius.Round
    val Padding = PaddingValues(horizontal = PaceDreamSpacing.MD, vertical = PaceDreamSpacing.SM)
    val IconSize = PaceDreamIconSize.SM
}

object PaceDreamPropertyCard {
    val Elevation = 6.dp
    val CornerRadius = PaceDreamRadius.LG
    val ImageHeight = 180.dp
    val ContentPadding = PaddingValues(PaceDreamSpacing.MD)
}

object PaceDreamDestinationCard {
    val Width = 200.dp
    val Height = 120.dp
    val ImageHeight = 80.dp
    val CornerRadius = PaceDreamRadius.MD
    val Padding = PaddingValues(PaceDreamSpacing.MD)
}

object PaceDreamRecentSearchItem {
    val Height = 48.dp
    val CornerRadius = PaceDreamRadius.MD
    val Padding = PaddingValues(PaceDreamSpacing.MD)
    val IconSize = PaceDreamIconSize.SM
}

object PaceDreamEmptyState {
    val Padding = PaddingValues(PaceDreamSpacing.XXXL)
    val IconSize = PaceDreamIconSize.XXXL
}

object PaceDreamErrorState {
    val Padding = PaddingValues(PaceDreamSpacing.XXXL)
    val IconSize = PaceDreamIconSize.XXXL
}

object PaceDreamLoadingState {
    val Padding = PaddingValues(PaceDreamSpacing.XXXL)
    val IconSize = PaceDreamIconSize.XXL
}

// Typography System
object PaceDreamTypography {
    val LargeTitle = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp
    )
    
    val Title1 = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 34.sp
    )
    
    val Title2 = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp
    )
    
    val Title3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    )
    
    val Headline = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp
    )
    
    val Body = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp
    )
    
    val Callout = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )
    
    val Subheadline = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )
    
    val Caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    )
}

// Elevation System
object PaceDreamElevation {
    val None = 0.dp
    val XS = 1.dp
    val SM = 2.dp
    val MD = 4.dp
    val LG = 8.dp
    val XL = 12.dp
    val XXL = 16.dp
}


// Button Heights
object PaceDreamButtonHeight {
    val SM = 32.dp
    val MD = 40.dp
    val LG = 48.dp
    val XL = 56.dp
}

// Input Field Heights
object PaceDreamInputHeight {
    val SM = 40.dp
    val MD = 48.dp
    val LG = 56.dp
}

// Card Dimensions
object PaceDreamCard {
    val MinHeight = 120.dp
    val MaxHeight = 300.dp
    val DefaultPadding = PaceDreamSpacing.MD
    val DefaultElevation = PaceDreamElevation.MD
}

// Bottom Navigation
object PaceDreamBottomNav {
    val Height = 80.dp
    val IconSize = PaceDreamIconSize.MD
    val LabelSize = 12.sp
    val Padding = PaceDreamSpacing.SM
}

// Top App Bar
object PaceDreamTopAppBar {
    val Height = 64.dp
    val IconSize = PaceDreamIconSize.LG
    val TitleSize = PaceDreamTypography.Title3.fontSize
    val Padding = PaceDreamSpacing.MD
}

// Search Bar
object PaceDreamSearchBar {
    val Height = 48.dp
    val IconSize = PaceDreamIconSize.MD
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
}

// Property Card
object PaceDreamPropertyCard {
    val ImageHeight = 200.dp
    val ContentPadding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Category Pill
object PaceDreamCategoryPill {
    val Height = 40.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.Round
    val IconSize = PaceDreamIconSize.SM
}

// Metric Card
object PaceDreamMetricCard {
    val MinHeight = 100.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.MD
    val IconSize = PaceDreamIconSize.LG
}

// Destination Card
object PaceDreamDestinationCard {
    val Width = 120.dp
    val Height = 160.dp
    val ImageHeight = 100.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.MD
}

// Recent Search Item
object PaceDreamRecentSearchItem {
    val Height = 40.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.SM
    val IconSize = PaceDreamIconSize.SM
}

// Hero Header
object PaceDreamHeroHeader {
    val MinHeight = 200.dp
    val Padding = PaceDreamSpacing.LG
    val TitleSize = PaceDreamTypography.Title1.fontSize
    val SubtitleSize = PaceDreamTypography.Body.fontSize
}

// Section Header
object PaceDreamSectionHeader {
    val Padding = PaceDreamSpacing.MD
    val TitleSize = PaceDreamTypography.Title3.fontSize
    val ViewAllSize = PaceDreamTypography.Callout.fontSize
}

// Loading States
object PaceDreamLoading {
    val ShimmerHeight = 120.dp
    val ShimmerCornerRadius = PaceDreamRadius.MD
    val ShimmerAnimationDuration = 1000
}

// Animation Durations
object PaceDreamAnimation {
    val Fast = 150
    val Normal = 300
    val Slow = 500
    val VerySlow = 1000
}

// Breakpoints for responsive design
object PaceDreamBreakpoints {
    val Mobile = 600.dp
    val Tablet = 840.dp
    val Desktop = 1200.dp
}

// Grid System
object PaceDreamGrid {
    val Columns = 12
    val Gutter = PaceDreamSpacing.MD
    val Margin = PaceDreamSpacing.LG
}

// Z-Index System
object PaceDreamZIndex {
    val Background = 0
    val Content = 1
    val Overlay = 10
    val Modal = 100
    val Toast = 1000
}

// Shadow System
object PaceDreamShadow {
    val None = 0.dp
    val XS = 1.dp
    val SM = 2.dp
    val MD = 4.dp
    val LG = 8.dp
    val XL = 12.dp
    val XXL = 16.dp
}

// Border System
object PaceDreamBorder {
    val None = 0.dp
    val XS = 0.5.dp
    val SM = 1.dp
    val MD = 2.dp
    val LG = 4.dp
}

// Opacity System
object PaceDreamOpacity {
    val Disabled = 0.38f
    val Medium = 0.6f
    val High = 0.87f
    val Full = 1.0f
}

// Content Width Constraints
object PaceDreamContentWidth {
    val Mobile = 600.dp
    val Tablet = 840.dp
    val Desktop = 1200.dp
    val MaxWidth = 1440.dp
}

// List Item Heights
object PaceDreamListItem {
    val SM = 40.dp
    val MD = 56.dp
    val LG = 72.dp
    val XL = 88.dp
}

// Dialog Dimensions
object PaceDreamDialog {
    val MinWidth = 280.dp
    val MaxWidth = 560.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.XL
}

// Snackbar
object PaceDreamSnackbar {
    val Height = 48.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.SM
    val Elevation = PaceDreamElevation.LG
}

// FAB (Floating Action Button)
object PaceDreamFAB {
    val Size = 56.dp
    val IconSize = PaceDreamIconSize.LG
    val CornerRadius = PaceDreamRadius.Round
    val Elevation = PaceDreamElevation.LG
}

// Chip Dimensions
object PaceDreamChip {
    val Height = 32.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.Round
    val IconSize = PaceDreamIconSize.SM
}

// Badge Dimensions
object PaceDreamBadge {
    val Size = 20.dp
    val TextSize = 10.sp
    val CornerRadius = PaceDreamRadius.Round
    val Padding = PaceDreamSpacing.XS
}

// Avatar Dimensions
object PaceDreamAvatar {
    val XS = 24.dp
    val SM = 32.dp
    val MD = 40.dp
    val LG = 48.dp
    val XL = 64.dp
    val XXL = 80.dp
    val XXXL = 120.dp
}

// Progress Indicator
object PaceDreamProgress {
    val StrokeWidth = 4.dp
    val Size = 40.dp
    val LargeSize = 60.dp
}

// Divider
object PaceDreamDivider {
    val Thickness = 1.dp
    val Padding = PaceDreamSpacing.MD
}

// Tab Dimensions
object PaceDreamTab {
    val Height = 48.dp
    val Padding = PaceDreamSpacing.MD
    val IndicatorHeight = 2.dp
    val CornerRadius = PaceDreamRadius.SM
}

// Tooltip
object PaceDreamTooltip {
    val MaxWidth = 200.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.SM
    val ArrowSize = 8.dp
}

// Menu
object PaceDreamMenu {
    val MinWidth = 160.dp
    val MaxWidth = 320.dp
    val Padding = PaceDreamSpacing.XS
    val CornerRadius = PaceDreamRadius.SM
    val Elevation = PaceDreamElevation.MD
}

// Navigation Drawer
object PaceDreamDrawer {
    val Width = 280.dp
    val HeaderHeight = 160.dp
    val ItemHeight = PaceDreamListItem.MD
    val Padding = PaceDreamSpacing.MD
}

// Bottom Sheet
object PaceDreamBottomSheet {
    val HandleHeight = 4.dp
    val HandleWidth = 40.dp
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.XL
}

// Stepper
object PaceDreamStepper {
    val Height = 40.dp
    val Width = 40.dp
    val CornerRadius = PaceDreamRadius.Round
    val BorderWidth = PaceDreamBorder.SM
}

// Rating
object PaceDreamRating {
    val StarSize = 20.dp
    val Spacing = PaceDreamSpacing.XS
}

// Price Display
object PaceDreamPrice {
    val LargeSize = 24.sp
    val MediumSize = 18.sp
    val SmallSize = 14.sp
    val CurrencySize = 12.sp
}

// Status Indicators
object PaceDreamStatus {
    val DotSize = 8.dp
    val BadgeSize = 16.dp
    val CornerRadius = PaceDreamRadius.Round
}

// Image Aspect Ratios
object PaceDreamAspectRatio {
    val Square = 1f
    val Landscape = 16f / 9f
    val Portrait = 3f / 4f
    val Wide = 21f / 9f
    val Golden = 1.618f
}

// Map Dimensions
object PaceDreamMap {
    val Height = 200.dp
    val CornerRadius = PaceDreamRadius.MD
    val MarkerSize = 32.dp
    val ClusterSize = 40.dp
}

// Calendar
object PaceDreamCalendar {
    val DaySize = 40.dp
    val HeaderHeight = 48.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.SM
}

// Time Picker
object PaceDreamTimePicker {
    val Height = 200.dp
    val ItemHeight = 48.dp
    val Padding = PaceDreamSpacing.MD
}

// Date Picker
object PaceDreamDatePicker {
    val Height = 300.dp
    val ItemHeight = 48.dp
    val Padding = PaceDreamSpacing.MD
}

// Color Picker
object PaceDreamColorPicker {
    val SwatchSize = 40.dp
    val Spacing = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.SM
}

// Slider
object PaceDreamSlider {
    val Height = 40.dp
    val ThumbSize = 20.dp
    val TrackHeight = 4.dp
}

// Switch
object PaceDreamSwitch {
    val Width = 48.dp
    val Height = 28.dp
    val ThumbSize = 20.dp
    val CornerRadius = PaceDreamRadius.Round
}

// Checkbox
object PaceDreamCheckbox {
    val Size = 20.dp
    val CornerRadius = PaceDreamRadius.XS
    val BorderWidth = PaceDreamBorder.SM
}

// Radio Button
object PaceDreamRadioButton {
    val Size = 20.dp
    val DotSize = 8.dp
}

// Toggle Button
object PaceDreamToggleButton {
    val Height = 32.dp
    val Padding = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.SM
}

// Segmented Control
object PaceDreamSegmentedControl {
    val Height = 32.dp
    val Padding = PaceDreamSpacing.XS
    val CornerRadius = PaceDreamRadius.SM
    val IndicatorCornerRadius = PaceDreamRadius.SM
}

// Picker
object PaceDreamPicker {
    val Height = 200.dp
    val ItemHeight = 40.dp
    val Padding = PaceDreamSpacing.MD
}

// Action Sheet
object PaceDreamActionSheet {
    val ItemHeight = 56.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.XL
}

// Alert Dialog
object PaceDreamAlertDialog {
    val MinWidth = 280.dp
    val MaxWidth = 400.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.XL
}

// Confirmation Dialog
object PaceDreamConfirmationDialog {
    val MinWidth = 300.dp
    val MaxWidth = 500.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.XL
}

// Loading Dialog
object PaceDreamLoadingDialog {
    val Size = 80.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.LG
}

// Toast
object PaceDreamToast {
    val MinHeight = 48.dp
    val MaxWidth = 400.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.SM
    val Elevation = PaceDreamElevation.MD
}

// Banner
object PaceDreamBanner {
    val MinHeight = 56.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.SM
    val Elevation = PaceDreamElevation.SM
}

// Card Grid
object PaceDreamCardGrid {
    val Columns = 2
    val Spacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
}

// List Grid
object PaceDreamListGrid {
    val Columns = 1
    val Spacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
}

// Masonry Grid
object PaceDreamMasonryGrid {
    val Columns = 2
    val Spacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
    val ItemSpacing = PaceDreamSpacing.XS
}

// Waterfall Grid
object PaceDreamWaterfallGrid {
    val Columns = 2
    val Spacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
    val ItemSpacing = PaceDreamSpacing.XS
}

// Staggered Grid
object PaceDreamStaggeredGrid {
    val Columns = 2
    val Spacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
    val ItemSpacing = PaceDreamSpacing.XS
}

// Carousel
object PaceDreamCarousel {
    val ItemSpacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
    val IndicatorSize = 8.dp
    val IndicatorSpacing = PaceDreamSpacing.XS
}

// Pagination
object PaceDreamPagination {
    val ItemSize = 40.dp
    val Spacing = PaceDreamSpacing.XS
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.SM
}

// Search Results
object PaceDreamSearchResults {
    val ItemHeight = 72.dp
    val Padding = PaceDreamSpacing.MD
    val Spacing = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.MD
}

// Filter Chips
object PaceDreamFilterChips {
    val Height = 32.dp
    val Padding = PaceDreamSpacing.SM
    val Spacing = PaceDreamSpacing.XS
    val CornerRadius = PaceDreamRadius.Round
}

// Sort Options
object PaceDreamSortOptions {
    val ItemHeight = 48.dp
    val Padding = PaceDreamSpacing.MD
    val Spacing = PaceDreamSpacing.SM
    val CornerRadius = PaceDreamRadius.SM
}

// Category Grid
object PaceDreamCategoryGrid {
    val Columns = 4
    val Spacing = PaceDreamSpacing.SM
    val Padding = PaceDreamSpacing.MD
    val ItemHeight = 80.dp
}

// Featured Section
object PaceDreamFeaturedSection {
    val Height = 200.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.SM
}

// Hero Section
object PaceDreamHeroSection {
    val Height = 300.dp
    val Padding = PaceDreamSpacing.XL
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.None
}

// Testimonial Card
object PaceDreamTestimonialCard {
    val MinHeight = 120.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.SM
}

// Stats Card
object PaceDreamStatsCard {
    val Height = 100.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Profile Card
object PaceDreamProfileCard {
    val Height = 120.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
    val AvatarSize = PaceDreamAvatar.LG
}

// Notification Card
object PaceDreamNotificationCard {
    val MinHeight = 80.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Message Card
object PaceDreamMessageCard {
    val MinHeight = 60.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Booking Card
object PaceDreamBookingCard {
    val MinHeight = 100.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Payment Card
object PaceDreamPaymentCard {
    val Height = 200.dp
    val Padding = PaceDreamSpacing.LG
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.MD
}

// Settings Item
object PaceDreamSettingsItem {
    val Height = 56.dp
    val Padding = PaceDreamSpacing.MD
    val IconSize = PaceDreamIconSize.MD
    val CornerRadius = PaceDreamRadius.SM
}

// Info Card
object PaceDreamInfoCard {
    val MinHeight = 80.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Warning Card
object PaceDreamWarningCard {
    val MinHeight = 80.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Error Card
object PaceDreamErrorCard {
    val MinHeight = 80.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Success Card
object PaceDreamSuccessCard {
    val MinHeight = 80.dp
    val Padding = PaceDreamSpacing.MD
    val CornerRadius = PaceDreamRadius.MD
    val Elevation = PaceDreamElevation.SM
}

// Empty State
object PaceDreamEmptyState {
    val IconSize = PaceDreamIconSize.XXXL
    val Padding = PaceDreamSpacing.XL
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.None
}

// Loading State
object PaceDreamLoadingState {
    val IconSize = PaceDreamIconSize.XXL
    val Padding = PaceDreamSpacing.XL
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.None
}

// Error State
object PaceDreamErrorState {
    val IconSize = PaceDreamIconSize.XXXL
    val Padding = PaceDreamSpacing.XL
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.None
}

// Success State
object PaceDreamSuccessState {
    val IconSize = PaceDreamIconSize.XXXL
    val Padding = PaceDreamSpacing.XL
    val CornerRadius = PaceDreamRadius.LG
    val Elevation = PaceDreamElevation.None
}
