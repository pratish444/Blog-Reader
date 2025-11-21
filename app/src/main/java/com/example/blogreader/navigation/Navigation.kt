package com.example.blogreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.blogreader.presentation.bloglist.blogdetail.BlogDetailScreen
import com.example.blogreader.presentation.bloglist.BlogListScreen

sealed class Screen(val route: String) {
    object BlogList : Screen("blog_list")
    object BlogDetail : Screen("blog_detail/{blogId}") {
        fun createRoute(blogId: Int) = "blog_detail/$blogId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BlogList.route
    ) {
        composable(Screen.BlogList.route) {
            BlogListScreen(
                onBlogClick = { blogId ->
                    navController.navigate(Screen.BlogDetail.createRoute(blogId))
                }
            )
        }

        composable(
            route = Screen.BlogDetail.route,
            arguments = listOf(
                navArgument("blogId") {
                    type = NavType.IntType
                }
            )
        ) {
            BlogDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}