package com.samyung.disruptor;

import com.google.gson.Gson;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.samyung.command.Command;
import io.vertx.ext.web.RoutingContext;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandEventProducer {
	
	private final static Logger LOGGER = Logger.getLogger(CommandEventProducer.class.getName());

  private final static EventTranslatorTwoArg<CommandEvent, Command, RoutingContext> TRANSLATOR =
          new EventTranslatorTwoArg<CommandEvent, Command, RoutingContext>() {
            public void translateTo(CommandEvent event, long sequence, Command command, RoutingContext ctx) {
              event.setCommand(command);
              event.setCtx(ctx);
            }
          };

  private final RingBuffer<CommandEvent> ringBuffer;

  public CommandEventProducer(RingBuffer<CommandEvent> ringBuffer)
  {
      this.ringBuffer = ringBuffer;
  }

  public void onData(Command command, RoutingContext ctx){
    ringBuffer.publishEvent(TRANSLATOR, command, ctx);
  }



}
