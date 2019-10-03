package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.param.AddNoteParam;
import com.samyung.param.GetCustomerDetailsCriteria;
import com.samyung.entity.Customer;

public class AddNoteHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    AddNoteParam param = gson.fromJson(paramStr, AddNoteParam.class);

    int count = DataService.getInstance().insertNote(param);

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
    AddNoteParam param = new AddNoteParam();
    param.setContent("TESTTESTTEST");
    param.setCustomerId(2);

    Gson gson = new Gson();
    String paramStr = gson.toJson(param);

    System.out.println(paramStr);

    Command command = new Command();
    command.setParam(paramStr);

    String result = AddNoteHandler.handle(command);

    System.out.println(result);

  }
}
