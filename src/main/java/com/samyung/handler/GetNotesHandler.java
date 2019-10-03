package com.samyung.handler;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.data.DataService;
import com.samyung.param.GetNotesCriteria;
import com.samyung.entity.Note;

import java.util.List;

public class GetNotesHandler {

  public static String handle(Command command) {
    String paramStr = command.getParam();
    Gson gson = new Gson();
    GetNotesCriteria criteria = gson.fromJson(paramStr, GetNotesCriteria.class);

    List<Note> noteList = DataService.getInstance().getNotes(criteria);

    String jsonStr = gson.toJson(noteList);

    return jsonStr;
  }

//  public static void main(String[] args) {
//    GetNotesCriteria criteria = new GetNotesCriteria();
//    criteria.setCustomerId(2);
//
//    Gson gson = new Gson();
//    String criteriaStr = gson.toJson(criteria);
//
//    System.out.println("criteria="+criteria);
//    Command command = new Command();
//    command.setParam(criteriaStr);
//
//    String result = GetNotesHandler.handle(command);
//
//    System.out.println("result="+result);
//
//  }
}
