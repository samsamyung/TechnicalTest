package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.param.DeleteNoteParam;
import com.samyung.param.EditNoteParam;

public class EditNoteHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    EditNoteParam param = gson.fromJson(paramStr, EditNoteParam.class);

    int count = DataService.getInstance().updateNote(param);

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
    EditNoteParam param = new EditNoteParam();
    param.setId(4);
    param.setContent("UPDATED CONTENT");

    Gson gson = new Gson();
    String paramStr = gson.toJson(param);

    System.out.println(paramStr);

    Command command = new Command();
    command.setParam(paramStr);

    String result = EditNoteHandler.handle(command);

    System.out.println(result);

  }
}
