package com.github.chinloyal.pusher_client.pusher.listeners

import android.os.Handler
import android.os.Looper
import com.github.chinloyal.pusher_client.core.utils.Constants
import com.github.chinloyal.pusher_client.pusher.PusherService
import com.google.gson.JsonObject
import com.pusher.client.channel.PresenceChannelEventListener
import com.pusher.client.channel.PusherEvent
import com.pusher.client.channel.User
import org.json.JSONObject
import java.lang.Exception

class FlutterPresenceChannelEventListener: FlutterBaseChannelEventListener(), PresenceChannelEventListener {
    companion object {
        val instance = FlutterPresenceChannelEventListener()
    }
    private val eventStreamJson = JSONObject();

    override fun onUsersInformationReceived(channelName: String, users: MutableSet<User>) {
        var jsonObject =  JsonObject()
        jsonObject.addProperty( "event",Constants.SUBSCRIPTION_SUCCEEDED.value)
        jsonObject.addProperty("channel",channelName)
        jsonObject.addProperty("user_id", "")
        jsonObject.addProperty("data",users.toString())

        this.onEvent(PusherEvent(jsonObject))
//        this.onEvent(PusherEvent(mapOf(
//                "event" to Constants.SUBSCRIPTION_SUCCEEDED.value,
//                "channel" to channelName,
//                "user_id" to null,
//                "data" to users.toString()
//        )))
    }

    override fun userUnsubscribed(channelName: String, user: User) {
        var jsonObject =  JsonObject()
        jsonObject.addProperty( "event",Constants.MEMBER_REMOVED.value)
        jsonObject.addProperty("channel",channelName)
        jsonObject.addProperty("user_id", user.id)
        jsonObject.addProperty("data","")

        this.onEvent(PusherEvent(jsonObject))
//        this.onEvent(PusherEvent(mapOf(
//                "event" to Constants.MEMBER_REMOVED.value,
//                "channel" to channelName,
//                "user_id" to user.id,
//                "data" to null
//        )))
    }

    override fun userSubscribed(channelName: String, user: User) {
        var jsonObject =  JsonObject()
        jsonObject.addProperty( "event",Constants.MEMBER_ADDED.value)
        jsonObject.addProperty("channel",channelName)
        jsonObject.addProperty("user_id", user.id)
        jsonObject.addProperty("data","")

        this.onEvent(PusherEvent(jsonObject))
//        this.onEvent(PusherEvent(mapOf(
//                "event" to Constants.MEMBER_ADDED.value,
//                "channel" to channelName,
//                "user_id" to user.id,
//                "data" to null
//        )))
    }

    override fun onAuthenticationFailure(message: String, e: Exception) {
        PusherService.errorLog(message)
        if(PusherService.enableLogging) e.printStackTrace()
    }

    override fun onEvent(event: PusherEvent) {
//        Handler(Looper.getMainLooper()).post {
//            try {
//                val eventJson = JSONObject(mapOf(
//                    "channelName" to event!!.channelName,
//                    "eventName" to event.eventName,
//                    "userId" to event.userId,
//                    "data" to event.data
//                ))
//
//                eventStreamJson.put("pusherEvent", eventJson)
//
//                PusherService.eventSink?.success(eventStreamJson.toString())
//                PusherService.debugLog(
//                    """
//                |[ON_EVENT] Channel: ${event.channelName}, EventName: ${event.eventName},
//                |Data: ${event.data}, User Id: ${event.userId}
//                """.trimMargin()
//                )
//            } catch (e: Exception) {
//                PusherService.eventSink?.error("ON_EVENT_ERROR", e.message, e)
//            }
//
//        }
    }

    override fun onSubscriptionSucceeded(channelName: String) {
        PusherService.debugLog("[PRESENCE] Subscribed: $channelName")

        var jsonObject =  JsonObject()
        jsonObject.addProperty( "event",Constants.SUBSCRIPTION_SUCCEEDED.value)
        jsonObject.addProperty("channel",channelName)
        jsonObject.addProperty("user_id", "")
        jsonObject.addProperty("data","")

        this.onEvent(PusherEvent(jsonObject))

//        this.onEvent(PusherEvent(mapOf(
//                "event" to Constants.SUBSCRIPTION_SUCCEEDED.value,
//                "channel" to channelName,
//                "user_id" to null,
//                "data" to null
//        )))
    }
}