package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.param.GetCustomersCriteria;
import com.samyung.entity.Customer;

import java.util.List;

public class GetCustomersHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    GetCustomersCriteria criteria = gson.fromJson(paramStr, GetCustomersCriteria.class);

    List<Customer> customerList = DataService.getInstance().getCustomers(criteria);

    String jsonStr = gson.toJson(customerList);

    return jsonStr;
  }

//  public static void main(String[] args) {
//    GetCustomersCriteria criteria = new GetCustomersCriteria();
////    criteria.setAddress("ad");
//    criteria.setName("a");
////    criteria.setEmail("@abc.com");
//    criteria.setSortBy(CustomerSortBy.CREATION_DATE_TIME);
//    criteria.setDecsending(true);
//
//    Gson gson = new Gson();
//    String criteriaStr = gson.toJson(criteria);
//
//    System.out.println("criteria="+criteria);
//    Command command = new Command();
//    command.setParam(criteriaStr);
//
//    String result = GetCustomersHandler.handle(command);
//
//    System.out.println("result="+result);
//
//  }
}
