package com.ecommerce.controller;

import com.ecommerce.model.CustomerDto;
import com.ecommerce.model.Item;
import com.ecommerce.model.ItemDto;
import com.ecommerce.model.util.DataResponseWrapper;
import com.ecommerce.model.util.ResponseWrapper;
import com.ecommerce.service.AdminService;
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
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Api(
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE,
        tags = "Admin"
)
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addItem")
    @ApiOperation(value = "Create item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = AdminController.ItemResponseWrapper.class),
            @ApiResponse(code = 400, message = "400", response = ResponseWrapper.class)
    }
    )
    public DataResponseWrapper<ItemDto> addItem(@RequestBody Item item){

        return ItemResponseWrapper.ok(adminService.addItem(item))
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","item.add.success","Item added successfully"))
                .build();
    }

    @DeleteMapping("/item/{id}")
    @ApiOperation(value = "Delete item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = ResponseWrapper.class),
            @ApiResponse(code = 404, message = "404", response = ResponseWrapper.class)
    }
    )
    public ResponseWrapper deleteItem(@PathVariable String id){
        adminService.deleteItem(id);
        return ResponseWrapper.ok()
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","item.delete.success","Item deleted successfully"))
                .build();

    }

    @ApiOperation(value = "Delete customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = ResponseWrapper.class),
            @ApiResponse(code = 404, message = "404", response = ResponseWrapper.class)
    }
    )
    @DeleteMapping("/customer/{id}")
    public ResponseWrapper deleteCustomer(@PathVariable String id){
        adminService.deleteCustomer(id);
        return ResponseWrapper.ok()
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","customer.delete.success","Customer deleted successfully"))
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
        return CustomerResponseWrapper.ok(adminService.getCustomer(id))
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
        return ItemResponseWrapper.ok(adminService.getItem(id))
                .rootMessage(ResponseWrapper.ResponseMessage.of("200","item.get.success","Item fetched successfully"))
                .build();

    }

    public static class ItemResponseWrapper extends DataResponseWrapper<ItemDto>{

    }

    public static class CustomerResponseWrapper extends DataResponseWrapper<CustomerDto>{

    }

}
