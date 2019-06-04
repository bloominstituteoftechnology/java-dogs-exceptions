package com.lambdaschool.dogsinitial.Controller

import com.lambdaschool.dogsinitial.CheckDog
import com.lambdaschool.dogsinitial.Model.Dog
import com.lambdaschool.dogsinitial.DogsinitialApplication
import com.lambdaschool.dogsinitial.Exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping("/dogs")
class DogController {
    // localhost:8080/dogs.html/dogs.html
    val allDogs: ResponseEntity<*>
        @GetMapping(value = ["/dogs"])
        get() = ResponseEntity(DogsinitialApplication.getOurDogList().dogList, HttpStatus.OK)


    // localhost:8080/dogs.html/{id}
    @GetMapping(value = ["/{id}"])
    fun getDogDetail(@PathVariable id: Long): ResponseEntity<*> {
        val rtnDog = DogsinitialApplication.getOurDogList().findDog(CheckDog { d -> d.id == id }) ?: throw ResourceNotFoundException(message = "Dog with id $id cannot be found", cause = null)
        return ResponseEntity<Dog>(rtnDog, HttpStatus.OK)
    }

    // localhost:8080/dogs.html/breeds/{breed}
    @GetMapping(value = ["/breeds/{breed}"])
    fun getDogBreeds(@PathVariable breed: String): ResponseEntity<*> {
//        val rtnDogs = DogsinitialApplication.getOurDogList().findDogs({ d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()) })
        val rtnDogs = DogsinitialApplication.getOurDogList().findDogs(CheckDog { d -> d.breed.toUpperCase() == breed.toUpperCase() })
        if(rtnDogs.isEmpty()){
            throw ResourceNotFoundException(message = "Could not find $breed", cause = null)
        }
        return ResponseEntity(rtnDogs, HttpStatus.OK)
    }

    @GetMapping(value = ["/breedtable"])
    fun getDogBreedTable():ModelAndView{
        val mav: ModelAndView = ModelAndView("dogs")
        mav.addObject("dogList", DogsinitialApplication.getOurDogList().dogList)

        return mav
    }

    @GetMapping(value = ["/breedtable/apartments"])
    fun getDogsForApartments():ModelAndView{
        val mav: ModelAndView = ModelAndView("dogsForApartments")
        mav.addObject("dogList", DogsinitialApplication.getOurDogList().dogList.filter { dog -> dog.isApartmentSuitable })

        return mav
    }
}