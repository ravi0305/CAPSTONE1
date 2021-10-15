package com.ecommerce.controller;

import com.ecommerce.model.Customer;
import com.ecommerce.model.CustomerDto;
import com.ecommerce.model.util.DataResponseWrapper;
import com.ecommerce.model.util.ResponseWrapper;
import com.ecommerce.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Api(
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE,
        tags = "Customers"
)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    @ApiOperation(value = "Create customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = CustomerResponseWrapper.class),
            @ApiResponse(code = 400, message = "400", response = ResponseWrapper.class)
    }
    )
    public DataResponseWrapper<CustomerDto> register(@RequestBody Customer customer){
        return CustomerResponseWrapper.ok(customerService.registerOrCreate(customer))
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","customer.register.success","Customer registered successfully"))
                .build();
        //save in customer table
    }

    @ApiOperation(value = "purchase item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = ResponseWrapper.class),
            @ApiResponse(code = 404, message = "400", response = ResponseWrapper.class)
    }
    )
    @PostMapping("/purchase/{itemId}/{customerId}")
    public ResponseWrapper purchase(@PathVariable String itemId, @PathVariable String customerId){
        customerService.purchaseItems(itemId, customerId);

        return ResponseWrapper.ok()
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","item.purchase.success","Item purchased successfully"))
                .build();
    }

    @GetMapping("/customer/{id}")
    @ApiOperation(value = "Get customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = AdminController.CustomerResponseWrapper.class),
            @ApiResponse(code = 404, message = "404", response = ResponseWrapper.class)
    }
    )
    public ResponseWrapper getCustomer(@PathVariable String id){
        return AdminController.CustomerResponseWrapper.ok(customerService.getCustomer(id))
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","customer.get.success","Customer fetched successfully"))
                .build();

    }

    @GetMapping("/item/{id}")
    @ApiOperation(value = "Get item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = AdminController.ItemResponseWrapper.class),
            @ApiResponse(code = 404, message = "404", response = ResponseWrapper.class)
    }
    )
    public ResponseWrapper getItem(@PathVariable String id){
        return AdminController.ItemResponseWrapper.ok(customerService.getItem(id))
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","item.get.success","Item fetched successfully"))
                .build();

    }

    public static class CustomerResponseWrapper extends DataResponseWrapper<CustomerDto>{

    }

}
