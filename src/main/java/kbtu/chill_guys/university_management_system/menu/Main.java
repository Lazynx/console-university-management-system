package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.controller.AuthController;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.service.AuthService;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        LoggerUtil.configureLogging();

        UserRepository userRepository = new UserRepository(Paths.get("account.ser"));
        AdminService adminService = new AdminService(userRepository);
        AdminController adminController = new AdminController(adminService);
        AdminView adminView = new AdminView();

        AuthService authService = new AuthService(userRepository);
        AuthController authController = new AuthController(authService);
        AuthView authView = new AuthView();

        Menu menu = new Menu();

        menu.registerCommand("createUser", new CreateUserCommand(adminController, adminView));
        menu.registerCommand("updateUser", new UpdateUserCommand(adminController, adminView));
        menu.registerCommand("deleteUser", new DeleteUserCommand(adminController, adminView));
        menu.registerCommand("login", new LoginCommand(authController, authView, menu));
        menu.registerCommand("logout", new LogoutCommand(menu));

        menu.run();
    }
}
