package com.magistor8.translator.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magistor8.translator.R
import com.magistor8.translator.view.fragment_details.DetailsFragment
import com.magistor8.translator.view.fragment_history.HistoryFragment
import com.magistor8.translator.view.fragment_main.MainFragment

class Navigation(
    private val context: FragmentActivity,
) {

    enum class Action {
        ADD, REPLACE
    }

    fun <T : Fragment> navigate(
        fragmentClass: Class<T>,
        action: Action,
        bundle: Bundle? = null,
        addToBS: Boolean = false
    ) {

        val fragment = fragmentClass.newInstance()
        var forcedCreation = false
        bundle?.let {
            fragment.arguments = bundle
            forcedCreation = true
        }
        transaction(fragment, action, R.id.container, addToBS, fragmentClass.name, forcedCreation)
    }

    private fun transaction(
        fragment: Fragment,
        action: Action,
        container: Int,
        addToBS: Boolean,
        tag: String,
        forcedCreation: Boolean
    ) {
        with(context.supportFragmentManager) {
            val tr = beginTransaction()
            if (findFragmentByTag(tag) != null && !forcedCreation) {
                popBackStack()
            } else {
                when(action) {
                    Action.ADD -> {
                        tr.add(
                            container,
                            fragment,
                            tag
                        )
                    }
                    Action.REPLACE -> {
                        tr.replace(
                            container,
                            fragment,
                            tag
                        )
                    }
                }
            }
            if (addToBS) {
                tr.addToBackStack(tag)
            }
            tr.commit()
        }
    }

}
