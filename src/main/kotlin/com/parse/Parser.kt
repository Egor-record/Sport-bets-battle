package com.parse

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.*
import kotlin.collections.ArrayList


class Parser {

    private val pathToChrome = "/Library/Chrome/chromedriver"

    fun parse() {

        System.setProperty("webdriver.chrome.driver", pathToChrome)

        val chromeOptions = ChromeOptions()

        chromeOptions.setHeadless(true)

        val browser: WebDriver = ChromeDriver(chromeOptions)

        browser.get("https://1xstavka.ru/live/Football/")

        Timer().scheduleAtFixedRate(object : TimerTask() {
                override fun run() {

                    val games: ArrayList<MutableMap<Any, Any>> = ArrayList()

                    try {

                        val elements = browser.findElements(
                            By.className("c-events-scoreboard")
                        )

                        for (element in elements) {

                            val game : MutableMap<Any, Any> = mutableMapOf()

                            val teamDivs = element.findElements(
                                By.className("c-events__team")
                            )

                            val scoreDivs = element.findElements(
                                By.className("c-events-scoreboard__cell--all")
                            )

                            for (i in teamDivs.indices) {
                                game[teamDivs[i].text] = scoreDivs[i].text
                            }



                            games.add(game)

                        }



                    } finally {
                        println("can't find!")
                    }


                    DataSender().sendDataToApi(games)

             }
         }, 0, 10000)

    }
}


