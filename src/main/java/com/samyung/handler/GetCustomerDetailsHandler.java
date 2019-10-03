package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.param.GetCustomerDetailsCriteria;
import com.samyung.entity.Customer;

public class GetCustomerDetailsHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    GetCustomerDetailsCriteria criteria = gson.fromJson(paramStr, GetCustomerDetailsCriteria.class);

    Customer customer = DataService.getInstance().getCustomerDetails(criteria);

    String jsonStr = gson.toJson(customer);

    return jsonStr;
  }

//  public static void main(String[] args) {
//    GetCustomerDetailsCriteria criteria = new GetCustomerDetailsCriteria();
//    criteria.setId(2);
//
//    Gson gson = new Gson();
//    String criteriaStr = gson.toJson(criteria);
//
//    System.out.println("criteria="+criteria);
//    Command command = new Command();
//    command.setParam(criteriaStr);
//
//    String result = GetCustomerDetailsHandler.handle(command);
//
//    System.out.println("result="+result);
//
//  }
}
