package com.ecommerce.service;

import com.ecommerce.exception.NonUniqueCustomerException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.CustomerDto;
import com.ecommerce.model.Item;
import com.ecommerce.model.ItemDto;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ItemDto addItem(Item item){
        if(isItemExists(item.getId())){
            throw new NonUniqueCustomerException("Item with id : "+item.getId()+" already exists in DB.");
        }
        return ItemDto.from(itemRepository.save(item));
    }

    public void deleteItem(String itemId){
        if(! isItemExists(itemId)){
            throw new ResourceNotFoundException("Item with id : "+itemId+" not exists");
        }
        itemRepository.deleteById(itemId);
    }

    public void deleteCustomer(String customerId){
        if(! isCustomerExists(customerId)){
            throw new ResourceNotFoundException("Item with id : "+customerId+" not exists");
        }
        customerRepository.deleteById(customerId);
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

    public boolean isItemExists(String id){
        return itemRepository.existsById(id);
    }

    public boolean isCustomerExists(String id){
        return customerRepository.existsById(id);
    }
}
