package com.term_project.system;
 
import com.term_project.game.GameState;
import com.term_project.system.MemorySlot;
 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
 
import joptsimple.OptionParser;
import joptsimple.OptionSet;
 
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;
 
 
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
 
import freemarker.template.Configuration;
 
/**
 * Hello world!
 *
 */
public class Main {
    private static final int DEFAULT_PORT = 4567;
    private static GameState gameState = null;
    private static final Gson GSON = new Gson();
 
    public static void main(String[] args) {
        new Main(args).run();
    }
    private String[] args;
 
    private Main(String[] args) {
        this.args = args;
    }
 
    private void run() {
        OptionParser parser = new OptionParser();
      parser.accepts("gui");
      parser.accepts("port")
                    .withRequiredArg()
                    .ofType(Integer.class)
            .defaultsTo(DEFAULT_PORT);
      OptionSet options = parser.parse(args);
 
      if (options.has("gui")) {
            runSparkServer((int) options.valueOf("port"));
      } else {
            Environment env = new Environment();
            try {
              env.startRepl();
            } catch (Throwable e) {
              e.printStackTrace();
            }
        }
    }
 
    /*
    * Some Spark methods/classes are currently residing in Main. We've discussed moving them if they get too over-bearing.
    *
    */
    private static FreeMarkerEngine createEngine() {
      Configuration config = new Configuration();
      File templates = new File("src/main/resources/spark/template/freemarker");
      try {
        config.setDirectoryForTemplateLoading(templates);
      } catch (IOException ioe) {
        System.out.printf("ERROR: Unable use %s for template loading.%n",
                                        templates);
        System.exit(1);
      }
      return new FreeMarkerEngine(config);
    }
 
    private void runSparkServer(int port) {
        //starts webSocket
    Spark.webSocket("/betrayal_connection", GameWebSocket.class);
 
      Spark.port(port);
      Spark.externalStaticFileLocation("src/main/resources/static");
      Spark.exception(Exception.class, new ExceptionPrinter());
 
      FreeMarkerEngine freeMarker = createEngine();
 
      // Setup Spark Routes
      Spark.get("/betrayal", new BetrayalHandler(), freeMarker);
        Spark.post("/betrayal", new UpdateHandler());
        Spark.post("/betrayal_create", new CreateLobbyHandler());
        Spark.post("/betrayal_join", new JoinLobbyHandler());
    }
 
    private static class MenuHandler implements TemplateViewRoute {
        @Override
        public ModelAndView handle(Request req, Response res) {
          String message = "<p>Let's play Betrayal!</p> To begin, either create your own game or join a lobby below:";
 
          Map<String, Object> variables = ImmutableMap.of(
                    "title",
              "Betrayal at House on the Hill",
                    "message",
                    message
            );
          return new ModelAndView(variables, "menu.ftl");
        }
    }
 
    private static class CreateLobbyHandler implements Route {
        @Override
        public String handle(Request req, Response res) {
 
          QueryParamsMap qm = req.queryMap();
          String game_name = qm.value("username");
          String player_number = qm.value("players");
 
          String message = "Your username is \"" + game_name + "\". ";
          message += "Waiting for more players to join your lobby of " + player_number + ".";
 
          System.out.println(message);
 
          Map<String, Object> variables = new HashMap<>();
 
            variables.put("", "");
            variables = ImmutableMap.copyOf(variables);
 
                return GSON.toJson(variables);
        }
    }
 
    private static class JoinLobbyHandler implements Route {
        @Override
        public String handle(Request req, Response res) {
 
          QueryParamsMap qm = req.queryMap();
          String game_name = qm.value("username");
 
          String message = "Your username is \"" + game_name + "\".";
 
          System.out.println(message);
 
          Map<String, Object> variables = new HashMap<>();
 
            variables.put("", "");
            variables = ImmutableMap.copyOf(variables);
 
                return GSON.toJson(variables);
        }
    }
   
    private static class BetrayalHandler implements TemplateViewRoute {
      @Override
      public ModelAndView handle(Request req, Response res) {
        Map<String, Object> variables = ImmutableMap.of(
                    "title",
            "Betrayal at House on the Hill"
            );
        return new ModelAndView(variables, "betrayal.ftl");
      }
    }
 
    private static class UpdateHandler implements Route {
      @Override
      public String handle(Request req, Response res) {
            QueryParamsMap qm = req.queryMap();
 
              Map<String, Object> variables = new HashMap<>();
 
              //the querymap sent back is basically just whatever action has just taken place
              //variables will have all information concerning the current player whose turn it is
 
                    // variables.putAll(gameState.update(qm));
                    // variables = ImmutableMap.copyOf(variables);
 
                  return GSON.toJson(variables);
      }
    }
 
    private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
        res.status(500);
        StringWriter stacktrace = new StringWriter();
        try (PrintWriter pw = new PrintWriter(stacktrace)) {
          pw.println("<pre>");
          e.printStackTrace(pw);
          pw.println("</pre>");
        }
        res.body(stacktrace.toString());
    }
    }
}
