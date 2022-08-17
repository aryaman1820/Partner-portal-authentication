package com.orbitbank.authenticationpartnerportal.controller

import com.orbitbank.authenticationpartnerportal.entity.User
import com.orbitbank.authenticationpartnerportal.model.CustomerDto
import com.orbitbank.authenticationpartnerportal.model.MappingDto
import com.orbitbank.authenticationpartnerportal.model.ProductDto
import com.orbitbank.authenticationpartnerportal.service.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity


@RestController
@RequestMapping("/api")
@CrossOrigin
class UserController(private val userServiceImpl: UserServiceImpl) {



    @PostMapping("/register")
    fun registerNewUser(@RequestBody user: User): ResponseEntity<String> {
        userServiceImpl.registerNewUser(user)
        return ResponseEntity.ok("User Registered successfully")
    }

    @PostMapping("/product/save")
    @PreAuthorize("hasRole('Partner')")
    fun saveProduct(@RequestBody product: ProductDto): ResponseEntity<String> {
        val url = "http://localhost:8086/registerNewProduct"
        val restTemplate = RestTemplate()
        return restTemplate.postForEntity(url, product, String::class.java)
    }

    @PostMapping("/mapping/save")
    @PreAuthorize("hasRole('Partner')")
    fun saveMapping(@RequestBody mappingDto: MappingDto): ResponseEntity<String> {
        val url = "http://localhost:8087/save/mapping"
        val restTemplate = RestTemplate()
        return restTemplate.postForEntity(url, mappingDto, String::class.java)
    }

    @PostMapping("/save/customer")
    @PreAuthorize("hasRole('Admin')")
    fun saveCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<String> {
        val url = "http://localhost:8087/save/customer"
        val restTemplate = RestTemplate()
        return restTemplate.postForEntity(url, customerDto, String::class.java)
    }

    @GetMapping("/product/all")
    @PreAuthorize("hasRole('Admin')")
    fun getAllProducts(): ResponseEntity<List<*>?>? {
        val url = "http://localhost:8086/allProducts"
        //RestTemplate template = new RestTemplate();
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(url, List::class.java)
    }

    @GetMapping("/mapping/all")
    @PreAuthorize("hasRole('Admin')")
    fun getAllmapping(): ResponseEntity<List<*>?>? {
        val url = "http://localhost:8087/allmappings"
        //RestTemplate template = new RestTemplate();
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(url, List::class.java)
    }

    @GetMapping("/all/users")
    fun findAllUsers(): ResponseEntity<*> {
        return ResponseEntity.ok(userServiceImpl.listAll())
    }

    @GetMapping("/findUser/{userName}")
    fun findByUserName(@PathVariable userName: String): ResponseEntity<*> {
        return ResponseEntity.ok(
            userServiceImpl.findByUserName(
                userName
            )
        )
    }

    @GetMapping("/product/{userName}")
    fun productByUserName(@PathVariable userName: String): ResponseEntity<List<*>> {
        val url = "http://localhost:8086/user/products/$userName"
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity<List<*>>(
            url,
            MutableList::class.java
        )
    }

    @GetMapping("/all/customers")
    fun allMappings(): ResponseEntity<List<*>> {
        val url = "http://localhost:8087/allcustomers"
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity<List<*>>(
            url,
            MutableList::class.java
        )
    }

    @GetMapping("/customer/{customerName}")
    fun getCustomerByCustomerName(@PathVariable customerName: String): ResponseEntity<CustomerDto> {
        val url = "http://localhost:8087/customer/$customerName"
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(url, CustomerDto::class.java)
    }

    @GetMapping("/productUrl/{productName}")
    fun productByUrl(@PathVariable productName: String): ResponseEntity<String> {
        val url = "http://localhost:8086/producturl/$productName"
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(url, String::class.java)
    }

    @PutMapping("/user/verify/{userName}")
    @PreAuthorize("hasRole('Admin')")
    fun verifyUser(@PathVariable userName: String): ResponseEntity<*> {
        userServiceImpl.verifyUser(userName)
        return ResponseEntity.ok("User Verified!!!")
    }
}
