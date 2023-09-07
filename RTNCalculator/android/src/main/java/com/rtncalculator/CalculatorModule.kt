package com.rtncalculator

import android.util.Log

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.NativeViewHierarchyManager
import com.facebook.react.uimanager.UIBlock
import com.facebook.react.uimanager.UIManagerModule
import com.rtncalculator.NativeCalculatorSpec


class CalculatorModule(reactContext: ReactApplicationContext) : NativeCalculatorSpec(reactContext) {

  override fun getName() = NAME

  override fun add(a: Double, b: Double, promise: Promise) {
    Log.i("CalculatorModule", "Turbo module called...");

    // promise.resolve(a + b)

    val uiManager = getReactApplicationContext()!!.getNativeModule(UIManagerModule::class.java)
    uiManager!!.addUIBlock(
        object : UIBlock {
            override fun execute(nativeViewHierarchyManager: NativeViewHierarchyManager?) {
                Log.i("CalculatorModule", "addUIBlock() called")
                promise.resolve(a + b)
            }
        },
    )
  }

  companion object {
    const val NAME = "RTNCalculator"
  }
}