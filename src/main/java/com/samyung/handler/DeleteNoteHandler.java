package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.param.AddNoteParam;
import com.samyung.param.DeleteNoteParam;

public class DeleteNoteHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    DeleteNoteParam param = gson.fromJson(paramStr, DeleteNoteParam.class);

    int count = DataService.getInstance().deleteNote(param);

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
    DeleteNoteParam param = new DeleteNoteParam();
    param.setId(2);

    Gson gson = new Gson();
    String paramStr = gson.toJson(param);

    System.out.println(paramStr);

    Command command = new Command();
    command.setParam(paramStr);

    String result = DeleteNoteHandler.handle(command);

    System.out.println(result);

  }
}
