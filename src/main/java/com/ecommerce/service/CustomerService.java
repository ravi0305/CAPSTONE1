package com.ecommerce.service;

import com.ecommerce.exception.NonUniqueCustomerException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.CustomerDto;
import com.ecommerce.model.Item;
import com.ecommerce.model.ItemDto;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;


    public CustomerDto registerOrCreate(Customer customer){
        if(isCustomerExists(customer.getId())){
            throw new NonUniqueCustomerException("Customer with id: "+customer.getId()+"  already exists!");
        }
        return CustomerDto.from(customerRepository.save(customer));
    }

    public void purchaseItems(String itemId, String customerId){
        if(!isItemExists(itemId)){
            throw new ResourceNotFoundException("Item with id : "+itemId+" not exists");
        }

        if(!isCustomerExists(customerId)){
            throw new ResourceNotFoundException("Customer with id : "+customerId+" not exists");
        }

        Customer customer = customerRepository.findById(customerId).get();
        Item item =itemRepository.findById(itemId).get();
        List<Item> items =customer.getItems();
        items.add(item);
        customer.setItems(items);
        customerRepository.save(customer);

    }

    public CustomerDto getCustomer(String customerId){
        if(!isCustomerExists(customerId)){
            throw new ResourceNotFoundException("Customer with id : "+customerId+" not exists");
        }
        return CustomerDto.from(customerRepository.findById(customerId).get());
    }

    public ItemDto getItem(String itemId){
        if(!isItemExists(itemId)){
            throw new ResourceNotFoundException("Item with id : "+itemId+" not exists");
        }
        return ItemDto.from(itemRepository.findById(itemId).get());
    }

    public boolean isCustomerExists(String id){
        return customerRepository.existsById(id);
    }

    public boolean isItemExists(String id){
        return itemRepository.existsById(id);
    }
}
