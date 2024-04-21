package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.html.*
import io.ktor.http.*
import kotlinx.html.*

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

val botToken = "MTIzMTY5MDQzODM2NTQxNzUyMg.G7Jvth.hfQXBxovP7itKCMZ_hhxTXi_iLkMN5m8WNLm8s"
val botChannelID = "756978240576553003"
val discordBot = JDABuilder.createDefault(botToken).build().awaitReady();
val channel = discordBot.getTextChannelById(botChannelID);


fun Application.configureRouting() {
    routing {
        get("/") {
            var message = call.request.queryParameters["message"]
            var info = ""

            println(message);

            if(!message.equals(""))
            {
                info = "Message - \"$message\" was sent succesfully!"
            }

            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title {
                        +"Ktor discord bot for ebiznes24"
                    }
                }
                body {
                    h1 {
                        +"Type a message below"
                    }

                    form(method = FormMethod.get, action = "/send"){
                        textInput { name = "message" }
                        submitInput { value = "submit" }
                    }

                    p{
                        +"$info"
                    }

                }
            }
        }

        get("/send") {
            var message = call.request.queryParameters["message"]
            channel?.sendMessage("$message")?.queue()
            call.respondRedirect("/?message=$message")
        }

    }
}
