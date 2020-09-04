package com.utkarsh.fxn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private val RC_SIGN_IN = 111//random


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNav : BottomNavigationView =findViewById(R.id.bottom_nav_bar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.myWatchlistFragment,R.id.searchFragment,R.id.exploreFragment,R.id.adsFragment,R.id.profileFragment))

        bottomNav.setupWithNavController(navController)




        Firebase.database.setPersistenceEnabled(true)

        // Initialize Firebase Auth
        auth = Firebase.auth


        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser

            if (user != null)//signed in
            {
                OnSignedInIntialize(user.displayName)

            } else {

                OnSignOutCleanUp()

                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.tv_icon)
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                            listOf(
                                AuthUI.IdpConfig.GoogleBuilder().build(),
                                AuthUI.IdpConfig.EmailBuilder().build()
                            )
                        )
                        .build(),
                    RC_SIGN_IN
                )
            }


        }


    }

    private fun OnSignOutCleanUp() {


        Toast.makeText(this," Bye ", Toast.LENGTH_SHORT).show()
    }

    private fun OnSignedInIntialize(displayName: String?) {

        Toast.makeText(this,"Welcome $displayName ", Toast.LENGTH_SHORT).show()

    }

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(authStateListener)
    }
}
