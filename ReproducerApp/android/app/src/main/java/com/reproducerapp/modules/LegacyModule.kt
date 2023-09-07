package com.reproducerapp.modules

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.NativeViewHierarchyManager
import com.facebook.react.uimanager.UIBlock
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.modules.core.DeviceEventManagerModule

class LegacyModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "LegacyModule"

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

    @ReactMethod
    fun hello(someArg: String, callback: Callback) {
        Log.d("LegacyModule", "Legacy module called with someArg: $someArg")
        callback.invoke(null, "success")

        val uiManager = getReactApplicationContext()!!.getNativeModule(UIManagerModule::class.java)
        uiManager!!.addUIBlock(
            object : UIBlock {
                override fun execute(nativeViewHierarchyManager: NativeViewHierarchyManager?) {
                    Log.i("LegacyModule", "addUIBlock() called")
                }
            },
        )
    }

    @ReactMethod
    fun addListener(eventName: String?) {
        // Keep: Required for RN built in Event Emitter Calls.
    }

    @ReactMethod
    fun removeListeners(count: Int?) {
        // Keep: Required for RN built in Event Emitter Calls.
    }
}