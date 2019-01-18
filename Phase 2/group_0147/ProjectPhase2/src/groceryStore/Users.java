package groceryStore;

public abstract class Users {

  private String name;
  private boolean loggedIn;

  public Users(String name) {
    this.name = name;
  }

  public void logIn(){
    loggedIn = true;
  }

  public void logOut(){
    loggedIn = false;
  }

  public boolean isLoggedIn(){
    return loggedIn;
  }

  //public abstract void scan();

  public String getName() {
    return this.name;
  }

}
