package Admin;

import Entities.*;
import Util.Serializer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class TicketApp extends AppInterface{
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");
    public TicketApp(AppInterface prevApp) {
        super(prevApp);
    }


}
