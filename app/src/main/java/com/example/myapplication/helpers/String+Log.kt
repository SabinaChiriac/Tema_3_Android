package com.example.myapplication.helpers

import android.util.Log

fun String.errorLog(tag: String = "tag"){
    Log.e(tag, this);
}