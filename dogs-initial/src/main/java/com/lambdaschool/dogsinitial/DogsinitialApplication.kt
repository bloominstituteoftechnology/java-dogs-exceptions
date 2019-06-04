package com.lambdaschool.dogsinitial


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
open class DogsinitialApplication {


    companion object {
        private lateinit var ourDogList: DogList

        @JvmStatic
        fun main(args: Array<String>) {
            ourDogList = DogList()
            SpringApplication.run(DogsinitialApplication::class.java, *args)
            val ctx = SpringApplication.run(DogsinitialApplication::class.java, *args)

            val dispatcherServlet = ctx.getBean("dispatcherServlet") as DispatcherServlet
            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)
        }

        fun getOurDogList() : DogList = ourDogList


    }
}