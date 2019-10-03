package com.samyung;

import com.google.gson.Gson;
import com.samyung.command.Command;
import com.samyung.command.CommandType;
import com.samyung.disruptor.CommandEventDisruptor;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Server {
  private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

  private Vertx vertx = Vertx.vertx();
  private String host;
  private int port;

  private Map<CommandType, CommandEventDisruptor> commandType2DisruptorMap = new ConcurrentHashMap<>();

  public Server(String host, int port) {
    this.host = host;
    this.port = port;
  }

  private void initDisruptor(){
    CommandEventDisruptor multiThreadDisruptor = new CommandEventDisruptor();
    CommandEventDisruptor fifoDisruptor = new CommandEventDisruptor();

    commandType2DisruptorMap.put(CommandType.GET_CUSTOMERS, multiThreadDisruptor);
    commandType2DisruptorMap.put(CommandType.GET_CUSTOMER_DETAILS, multiThreadDisruptor);
    commandType2DisruptorMap.put(CommandType.CHANGE_CUSTOMER_STATUS, fifoDisruptor);
    commandType2DisruptorMap.put(CommandType.GET_NOTES, multiThreadDisruptor);
    commandType2DisruptorMap.put(CommandType.ADD_NOTE, fifoDisruptor);
    commandType2DisruptorMap.put(CommandType.EDIT_NOTE, fifoDisruptor);
    commandType2DisruptorMap.put(CommandType.DELETE_NOTE, fifoDisruptor);


    multiThreadDisruptor.start();
    fifoDisruptor.start();
  }

  public void startHttpServer() {
    LOGGER.info("Http Server is starting...");
    initDisruptor();

    HttpServerOptions options = new HttpServerOptions()
            .setHost(this.host)
            .setPort(this.port);

    Set<String> allowedHeaders = new HashSet<>();
    allowedHeaders.add("x-requested-with");
    allowedHeaders.add("Access-Control-Allow-Origin");
    allowedHeaders.add("origin");
    allowedHeaders.add("Content-Type");
    allowedHeaders.add("accept");
    allowedHeaders.add("X-PINGARUNER");

    Set<HttpMethod> allowedMethods = new HashSet<>();
//    allowedMethods.add(HttpMethod.GET);
    allowedMethods.add(HttpMethod.POST);
    allowedMethods.add(HttpMethod.OPTIONS);
//    allowedMethods.add(HttpMethod.DELETE);
//    allowedMethods.add(HttpMethod.PATCH);
//    allowedMethods.add(HttpMethod.PUT);

    Router router = Router.router(this.vertx);
    router.route()
            .handler(BodyHandler.create())
            .handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));

    // handle the form
    router.post("/").handler(ctx -> {
      String msg = ctx.getBodyAsString();
      handle(msg, ctx);
    });


    this.vertx
            .createHttpServer(options)
            .requestHandler(router)
            .listen();

    LOGGER.info("Http Server is now listening to "+this.host+":"+this.port);
  }

  private void handle(String str, RoutingContext ctx) {
    LOGGER.info(str);
    if (ctx==null) {
      LOGGER.warning("ctx is null");
      return;
    }

    if (str==null) {
      LOGGER.warning("str is null");
      return;
    }

    Gson gson = new Gson();
    Command command = gson.fromJson(str, Command.class);

    if (command.getCommandType()==null || command.getParam()==null) {
      LOGGER.warning("command or param is missing: commandType="+command.getCommandType()+" param="+command.getParam());
      return;
    }

    CommandEventDisruptor disruptor = commandType2DisruptorMap.get(command.getCommandType());

    if (disruptor == null) {
      LOGGER.warning("disruptor not found: command="+command.getCommandType()+" param="+command.getParam());
      return;
    }

    disruptor.submit(command, ctx);

    //LOGGER.log(Level.FINEST, "response="+response);

  }

  public void stop() {
    this.vertx.close();

    //stop all disruptor
    for (CommandEventDisruptor disruptor : commandType2DisruptorMap.values()) {
      disruptor.shutdown();
    }
  }
}
