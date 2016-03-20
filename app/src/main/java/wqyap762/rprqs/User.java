package wqyap762.rprqs;

public class User {

    private String _username;
    private String _fullname;
    private String _password;
    private int _hpno;

    public User() {
    }

    public User(String username, String fullname, String password, int hpno) {
        this._username = username;
        this._fullname = fullname;
        this._password = password;
        this._hpno = hpno;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public void set_fullname(String _fullname) {
        this._fullname = _fullname;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_hpno(int _hpno) {
        this._hpno = _hpno;
    }

    public String get_username() {
        return _username;
    }

    public String get_fullname() {
        return _fullname;
    }

    public String get_password() {
        return _password;
    }

    public int get_hpno() {
        return _hpno;
    }
}
