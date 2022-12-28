import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class bot extends TelegramLongPollingBot {

    private String staff_id, ic_No, name, mobile_TelNo, email, room_id, booking_date, booking_time, booking_purpose;

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message;
        if(update.hasMessage()){
            message = update.getMessage();
        }else
            return;

        String[] com = message.getText().split(" - ");

        String chatId = message.getChatId().toString();
        String command = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        databaseManager app = new databaseManager();

        switch (com[0]){
            case "/start":
                if(com.length == 1){
                    sendMessage.setText("Hihi, "+ update.getMessage().getFrom().getFirstName() + ". \ud83d\udc4b\n" +
                            "Welcome to Meeting Room Booking System!\n\n" +
                            "/information - See more information\n" +
                            "/booking - Booking a meeting room\n" +
                            "/list - Checking the list of room\n" +
                            "/checking - Checking booking by enter staff ID\n" +
                            "/cancel - Cancel booking by enter Staff ID");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case"/information":
                sendMessage.setText("Welcome to Meeting Room Booking System!\n\n" +
                        "/information - See more information\n" +
                        "/booking - Booking a meeting room\n" +
                        "/list - Checking the list of room\n" +
                        "/checking - Checking booking by enter staff ID\n" +
                        "/cancel - Cancel booking by enter Staff ID");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/booking":
                sendMessage.setText("Hi " + update.getMessage().getFrom().getFirstName() + ", let's proceed with booking-making! " +
                        "\nPlease provide the following information to book the meeting room." +
                        "\n\n\t\ud83d\udc49 Please enter your staff ID. (Format: ID - xxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "ID":
                staff_id = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                sendMessage.setText("Your staff ID is " + staff_id + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter your name. (Format: Name - xxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Name":
                name = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your name is " + name + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter your IC number. (Format: IC - xxxxxx-xx-xxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "IC":
                ic_No = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your IC Number is " + ic_No + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49  Please enter your phone number. (Format: Phone - xxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Phone":
                mobile_TelNo = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your phone number is " + mobile_TelNo + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n  \ud83d\udccb Phone Number: " + mobile_TelNo +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter your email. (Format: Email - xxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Email":
                email = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your email is " + email + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n  \ud83d\udccb Phone Number: " + mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + email +
                        "\n\nSo, we continue with the information."+
                        "\n\n" + "These are the room that is ready to booking: "+
                        "\n" + app.displayRoomID()+
                        "\n\t\ud83d\udc49 Which room you want to booking? (Format: Room - xxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Room":
                room_id = com[1];
                sendMessage.setText("Your booking room is " + room_id + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n  \ud83d\udccb Phone Number: " + mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + room_id +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the date you want to booking. (Format: Date - xx-xx-xxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Date":
                booking_date = com[1];
                sendMessage.setText("Your booking date is " + booking_date + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n  \ud83d\udccb Phone Number: " + mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + email +
                        "\n\n  Booking Details" +
                        "\n  =================" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + room_id +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + booking_date +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the time you want to booking. (Format: Time - xx:xxAM/ xx:xxPM) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Time":
                booking_time = com[1];
                sendMessage.setText("Your booking time is " + booking_time + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n  \ud83d\udccb Phone Number: " + mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + email +
                        "\n\n  Booking Details" +
                        "\n  =================" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + room_id +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + booking_date +
                        "\n  \ud83c\udfdb\ufe0f Booking Time: " + booking_time +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the purpose you want to booking. (Format: Purpose - xxxxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Purpose":
                booking_purpose = com[1];
                sendMessage.setText("Your booking purpose is " + booking_purpose + ". "+
                        "\n\nYour have already finish enter your booking information." +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + staff_id +
                        "\n  \ud83d\udccb Name: " + name +
                        "\n  \ud83d\udccb IC Number: " + ic_No +
                        "\n  \ud83d\udccb Phone Number: " + mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + email +
                        "\n\n  Booking Details" +
                        "\n  =================" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + room_id +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + booking_date +
                        "\n  \ud83c\udfdb\ufe0f Booking Time: " + booking_time +
                        "\n  \ud83c\udfdb\ufe0f Booking Purpose: " + booking_purpose +
                        "\n\n\ud83d\udc4f Congratulations!! You have successfully booking the meeting room!"
                );
                app.insertUser(staff_id, ic_No, name, mobile_TelNo, email);
                app.insertBooking(room_id, booking_date, booking_time, booking_purpose, staff_id);
                app.updateStatus(room_id);
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/list":
                String msg = app.displayRoom();
                sendMessage.setText("Details of meeting room: " + "\n\n" +msg);
                sendMessage.setChatId(update.getMessage().getChatId());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/checking":
                if(command.equals("/checking")){
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. "+
                            "\n \ud83d\udc49 Example format: /checking - staff ID");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else{
                    staff_id = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    String displayBooking = app.displayBooking(staff_id);
                    sendMessage.setText(displayBooking);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                break;

            case "/cancel":
                if(command.equals("/cancel")){
                    sendMessage.setText("If you want to cancel booking, please enter the staff ID to cancel it. "+
                            "\n \ud83d\udc49 Example format: /cancel - staff ID");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else{
                    staff_id = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    app.updateStatusDelete(staff_id);
                    app.deleteUsers(staff_id);
                    app.deleteBooking(staff_id);
                    sendMessage.setText("Booking of " + staff_id + " already been cancel."+ "\n\n");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                sendMessage.setText("Please enter command available with the correct format. \u2764\ufe0f");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}