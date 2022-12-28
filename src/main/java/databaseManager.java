import java.sql.*;

public class databaseManager {

    private Connection connect(){
        String url = "";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String[] insertUser(String staff_id, String ic_No, String name, String mobile_TelNo, String email){
        String sql = "INSERT INTO users(staff_id, ic_No, name, mobile_TelNo, email) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, staff_id);
            pstmt.setString(2, ic_No);
            pstmt.setString(3, name);
            pstmt.setString(4, mobile_TelNo);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }

    public String[] insertBooking(String room_id, String booking_date, String booking_time, String booking_purpose, String staff_id){
        String sql = "INSERT INTO booking(room_id, booking_date, booking_time, booking_purpose, staff_id) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, room_id);
            pstmt.setString(2, booking_date);
            pstmt.setString(3, booking_time);
            pstmt.setString(4, booking_purpose);
            pstmt.setString(5, staff_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }

    public void updateStatus(String room_id) {
        String sql = "UPDATE room SET status = 'Reserved' WHERE room_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, room_id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStatusDelete(String staffId) {
        String sql = "UPDATE room SET status = 'Ready' WHERE room_id =(SELECT room.room_id from room INNER JOIN booking on booking.room_id = room.room_id WHERE booking.staff_id = ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, staffId);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String displayBooking(String staffId) {
        String respond = " ";
        String sql = "SELECT * FROM users INNER JOIN booking ON users.staff_id = booking.staff_id WHERE users.staff_id = ?;";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                respond += "Congratulations! Record " + staffId + " already been search."+ "\n\n";
                respond += "These the booking about " + staffId + ": " + "\n\n";
                respond += " Booking Details" + "\n";
                respond += " ~~~~~~~~~~" + "\n";
                respond += " \u21aa\ufe0f Staff ID: "+ resultSet.getString("staff_id") + "\n";
                respond += " \u21aa\ufe0f Name: " + resultSet.getString("name") + "\n";
                respond += " \u21aa\ufe0f Phone Number: " + resultSet.getString("mobile_TelNo") + "\n\n";
                respond += " \u21aa\ufe0f Room ID: " + resultSet.getString("room_id") + "\n";
                respond += " \u21aa\ufe0f Booking Date: " + resultSet.getString("booking_date") + "\n";
                respond += " \u21aa\ufe0f Booking Time: " + resultSet.getString("booking_time") + "\n";
            }
            if(respond.equals(" ")){
                respond += "Sorry, there is no record of " + staffId + ". Please try another ID.";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    public String displayRoom() {
        String respond = " ";

        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM room")) {

            while (resultSet.next()) {
                respond += " \ud83d\udccd Room ID: "+ resultSet.getString("room_id") + "\n";
                respond += " \ud83d\udccd Room Description: " + resultSet.getString("room_description") + "\n";
                respond += " \ud83d\udccd Room Capacity: " + resultSet.getString("maximum_capacity") + "\n";
                respond += " \ud83d\udccd Status of room: " + resultSet.getString("status") + "\n\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    public String displayRoomID() {
        String respond = " ";

        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT room_id FROM room WHERE status = 'Ready'")) {

            while (resultSet.next()) {
                respond += "Room ID: "+ resultSet.getString("room_id") + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    public void deleteBooking(String staffId) {
        String sql = "DELETE FROM booking WHERE staff_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, staffId);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUsers(String staffId) {
        String sql = "DELETE FROM users WHERE staff_id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, staffId);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
