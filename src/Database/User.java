package Database;

public class User {
    private int id;
    private String username, password, fullName, phone, address, email;

    public User(int id, String username, String password, String fullName, String phone, String address, String email) throws Exception {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setFullName(fullName);
        this.setPhone(phone);
        this.setAddress(address);
        this.setEmail(email);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) throws Exception {
        if (username.length() < 6)
            throw new IllegalArgumentException("Username must be at least 6 characters long");
        if (!username.matches("^[a-zA-Z0-9]+$"))
            throw new IllegalArgumentException("Username must contain only letter, numbers and no spaces");
        this.username = username;
    }

    public void setPassword(String password) throws Exception {
        if (!password.matches("^[a-zA-Z0-9]+$"))
            throw new IllegalArgumentException("Password must contain only letter, numbers and no spaces"); // Adjust password validation regex if needed
        this.password = password;
    }

    public void setFullName(String fullName) throws Exception {
        if (!fullName.matches("^[a-zA-Z]+$"))
            throw new IllegalArgumentException("Full name must contain only letters");
        this.fullName = fullName;
    }

    public void setPhone(String phone) throws Exception {
        if (!phone.matches("^[0-9]{11}$"))
            throw new IllegalArgumentException("Phone number must be 11 digits");
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) throws Exception {
        if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
            throw new IllegalArgumentException("Invalid email address");
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
