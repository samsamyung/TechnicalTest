package com.samyung.disruptor;

import com.samyung.command.Command;
import io.vertx.ext.web.RoutingContext;

public class CommandEvent {

	private Command command;
	private RoutingContext ctx;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public RoutingContext getCtx() {
		return ctx;
	}

	public void setCtx(RoutingContext ctx) {
		this.ctx = ctx;
	}
}

