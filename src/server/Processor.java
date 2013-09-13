package server;

import server.Request;
import server.Response;

public interface Processor {

    Response process(Request request);
}
