package com.samyung.disruptor;

import com.lmax.disruptor.EventHandler;
import com.samyung.command.Command;
import com.samyung.handler.*;
import io.vertx.ext.web.RoutingContext;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandEventHandler implements EventHandler<CommandEvent> {

	private final static Logger LOGGER = Logger.getLogger(CommandEventHandler.class.getName());

	@Override
	public void onEvent(CommandEvent commandEvent, long sequence, boolean endOfBatch) throws Exception {

		Command command = commandEvent.getCommand();

		LOGGER.finest("consuming sequence="+sequence);
		if (command == null){
			LOGGER.warning("command is null: consuming sequence="+sequence);
			return;
		}

		String response = null;

		switch (command.getCommandType()) {
			case GET_CUSTOMERS:
				response = GetCustomersHandler.handle(command);
				break;
			case GET_CUSTOMER_DETAILS:
				response = GetCustomerDetailsHandler.handle(command);
				break;
			case CHANGE_CUSTOMER_STATUS:
				response = ChangeCustomerStatusHandler.handle(command);
				break;
			case GET_NOTES:
				response = GetNotesHandler.handle(command);
				break;
			case ADD_NOTE:
				response = AddNoteHandler.handle(command);
				break;
			case EDIT_NOTE:
				response = EditNoteHandler.handle(command);
				break;
			case DELETE_NOTE:
				response = DeleteNoteHandler.handle(command);
				break;
			default:
				break;
		}

		RoutingContext ctx = commandEvent.getCtx();
		if (response == null) {
			LOGGER.warning("response is null");
			ctx.response().end();
		}

		ctx.response().end(response);
	}

}
