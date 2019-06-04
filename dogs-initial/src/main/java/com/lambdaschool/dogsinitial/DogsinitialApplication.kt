package com.lambdaschool.dogsinitial


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class DogsinitialApplication {


    companion object {
        private lateinit var ourDogList: DogList

        @JvmStatic
        fun main(args: Array<String>) {
            ourDogList = DogList()
            SpringApplication.run(DogsinitialApplication::class.java, *args)
        }

        fun getOurDogList() : DogList = ourDogList


    }
}