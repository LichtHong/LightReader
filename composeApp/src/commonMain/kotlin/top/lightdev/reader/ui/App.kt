package top.lightdev.reader.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import top.lightdev.reader.ui.bookshelf.BookshelfScreen

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController()
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType by remember {
        derivedStateOf {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenRoute by remember {
        derivedStateOf { backStackEntry?.destination?.route ?: Bookshelf.route }
    }
    val screens = remember { listOf(Browse, Bookshelf, Profile) }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            repeat(screens.size) { index ->
                val screenWithIcons = screens[index]
                val selected = currentScreenRoute.startsWith(screenWithIcons.route)
                item(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.navigate(screenWithIcons.route) {
                                popUpTo(Bookshelf.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) screenWithIcons.selectedIcon else screenWithIcons.icon,
                            contentDescription = stringResource(screenWithIcons.title)
                        )
                    },
                    label = { Text(stringResource(screenWithIcons.title)) },
                    alwaysShowLabel = false
                )
            }
        },
        layoutType = customNavSuiteType
    ) {
        NavHost(
            navController = navController,
            startDestination = Bookshelf.route
        ) {
            composable(route = Browse.route) {
                Text(stringResource(Browse.title))
            }
            composable(
                route = Bookshelf.route
            ) {
                BookshelfScreen()
            }
            composable(
                route = "${Bookshelf.route}/{type}"
            ) {
                val type = backStackEntry?.arguments?.getString("type")
                BookshelfScreen(type ?: "reading")
            }
            composable(route = Profile.route) {
                Text(stringResource(Profile.title))
            }
        }
    }
}