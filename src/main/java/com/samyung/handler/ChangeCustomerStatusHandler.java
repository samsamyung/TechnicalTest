package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.entity.CustomerStatus;
import com.samyung.param.AddNoteParam;
import com.samyung.param.ChangeCustomerStatusParam;

public class ChangeCustomerStatusHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    ChangeCustomerStatusParam param = gson.fromJson(paramStr, ChangeCustomerStatusParam.class);

    int count = DataService.getInstance().updateCustomerStatus(param);

    String jsonStr;

    if (count > 0) {
      jsonStr = gson.toJson("OK");
    }
    else {
      jsonStr = gson.toJson("ERROR");
    }

    return jsonStr;
  }

  public static void main(String[] args) {
    ChangeCustomerStatusParam param = new ChangeCustomerStatusParam();
    param.setId(1);
    param.setStatus(CustomerStatus.CURRENT);

    Gson gson = new Gson();
    String paramStr = gson.toJson(param);

    System.out.println(paramStr);

    Command command = new Command();
    command.setParam(paramStr);

    String result = ChangeCustomerStatusHandler.handle(command);

    System.out.println(result);
  }
}
