package com.vmo.mobileminiproject.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

object Navigator {
    fun addFragment(context: AppCompatActivity, frameContainId: Int, fragment: Fragment) {
        context.supportFragmentManager.commit {
            add(frameContainId, fragment)
            addToBackStack(fragment.javaClass.simpleName)
        }
    }

    fun addFragment(context: Fragment, frameContainId: Int, fragment: Fragment) {
        context.parentFragmentManager.commit {
            add(frameContainId, fragment)
            addToBackStack(fragment.javaClass.simpleName)
        }
    }

    fun replaceFragment(context: AppCompatActivity, frameContainId: Int, fragment: Fragment) {
        context.supportFragmentManager.commit {
            replace(frameContainId, fragment)
            addToBackStack(fragment.javaClass.simpleName)
        }
    }

    fun backFragment(context: AppCompatActivity) {
        val fragmentManager: FragmentManager = context.supportFragmentManager
        fragmentManager.popBackStack()
    }
}