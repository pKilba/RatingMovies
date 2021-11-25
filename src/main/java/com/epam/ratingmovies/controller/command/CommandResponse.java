package com.epam.ratingmovies.controller.command;

public class CommandResponse {
    private  String page;
    private  boolean isRedirect;

    private CommandResponse(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResponse redirect(String page) {
        return new CommandResponse(page, true);
    }

    public static CommandResponse forward(String page) {
        return new CommandResponse(page, false);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}

