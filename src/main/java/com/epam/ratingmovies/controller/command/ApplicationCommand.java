package com.epam.ratingmovies.controller.command;

import com.epam.ratingmovies.controller.command.impl.*;

import java.util.Arrays;

enum ApplicationCommand {
    GO_TO_SIGN_UP_PAGE(new GoToSignUpPageCommand(), CommandName.SIGN_UP_PAGE),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand(), CommandName.LOGIN_PAGE),
    GO_TO_USER_PAGE(new GoToUserPageCommand(), CommandName.PROFILE_PAGE),
    //GO_TO_ACCOUNT_SETTING_PAGE(new GoToAccountSettingPageCommand(), CommandName.ACCOUNT_SETTINGS_PAGE),
    /*  GO_TO_GAMBLERS_PAGE(new GoToUsersPageCommand(), CommandName.USERS_PAGE),
      GO_TO_STATISTIC_GAMES(new GoToStatisticGamesPageCommand(),CommandName.STATISTIC_GAMES_PAGE),
      GO_TO_ADMIN_PANEL_PAGE(new GoToAdminPanelPageCommand(), CommandName.ADMIN_PANEL_PAGE),

      */
    GO_TO_HOME_PAGE(new GoToHomePageCommand(), CommandName.HOME_PAGE),
    //todo добавить только для админа ?
    GO_TO_USERS_PAGE(new GoToUsersPageCommand(), CommandName.USERS_PAGE),
    GO_TO_MOVIES_PAGE(new GoToMoviesPageCommand(), CommandName.MOVIES_PAGE),
    SIGN_UP(new SignUpCommand(), CommandName.SIGN_UP),
    LOGIN(new LoginCommand(), CommandName.LOGIN);

    /*LOGOUT(new LogoutCommand(), CommandName.LOGOUT),
    LOCALIZATION(new LocalizationCommand(), CommandName.LOCALIZATION),
    CHANGE_PASSWORD(new ChangePasswordCommand(), CommandName.CHANGE_PASSWORD),
    CHANGE_GENERAL_INFO(new ChangeGeneralInfoCommand(), CommandName.CHANGE_GENERAL_INFO),
    //AJAX
    CHECK_EXIST_LOGIN(new CheckExistUsernameCommand(), CommandName.CHECK_EXIST_LOGIN),
    CHECK_EXIST_EMAIL(new CheckExistUsernameCommand(), CommandName.CHECK_EXIST_EMAIL),
    ACTION_BAN_USER(new ActionBanUserCommand(), CommandName.ACTION_BAN_USER),
    ACTION_UNBAN_USER(new ActionUnbanUserCommand(), CommandName.ACTION_UNBAN_USER),
    ACTION_CHANGE_BALANCE_USER(new ActionChangeBalanceUserCommand(), CommandName.ACTION_CHANGE_BALANCE),
    //game
    TAKE_LOBBY_DATA(new TakeLobbyDataCommand(), CommandName.LOBBY_DATA),
    TAKE_TABLE_DATA(new TakeTableDataCommand(), CommandName.TABLE_DATA);
*/
    private final Command command;
    private final String commandName;

    ApplicationCommand(Command command, String commandName) {
        this.command = command;
        this.commandName = commandName;
    }

    public Command getCommand() {
        return command;
    }

    public static ApplicationCommand getByString(String commandString) {
        return Arrays.stream(ApplicationCommand.values())
                .filter(command -> command.toString()
                        .equalsIgnoreCase(commandString))
                .findFirst()
                //.orElse(DEFAULT);
                .orElse(null);
    }

    public String getCommandName() {
        return commandName;
    }

    static Command of(String name) {
        for (ApplicationCommand action : ApplicationCommand.values()) {
            if (action.getCommandName().equalsIgnoreCase(name)) {
                return action.command;
            }
        }
        return null;
        //todo
        // return GoToNotFoundPageCommand.INSTANCE;
    }
}
