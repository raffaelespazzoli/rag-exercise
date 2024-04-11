package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/query-raffa")
public class ChatWebBot {
  @Inject RaffaBot raffaBot;

  @GET
  public String query(@QueryParam(value = "session") String session,@QueryParam(value = "question") String question) {
     return raffaBot.chat(question);
  }
}