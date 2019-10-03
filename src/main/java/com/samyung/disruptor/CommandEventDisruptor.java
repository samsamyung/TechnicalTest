package com.samyung.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.samyung.command.Command;
import io.vertx.ext.web.RoutingContext;

import java.util.concurrent.Executors;

public class CommandEventDisruptor {

	private Disruptor<CommandEvent> disruptor = null;
	private CommandEventProducer producer = null;

	public CommandEventDisruptor() {
		int bufferSize = 1024;// Specify the size of the ring buffer, must be power of 2.

		CommandEventFactory factory = new CommandEventFactory();

		// Construct the Disruptor
		this.disruptor = new Disruptor<CommandEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

		// Connect the handler
		this.disruptor.handleEventsWith(new CommandEventHandler());

		this.producer = new CommandEventProducer(this.disruptor.getRingBuffer());
	}

	public void submit(Command command, RoutingContext ctx) {
		this.producer.onData(command, ctx);
	}
    
	public void start(){
		this.disruptor.start();
	}

	public void shutdown(){
		this.disruptor.shutdown();
	}
}
