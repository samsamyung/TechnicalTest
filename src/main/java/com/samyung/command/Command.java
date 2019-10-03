package com.samyung.command;

import io.vertx.ext.web.RoutingContext;

public class Command {
  private CommandType commandType;
  private String param;

  public CommandType getCommandType() {
    return commandType;
  }

  public void setCommandType(CommandType commandType) {
    this.commandType = commandType;
  }

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  @Override
  public String toString() {
    return "Command{" +
            "commandType=" + commandType +
            ", param='" + param + '\'' +
            '}';
  }
}
