import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private ArrayList<Task> tasks;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = new ArrayList<>();
    }

    public int  getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void listTasks(){
        System.out.println("listing tasks for user " + name + " :");
        for(Task task : tasks){
            System.out.println(" - task: " + task.getName() + " | Status: " + task.getStatus());
        }
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

}
